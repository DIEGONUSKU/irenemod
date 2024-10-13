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
        breadAnimationState.animateWhen(isOrderedToSit(), this.tickCount);
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

    @Override
    protected void updateWalkAnimation(float pPartialTick)  {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 4f, 1f);
        } else {
            f = 0f;
        }

        this.piruWalkAnimation.update(f, 0.25f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.1, Ingredient.of(Items.CHICKEN), false));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0, 10.0F, 5.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.15));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 5f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

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
            this.setOrderedToSit(true);
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
