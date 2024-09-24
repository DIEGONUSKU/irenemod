package net.irene.amorcito.item;

import net.irene.amorcito.amorcito;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;

public class modItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, amorcito.MODID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
