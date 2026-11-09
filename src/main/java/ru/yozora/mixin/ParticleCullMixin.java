package ru.yozora.mixin;

import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.yozora.modules.opt.EntityCull;
import ru.yozora.modules.opt.ParticleCull;

@Mixin(ParticleManager.class)
public class ParticleCullMixin {

    @Inject(method = "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", at = @At("HEAD"), cancellable = true)
    private void yozora$cullFarParticles(net.minecraft.particle.ParticleEffect parameters,
                                         double x, double y, double z,
                                         double vx, double vy, double vz,
                                         CallbackInfo ci) {
        ClientWorld world = (ClientWorld) (Object) this;
        if (world.getCameraEntity() == null) return;

        BlockPos camPos = world.getCameraEntity().getBlockPos();
        if (ParticleCull.shouldSkip(x - camPos.getX(), y - camPos.getY(), z - camPos.getZ())) {
            ci.cancel();
        }
    }
}
