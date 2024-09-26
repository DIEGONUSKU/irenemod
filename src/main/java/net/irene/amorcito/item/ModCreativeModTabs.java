package net.irene.amorcito.item;

import net.irene.amorcito.Amorcito;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Amorcito.MODID);

    public static final RegistryObject<CreativeModeTab> AMORCITO_TAB = CREATIVE_MODE_TABS.register("amorcito_tab",
    () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.BOWTIEHEART.get()))
            .title(Component.translatable("creativetab.amorcito_tab"))
            .displayItems(((pParameters, pOutput) -> {
                pOutput.accept(ModItems.BOWTIEHEART.get());
                pOutput.accept(ModItems.DEEPHEART.get());
                pOutput.accept(ModItems.HEARTCORE.get());
                pOutput.accept(ModItems.HEARTFRAGMENT.get());
                pOutput.accept(ModItems.PURIFIEDDEEPHEART.get());
            }))
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
