package net.irene.amorcito.entity;

import net.irene.amorcito.Amorcito;
import net.irene.amorcito.entity.custom.PiruEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Amorcito.MODID);

    public static final RegistryObject<EntityType<PiruEntity>> PIRU =
            ENTITY_TYPES.register("piru", () -> EntityType.Builder.of(PiruEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.5f).build("piru"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
