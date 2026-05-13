package snowy893.snowysvariants;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SnowysVariants.MOD_ID)
public class SnowysVariants {
    public static final String MOD_ID = "snowysvariants";
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public static ResourceLocation modPrefix(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public SnowysVariants(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        // ModCatVariants.register(modEventBus);
    }
}
