//package net.irene.amorcito.datagen;
//
//import net.irene.amorcito.Amorcito;
//import net.irene.amorcito.entity.ModEntities;
//import net.minecraft.core.HolderLookup;
//import net.minecraft.data.PackOutput;
//import net.minecraft.data.tags.EntityTypeTagsProvider;
//import net.minecraft.tags.EntityTypeTags;
//import net.minecraft.tags.TagKey;
//import net.minecraft.world.entity.EntityType;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import java.util.concurrent.CompletableFuture;
//
//public class ModEntityTagsProvider extends EntityTypeTagsProvider {
//
//    public ModEntityTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider) {
//        super(pOutput, pProvider);
//    }
//
//    @Override
//    protected void addTags(HolderLookup.Provider pProvider) {
//        this.tag(EntityTypeTags.FALL_DAMAGE_IMMUNE)
//                .add(ModEntities.PIRU.get());
//        TagKey<EntityType<?>> spawnInVillage = TagKey.create(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), Amorcito.MODID, "spawn_in_village");
//
//        this.tag(spawnInVillage)
//                .add(ModEntities.PIRU.get())
//                .replace(false);
//    }
//}
