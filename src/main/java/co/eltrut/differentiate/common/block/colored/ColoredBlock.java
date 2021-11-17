package co.eltrut.differentiate.common.block.colored;

import co.eltrut.differentiate.common.interf.IColoredBlock;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;

public class ColoredBlock extends Block implements IColoredBlock {
    private final BlockColorProvider blockColor;
    private final ItemColorProvider itemColor;

    public ColoredBlock(Settings settings, BlockColorProvider blockColor, ItemColorProvider itemColor) {
        super(settings);
        this.blockColor = blockColor;
        this.itemColor = itemColor;
    }

    @Override
    public BlockColorProvider getBlockColor() {
        return this.blockColor;
    }

    @Override
    public ItemColorProvider getItemColor() {
        return this.itemColor;
    }
}