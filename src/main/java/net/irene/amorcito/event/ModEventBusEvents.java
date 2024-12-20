package net.irene.amorcito.event;

import net.irene.amorcito.Amorcito;
import net.irene.amorcito.entity.ModEntities;
import net.irene.amorcito.entity.client.PiruModel;
import net.irene.amorcito.entity.custom.PiruEntity;
import net.irene.amorcito.particle.ModParticles;
import net.irene.amorcito.particle.custom.HeartParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;

@Mod.EventBusSubscriber(modid = Amorcito.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.HEART_PARTICLES.get(),
                HeartParticles.Provider::new);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PIRU.get(), PiruEntity.createAttributes().build());
    }
}
