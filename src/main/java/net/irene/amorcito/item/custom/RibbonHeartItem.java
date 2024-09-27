package net.irene.amorcito.item.custom;

import net.irene.amorcito.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;

import static net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH;

public class RibbonHeartItem extends Item {

    public RibbonHeartItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        if(!(Objects.requireNonNull(pPlayer.getAttribute(MAX_HEALTH)).getValue() >= 40.0)) {

            BlockPos soundOrigin = this.getSoundOrigin(pPlayer);

            float volume = 1f;
            float pitch = 0.5f;

            pLevel.playSound(pPlayer, soundOrigin, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, volume, pitch);

            this.addParticles(pPlayer, pLevel);
        }

        if(!pLevel.isClientSide() && pUsedHand == InteractionHand.MAIN_HAND){

            double playerMaxHealth = Objects.requireNonNull(pPlayer.getAttribute(MAX_HEALTH)).getValue();

            Objects.requireNonNull(pPlayer.getAttribute(MAX_HEALTH)).setBaseValue(operateItemUsage(playerMaxHealth, pPlayer, pLevel));

            return super.use(pLevel, pPlayer, pUsedHand);

        } else {

            return super.use(pLevel, pPlayer, pUsedHand);

        }
    }

    public double operateItemUsage(double currentMaxHealth, Player pPlayer, Level pLevel) {

        if (currentMaxHealth >= 40.0) {

            return 40.0;

        } else {

            pPlayer.getMainHandItem().hurtAndBreak(1, pPlayer, EquipmentSlot.MAINHAND);
            return currentMaxHealth + 2.0;

        }
    }

    private BlockPos getSoundOrigin(Player pPlayer) {
        int playerXpos;
        int playerYpos;
        int playerZpos;

        playerXpos = (int) Math.round(pPlayer.position().x);
        playerYpos = (int) Math.round(pPlayer.position().y);
        playerZpos = (int) Math.round(pPlayer.position().z);

        return new BlockPos(playerXpos, playerYpos, playerZpos);
    }

    private void addParticles(Player pPlayer, Level pLevel) {
        double playerXpos = pPlayer.position().x;
        double playerYpos = pPlayer.position().y;
        double playerZpos = pPlayer.position().z;

        for (int i = 0; i < 720; i++) {
            if (i % 15 == 0) {
                pLevel.addParticle(ModParticles.HEART_PARTICLES.get(),
                        playerXpos,
                        playerYpos,
                        playerZpos,
                        Math.cos(i) * 0.4d,
                        i/270d,
                        Math.sin(i) * 0.4d);
            }
        }
    }
}
