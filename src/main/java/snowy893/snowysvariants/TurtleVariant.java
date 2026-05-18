package snowy893.snowysvariants;

import net.minecraft.resources.ResourceLocation;

public enum TurtleVariant {
    DEFAULT("big_sea_turtle"),
    PINK("pink");
    
    TurtleVariant(String name) {
        this.name = name;
    }
    
    private final String name;
    
    @SuppressWarnings("removal")
    public ResourceLocation getTexture() {
        return new ResourceLocation("textures/entity/turtle/"+name+".png");
    }
    
    public int getId() {
        return this.ordinal();
    }
    
    public static TurtleVariant getVariant(int id) {
        return TurtleVariant.values()[id];
    }
}
