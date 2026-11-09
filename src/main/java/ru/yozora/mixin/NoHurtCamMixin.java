package ru.yozora.mixin;

import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.yozora.modules.visual.NoHurtCam;

@Mixin(Camera.class)
public class NoHurtCamMixin {

    @Inject(method = "tilt", at = @At("HEAD"), cancellable = true)
    private void yozora$cancelHurtTilt(float roll, float delta, CallbackInfo ci) {
        if (NoHurtCam.shouldCancel()) {
            ci.cancel();
        }
    }
}
