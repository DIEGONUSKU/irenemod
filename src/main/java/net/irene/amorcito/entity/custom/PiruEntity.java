package net.irene.amorcito.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class PiruEntity extends Animal {
    public PiruEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setUpAnimationStates();
        }
    }

    private void setUpAnimationStates() {
        if(idleAnimationTimeout<= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6f, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.25f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.2, Ingredient.of(Items.CHICKEN), false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.15));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 5f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean isFood(@NotNull ItemStack pStack) {
        return pStack.is(Items.CHICKEN);
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.1f)
                .add(Attributes.FOLLOW_RANGE, 18D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.CAT_HURT;
    }

    @Override
    protected @org.jetbrains.annotations.Nullable SoundEvent getDeathSound() {
        return SoundEvents.CAT_DEATH;
    }

    @Override
    protected @org.jetbrains.annotations.Nullable SoundEvent getAmbientSound() {
        return SoundEvents.CAT_AMBIENT;
    }
}
