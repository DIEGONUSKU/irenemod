package net.irene.amorcito.item;

import net.irene.amorcito.Amorcito;
import net.irene.amorcito.item.custom.RibbonHeartItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Amorcito.MODID);

    public static final RegistryObject<Item> RIBBONHEART = ITEMS.register("ribbon_heart",
            () -> new RibbonHeartItem(new Item.Properties()
                    .durability(1)
                    .stacksTo(1)));

    public static final RegistryObject<Item> DEEPHEART = ITEMS.register("deep_heart",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEARTFRAGMENT = ITEMS.register("heart_fragment",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> HEARTCORE = ITEMS.register("heart_core",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PURIFIEDDEEPHEART = ITEMS.register("purified_deep_heart",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
