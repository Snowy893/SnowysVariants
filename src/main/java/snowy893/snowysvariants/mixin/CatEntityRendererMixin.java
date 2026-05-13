package snowy893.snowysvariants.mixin;

import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatRenderer.class)
public class CatEntityRendererMixin {
    @Unique private static final ResourceLocation ALIEN_TEXTURE = getVariantTexture("alien");
    @Unique private static final ResourceLocation GAMORA_TEXTURE = getVariantTexture("gamora");
    
    public CatEntityRendererMixin() {
    }
    
    @Inject(
            method = {"getTextureLocation(Lnet/minecraft/world/entity/animal/Cat;)Lnet/minecraft/resources/ResourceLocation;"},
            at = {@At("RETURN")},
            cancellable = true
    )
    private void getCatTexture(Cat pEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        String catName = pEntity.getName().getString();
        switch (catName.toLowerCase()) {
            case "alien" -> cir.setReturnValue(ALIEN_TEXTURE);
            case "gamora" -> cir.setReturnValue(GAMORA_TEXTURE);
        }
    }
    
    @SuppressWarnings("removal")
    @Unique
    private static ResourceLocation getVariantTexture(String name) {
        return new ResourceLocation("textures/entity/cat/"+name+".png");
    }
}
