package net.irene.amorcito;

import com.mojang.logging.LogUtils;
import net.irene.amorcito.block.ModBlocks;
import net.irene.amorcito.entity.ModEntities;
import net.irene.amorcito.entity.client.PiruRenderer;
import net.irene.amorcito.item.ModCreativeModTabs;
import net.irene.amorcito.particle.ModParticles;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import net.irene.amorcito.item.ModItems;

import javax.swing.text.html.parser.Entity;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Amorcito.MODID)
public class Amorcito
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "amorcitoid";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Amorcito()
    {


        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModParticles.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.KALANCHOE.getId(), ModBlocks.POTTED_KALANCHOE);
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.RIBBON_HEART);
        } if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.DEEP_HEART);
        } if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.HEART_FRAGMENT);
        } if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.HEART_CORE);
        }if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.PURIFIED_DEEP_HEART);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup (FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.PIRU.get(), PiruRenderer::new);
        }
    }
}
