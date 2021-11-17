package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.common.repo.VariantBlocks;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class BlockHelper extends AbstractHelper {
    protected final ItemHelper itemRegister;

    public BlockHelper(Registrator parent) {
        super(parent);
        itemRegister = this.parent.getHelper(Registrator.Helper.ITEM);
    }

    public Block createBlock(String name, Block block, Item.Settings props) {
        Block registeredBlock = Registry.register(Registry.BLOCK, this.parent.id(name), block);
        this.itemRegister.createItem(name, new BlockItem(registeredBlock, props));

        return registeredBlock;
    }

    public Block createSimpleBlock(String name, Block block, ItemGroup group, String... mods) {
        return this.createBlock(name, block, GroupUtil.getProps(group, mods));
    }

    public Block createBlockWithoutItem(String name, Block block) {
        Block registeredBlock = Registry.register(Registry.BLOCK, this.parent.id(name), block);
        return registeredBlock;
    }

    public VariantBlocks createSimpleBlockWithVariants(String name, Block block, AbstractBlock.Settings props, ItemGroup group, String... mods) {
        Block baseBlock = this.createSimpleBlock(name, block, group, mods);
        Block slabBlock = this.createSlabBlock(name, props, mods);
        Block stairsBlock = this.createStairsBlock(name, new StairsBlock(baseBlock.getDefaultState(), props), mods);
        Block wallBlock = this.createWallBlock(name, props, mods);
        return new VariantBlocks.Builder().setBlock(baseBlock).setSlabBlock(slabBlock).setStairsBlock(stairsBlock).setWallBlock(wallBlock).build();
    }

    public VariantBlocks createSimpleBlockWithVariants(String name, AbstractBlock.Settings props, ItemGroup group, String... mods) {
        return this.createSimpleBlockWithVariants(name, new Block(props), props, group, mods);
    }

    public Block createSlabBlock(String name, AbstractBlock.Settings props, String... mods) {
        String prefix = name.endsWith("bricks") || name.endsWith("tiles") ? name.replace("_bricks", "_brick").replace("_tiles", "_tile") : name;
        return this.createSimpleBlock(prefix + "_slab", new SlabBlock(props), ItemGroup.BUILDING_BLOCKS, mods);
    }

    public Block createStairsBlock(String name, Block block, String... mods) {
        String prefix = name.endsWith("bricks") || name.endsWith("tiles") ? name.replace("_bricks", "_brick").replace("_tiles", "_tile") : name;
        return this.createSimpleBlock(prefix + "_stairs", block, ItemGroup.BUILDING_BLOCKS, mods);
    }

    public Block createWallBlock(String name, AbstractBlock.Settings props, String... mods) {
        String prefix = name.endsWith("bricks") || name.endsWith("tiles") ? name.replace("_bricks", "_brick").replace("_tiles", "_tile") : name;
        return this.createSimpleBlock(prefix + "_wall", new WallBlock(props), ItemGroup.DECORATIONS, mods);
    }
}