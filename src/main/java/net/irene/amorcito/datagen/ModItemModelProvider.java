package net.irene.amorcito.datagen;

import net.irene.amorcito.Amorcito;
import net.irene.amorcito.block.ModBlocks;
import net.irene.amorcito.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Amorcito.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.DEEP_HEART.get());
        basicItem(ModItems.HEART_FRAGMENT.get());
        basicItem(ModItems.HEART_CORE.get());
        basicItem(ModItems.PURIFIED_DEEP_HEART.get());
        basicItem(ModItems.RIBBON_HEART.get());

        simpleBlockItem(ModBlocks.KALANCHOE);

        withExistingParent(ModItems.PIRU_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Amorcito.MODID,"item/" + item.getId().getPath()));
    }
}
