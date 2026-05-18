package snowy893.snowysvariants.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snowy893.snowysvariants.mixinapi.ITurtle;

@Mixin(Turtle.class)
abstract class TurtleMixin extends Animal implements ITurtle {
    @Shadow public abstract void readAdditionalSaveData(CompoundTag pCompound);
    
    @Unique private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(TurtleMixin.class, EntityDataSerializers.INT);
    
    protected TurtleMixin(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    
    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    protected void define(CallbackInfo ci) {
        this.entityData.define(VARIANT, 0);
    }
    
    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    public void add(CompoundTag pCompound, CallbackInfo ci) {
        pCompound.putInt("Variant", 0);
    }
    
    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    public void read(CompoundTag pCompound, CallbackInfo ci) {
        this.entityData.set(VARIANT, pCompound.getInt("Variant"));
    }
    
    @Inject(method = "finalizeSpawn", at = @At("HEAD"))
    public void finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, SpawnGroupData pSpawnData, CompoundTag pDataTag, CallbackInfoReturnable<SpawnGroupData> cir) {
        if (pReason != MobSpawnType.BREEDING)
            this.entityData.set(VARIANT, Math.random() < 0.5 ? 1 : 0);
    }
    
    @Inject(method = "getBreedOffspring", at = @At("HEAD"))
    public void offspring(ServerLevel pLevel, AgeableMob pOtherParent, CallbackInfoReturnable<AgeableMob> cir) {
        Turtle turtle = EntityType.TURTLE.create(pLevel);
        if (turtle != null && pOtherParent instanceof Turtle turtle1) {
            int parentVariant;
            int babyVariant = 0;
            if (pLevel.random.nextBoolean())
                parentVariant = (((ITurtle) (Object)this).getVariant());
            else
                parentVariant = ((ITurtle) turtle1).getVariant();
            if (parentVariant == 1) babyVariant = Math.random() < 0.5 ? parentVariant : 0;
            turtle.getEntityData().set(VARIANT, babyVariant);
        }
    }
    
    @Override
    public int getVariant() {
        return this.entityData.get(VARIANT);
    }
}
