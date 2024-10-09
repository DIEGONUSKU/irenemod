package net.irene.amorcito.datagen;

import net.irene.amorcito.Amorcito;
import net.irene.amorcito.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Amorcito.MODID, exFileHelper);
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    public ResourceLocation blockTexture(Block block) {
        return key(block).withPath(p -> ModelProvider.BLOCK_FOLDER + '/' + p);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.KALANCHOE.get(), models().cross(name(ModBlocks.KALANCHOE.get()), blockTexture(ModBlocks.KALANCHOE.get())).renderType("cutout"));
        simpleBlock(ModBlocks.POTTED_KALANCHOE.get(), models().singleTexture(name(ModBlocks.POTTED_KALANCHOE.get()),
                mcLoc("flower_pot_cross"),
                "plant",
                blockTexture(ModBlocks.KALANCHOE.get())).renderType("cutout"));

    }

    private void blockWithItem (RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
