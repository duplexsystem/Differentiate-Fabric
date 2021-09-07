package co.eltrut.differentiate.core.test;

import co.eltrut.differentiate.core.Differentiate;
import co.eltrut.differentiate.core.registrator.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.util.registry.Registry;

public class TestBlocks {
    private static final BlockHelper HELPER = Differentiate.REGISTRATOR.getHelper(Registry.BLOCK);

    public static final Block TEST_BLOCK = HELPER.createSimpleBlock("test_block", new Block(Settings.NORMAL), ItemGroup.BUILDING_BLOCKS);

    public static class Settings {
        public static FabricBlockSettings NORMAL = FabricBlockSettings.copyOf(Blocks.STONE).requiresTool().breakByTool(FabricToolTags.PICKAXES, 0);
    }
}