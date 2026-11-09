package ru.yozora.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import ru.yozora.Yozora;
import ru.yozora.modules.Category;
import ru.yozora.modules.Module;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiScreen extends Screen {
    private static final int PANEL_X = 12;
    private static final int PANEL_Y = 12;
    private static final int PANEL_W = 140;
    private static final int PANEL_H = 20;

    private Category selected = Category.OPT;
    private String search = "";

    public ClickGuiScreen() {
        super(Text.literal("Yozora Client"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, this.width, this.height, 0xC0101018);

        // header
        context.fill(PANEL_X, PANEL_Y, PANEL_X + PANEL_W, PANEL_Y + PANEL_H, 0xFF1E2233);
        context.drawTextWithShadow(this.textRenderer, "Yozora Client", PANEL_X + 6, PANEL_Y + 6, 0xFFA78BFA);

        // search
        context.fill(PANEL_X, PANEL_Y + PANEL_H + 4, PANEL_X + PANEL_W, PANEL_Y + PANEL_H + 24, 0xFF1A1E2E);
        context.drawTextWithShadow(this.textRenderer,
                search.isEmpty() ? "Search..." : search,
                PANEL_X + 6, PANEL_Y + PANEL_H + 10, 0xFF8892B0);

        // categories
        int y = PANEL_Y + PANEL_H + 30;
        for (Category c : Category.values()) {
            int color = (c == selected) ? 0xFF2A2F47 : 0xFF1A1E2E;
            context.fill(PANEL_X, y, PANEL_X + PANEL_W, y + 22, color);
            context.drawTextWithShadow(this.textRenderer, c.title, PANEL_X + 6, y + 7, 0xFFFFFFFF);
            y += 26;
        }

        // module list
        int mx = PANEL_X + PANEL_W + 8;
        int my = PANEL_Y;
        for (Module m : currentList()) {
            int color = m.isEnabled() ? 0xFF1F3A2E : 0xFF22273E;
            context.fill(mx, my, mx + 180, my + 22, color);
            context.drawTextWithShadow(this.textRenderer, m.getName(), mx + 6, my + 7,
                    m.isEnabled() ? 0xFF55FF99 : 0xFFCCD0E0);
            my += 26;
        }

        super.render(context, mouseX, mouseY, delta);
    }

    private List<Module> currentList() {
        List<Module> out = new ArrayList<>();
        for (Module m : Yozora.modules().all()) {
            if (m.getCategory() != selected) continue;
            if (!search.isEmpty() && !m.getName().toLowerCase().contains(search.toLowerCase())) continue;
            out.add(m);
        }
        return out;
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        if (super.mouseClicked(click, doubled)) return true;

        double mx = click.x();
        double my = click.y();

        // category click
        int y = PANEL_Y + PANEL_H + 30;
        for (Category c : Category.values()) {
            if (mx >= PANEL_X && mx <= PANEL_X + PANEL_W && my >= y && my <= y + 22) {
                selected = c;
                return true;
            }
            y += 26;
        }

        // module click
        int mxx = PANEL_X + PANEL_W + 8;
        int mmy = PANEL_Y;
        for (Module m : currentList()) {
            if (mx >= mxx && mx <= mxx + 180 && my >= mmy && my <= mmy + 22) {
                m.toggle();
                Yozora.save();
                return true;
            }
            mmy += 26;
        }

        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 259 && !search.isEmpty()) { // backspace
            search = search.substring(0, search.length() - 1);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(net.minecraft.client.input.CharInput input) {
        char c = input.character();
        if (Character.isLetterOrDigit(c) || c == ' ') {
            search += c;
            return true;
        }
        return super.charTyped(input);
    }

    @Override
    public void close() {
        Yozora.save();
        MinecraftClient.getInstance().setScreen(null);
    }
}
