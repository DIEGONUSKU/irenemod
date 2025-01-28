package net.irene.amorcito.event;

import net.irene.amorcito.Amorcito;
import net.irene.amorcito.entity.client.PiruModel;
import net.irene.amorcito.particle.ModParticles;
import net.irene.amorcito.particle.custom.HeartParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Amorcito.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.HEART_PARTICLES.get(),
                HeartParticles.Provider::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PiruModel.LAYER_LOCATION, PiruModel::createBodyLayer);
    }
}
