package ru.yozora.event;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import ru.yozora.Yozora;
import ru.yozora.modules.Module;
import ru.yozora.modules.hud.HudModule;
import ru.yozora.modules.visual.CustomCrosshair;

public class HudManager {
    private static int offsetY = 0;

    public static void register() {
        HudRenderCallback.EVENT.register(HudManager::render);
    }

    private static void render(DrawContext context, float delta) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.options.hudHidden) return;

        offsetY = 4;

        for (Module m : Yozora.modules().all()) {
            if (!(m instanceof HudModule hud)) continue;
            if (!hud.isEnabled()) continue;

            String text = hud.text();
            if (text == null || text.isEmpty()) continue;

            context.fill(hud.x - 2, offsetY - 2, hud.x + mc.textRenderer.getWidth(text) + 2, offsetY + 10, 0x88000000);
            context.drawTextWithShadow(mc.textRenderer, text, hud.x, offsetY, hud.color());
            offsetY += 13;
        }

        Module crosshair = Yozora.modules().byName("CustomCrosshair");
        if (crosshair != null && crosshair.isEnabled() && mc.options.getPerspective().isFirstPerson()) {
            CustomCrosshair cc = (CustomCrosshair) crosshair;
            int cx = mc.getWindow().getScaledWidth() / 2;
            int cy = mc.getWindow().getScaledHeight() / 2;
            int s = cc.size;
            int g = cc.gap;
            int col = cc.color;
            context.fill(cx - s - g, cy, cx - g, cy + cc.thickness, col);
            context.fill(cx + g, cy, cx + s + g, cy + cc.thickness, col);
            context.fill(cx, cy - s - g, cx + cc.thickness, cy - g, col);
            context.fill(cx, cy + g, cx + cc.thickness, cy + s + g, col);
        }
    }
}
