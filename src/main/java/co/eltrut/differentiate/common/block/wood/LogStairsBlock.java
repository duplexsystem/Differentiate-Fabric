package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.common.interf.IFuel;
import co.eltrut.differentiate.core.util.DataUtil;
import co.eltrut.differentiate.core.util.GroupUtil;
import co.eltrut.differentiate.mixin.AbstractBlock$SettingsAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class LogStairsBlock extends StairsBlock implements IFlammableBlock, IFuel {
    protected final Boolean burnable;

    public LogStairsBlock(BlockState state, Settings settings) {
        super(state, settings);
        burnable = ((AbstractBlock$SettingsAccessor) settings).getMaterial().isBurnable();
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
        GroupUtil.fillItem(this.asItem(), Items.WARPED_STAIRS, group, items);
    }

    @Override
    public int getEncouragement() {
        return burnable ? DataUtil.FlammableChance.WOOD.getLeft() : 0;
    }

    @Override
    public int getFlammability() {
        return burnable ? DataUtil.FlammableChance.WOOD.getRight() : 0;
    }

    @Override
    public int getBurnTime() {
        return burnable ? DataUtil.BurnTime.WOOD : 0;
    }
}