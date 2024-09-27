package net.irene.amorcito.event;

import net.irene.amorcito.Amorcito;
import net.irene.amorcito.particle.ModParticles;
import net.irene.amorcito.particle.custom.HeartParticles;
import net.minecraft.client.Minecraft;
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
}
