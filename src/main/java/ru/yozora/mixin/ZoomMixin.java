package ru.yozora.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import ru.yozora.modules.visual.Zoom;

@Mixin(GameRenderer.class)
public class ZoomMixin {

    @ModifyArg(method = "renderWorld", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/render/Camera;getFov()D"), require = 0)
    private double yozora$zoomFov(double original) {
        if (!Zoom.active()) return original;
        return original / Zoom.FACTOR;
    }
}
