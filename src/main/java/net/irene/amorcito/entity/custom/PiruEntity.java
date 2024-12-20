package net.irene.amorcito.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraftforge.unsafe.UnsafeFieldAccess;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class PiruEntity extends TamableAnimal {
    public PiruEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setTame(false, false);
        this.setPathfindingMalus(PathType.POWDER_SNOW, -1.0F);
        this.setPathfindingMalus(PathType.DANGER_POWDER_SNOW, -1.0F);
    }

    public final WalkAnimationState piruWalkAnimation = new WalkAnimationState();

    public final AnimationState breadAnimationState = new AnimationState();

    public final AnimationState unBreadAnimationState = new AnimationState();

    public final AnimationState idleAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;

    private float limbSwingAmount;

    private float limbSwing;

    private boolean isMoving() {
        //Checks whether the entity is moving
        double deltaX = this.getDeltaMovement().x();
        double deltaZ = this.getDeltaMovement().z();

        // If either of the horizontal components is non-zero, the entity is moving
        return deltaX != 0 || deltaZ != 0;
    }

    public float getLimbSwingAmount() {
        return this.limbSwingAmount;
    }

    public float getLimbSwing() {
        return this.limbSwing;
    }

    @Override
    public void tick() {
        super.tick();

        if(this.isMoving()) {
            //Pass correct limbSwing to EntityModel
            double deltaX = this.getDeltaMovement().x();
            double deltaZ = this.getDeltaMovement().z();
            double velocity = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ); // Get horizontal movement velocity
            this.limbSwingAmount = (float) Math.min(velocity, 1.0);  // Clamp to a maximum value for smoothness
            this.limbSwing += 0.5F;  // Increase limbSwing with each tick when moving
        } else {
            this.limbSwingAmount = 0;
        }

        if(this.level().isClientSide()) {
            setUpAnimationStates();
        }
    }

    private void setUpAnimationStates() {
        if(isOrderedToSit()) {
            this.setPose(Pose.SITTING);
            //idleAnimationState.stop();
        } else {
            //breadAnimationState.ifStarted(AnimationState::stop);
            this.setPose(Pose.STANDING);
        }

        if (this.getPose() == Pose.STANDING) {
            if(idleAnimationTimeout<= 0) {
                this.idleAnimationTimeout = this.random.nextInt(40) + 80;
                this.idleAnimationState.start(this.tickCount);
            } else {
                --this.idleAnimationTimeout;
            }
        }
    }

//    @Override
//    protected void updateWalkAnimation(float pPartialTick)  {
//        float f;
//        f = Math.min(pPartialTick, 0.5f);
//
//        this.piruWalkAnimation.update(f, 0.25f);
//    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.1, Ingredient.of(Items.CHICKEN), false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.15));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0, 10.0F, 5.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 5f));


    }


    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (!this.isTame() && item == Items.CHICKEN) {
            itemstack.consume(1, pPlayer);
            this.tryToTame(pPlayer);
            return InteractionResult.SUCCESS;
        } else if (this.isTame() && isOwnedBy(pPlayer)) {
            if (this.getPose() == Pose.STANDING) {
                breadAnimationState.start(this.tickCount);
            } else {
                breadAnimationState.stop();
                unBreadAnimationState.start(this.tickCount);
            }

            setOrderedToSit(!isOrderedToSit());
            return InteractionResult.sidedSuccess(this.level().isClientSide());

        }
        return InteractionResult.PASS;
    }

    private void tryToTame(Player pPlayer) {
        if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, pPlayer)) {
            this.tame(pPlayer);
            this.navigation.stop();
            this.setTarget(null);
            this.level().broadcastEntityEvent(this, (byte)7);
        } else {
            this.level().broadcastEntityEvent(this, (byte)6);
        }
    }

    @Override
    public boolean isFood(@NotNull ItemStack pStack) {
        return pStack.is(Items.CHICKEN);
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.25f)
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
