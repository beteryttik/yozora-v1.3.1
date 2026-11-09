package ru.yozora.mixin;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.yozora.modules.opt.EntityCull;

@Mixin(EntityRenderDispatcher.class)
public class EntityCullMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void yozora$cullFarEntities(Entity entity, double x, double y, double z,
                                        float yaw, float delta,
                                        org.joml.Matrix4f matrix4f,
                                        net.minecraft.client.render.VertexConsumerProvider vertexConsumers,
                                        int light,
                                        CallbackInfo ci) {
        if (EntityCull.shouldSkip(x, y, z)) {
            ci.cancel();
        }
    }
}
