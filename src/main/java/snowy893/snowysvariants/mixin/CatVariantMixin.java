package snowy893.snowysvariants.mixin;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.CatVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatVariant.class)
public abstract class CatVariantMixin {
    @Shadow
    private static ResourceKey<CatVariant> createKey(String pName) { return null; }
    
    @Unique
    private static final ResourceKey<CatVariant> ALIEN = createKey("alien");
    
    @Inject(method = {"bootstrap"}, at = {@At("RETURN")})
    private static void bootstrap(Registry<CatVariant> pRegistry, CallbackInfoReturnable<CatVariant> cir) {
        register(pRegistry, ALIEN, "alien");
    }
    
    @SuppressWarnings("removal") @Unique
    private static void register(Registry<CatVariant> pRegistry, ResourceKey<CatVariant> pKey, String name) {
        Registry.register(pRegistry, pKey, new CatVariant(new ResourceLocation("textures/entity/cat/"+name+".png")));
    }
}
