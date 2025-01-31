package net.irene.amorcito.entity;

import net.irene.amorcito.Amorcito;
import net.irene.amorcito.entity.custom.PiruEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Amorcito.MODID);


    public static final RegistryObject<EntityType<PiruEntity>> PIRU =
            ENTITY_TYPES.register("piru", () -> EntityType.Builder.of(PiruEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.8f).build("piru"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
