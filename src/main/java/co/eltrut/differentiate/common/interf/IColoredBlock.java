package co.eltrut.differentiate.common.interf;

import net.minecraft.client.color.block.BlockColorProvider;

public interface IColoredBlock extends IColoredItem {
    BlockColorProvider getBlockColor();
}