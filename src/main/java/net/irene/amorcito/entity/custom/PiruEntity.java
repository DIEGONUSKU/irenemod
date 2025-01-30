package net.irene.amorcito.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;
import org.jline.utils.Log;

import javax.annotation.Nullable;

public class PiruEntity extends TamableAnimal {
    public PiruEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.POWDER_SNOW, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, -1.0F);
    }

    private static final EntityDataAccessor<Byte> DATA_FLAGS_ID =SynchedEntityData.defineId(PiruEntity.class, EntityDataSerializers.BYTE);

    @Override
    protected void defineSynchedData() {

        super.defineSynchedData();
        this.entityData.define(DATA_FLAGS_ID, (byte) 0);

    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("Sitting", this.isOrderedToSit());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setOrderedToSit(pCompound.getBoolean("Sitting"));
    }

    public final AnimationState sitAnimationState = new AnimationState();

//    public final AnimationState standAnimationState = new AnimationState();

    public final AnimationState idleAnimationState = new AnimationState();

//    public final AnimationState staySitAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;

    private float limbSwingAmount;

    private float limbSwing;

    //Getters
    public float getLimbSwingAmount() {
        return this.limbSwingAmount;
    }
    public float getLimbSwing() {
        return this.limbSwing;
    }

    @Override
    public void tick() {

        super.tick();

        if(this.level().isClientSide) {

            setUpAnimationStates();

        }
    }

    private void setUpAnimationStates() {

        if (!isInSittingPose()) {

            sitAnimationState.stop();

            if (idleAnimationTimeout<= 0) {

                this.idleAnimationTimeout = this.random.nextInt(40) + 80;
                this.idleAnimationState.start(this.tickCount);

            } else {

                --this.idleAnimationTimeout;

            }
        } else {

            this.sitAnimationState.startIfStopped(this.tickCount);

        }
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.1, Ingredient.of(Items.CHICKEN), false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.15));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 5f));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0, 10.0F, 5.0F, false));

    }

    //Interact with raw chicken, resulting in tame, or sitting if piru has owner
    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {

        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();

        if (!this.isTame() && item == Items.CHICKEN) {

            if(!pPlayer.getAbilities().instabuild) {

                itemstack.shrink(1);

            }

            this.tryToTame(pPlayer);

            return super.mobInteract(pPlayer, pHand);

        } else if (this.isTame() && isOwnedBy(pPlayer)) {

            if (!this.level().isClientSide) {

                this.setOrderedToSit(!isOrderedToSit());

                this.jumping = false;

                this.navigation.stop();

                this.setTarget((LivingEntity)null);

            } else {

                this.sitOrStand();

            }

            return InteractionResult.SUCCESS;

        } else {

          return super.mobInteract(pPlayer, pHand);

        }
    }

    private void sitOrStand() {

            if (isInSittingPose()) {

                sitAnimationState.start(this.tickCount);

            } else {

                sitAnimationState.stop();

            }
    }

    //Tame attempt, called when interacting with raw chicken
    private void tryToTame(Player pPlayer) {

        if (this.random.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, pPlayer)) {

            this.tame(pPlayer);
            this.navigation.stop();
            this.setTarget((LivingEntity)null);
            this.setOrderedToSit(true);

            this.sitOrStand();

            this.level().broadcastEntityEvent(this, (byte)7);

        } else {

            this.level().broadcastEntityEvent(this, (byte)6);

        }
    }

    @Override
    public boolean isFood(@NotNull ItemStack pStack) {

        return pStack.is(Items.CHICKEN);

    }

    //Creates attributes
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

    //Get the sounds
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
