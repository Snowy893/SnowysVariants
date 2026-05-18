package snowy893.snowysvariants.mixin;

import net.minecraft.client.renderer.entity.TurtleRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Turtle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snowy893.snowysvariants.TurtleVariant;
import snowy893.snowysvariants.mixinapi.ITurtle;

@Mixin(TurtleRenderer.class)
public class TurtleRendererMixin {
    @Inject(
            method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Turtle;)Lnet/minecraft/resources/ResourceLocation;",
            at = @At("RETURN"),
            cancellable = true
    )
    private void getTurtleTexture(Turtle pEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        TurtleVariant variant = TurtleVariant.getVariant(((ITurtle) pEntity).getVariant());
        cir.setReturnValue(variant.getTexture());
    }
}
