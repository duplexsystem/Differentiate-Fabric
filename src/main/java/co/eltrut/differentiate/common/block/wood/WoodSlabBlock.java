package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.common.interf.IFuel;
import co.eltrut.differentiate.core.util.DataUtil;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import co.eltrut.differentiate.core.util.GroupUtil;
import co.eltrut.differentiate.mixin.AbstractBlock$SettingsAccessor;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class WoodSlabBlock extends SlabBlock implements IFlammableBlock, IFuel {
    protected final Boolean burnable;

    public WoodSlabBlock(Settings settings) {
        super(settings);
        burnable = ((AbstractBlock$SettingsAccessor) settings).getMaterial().isBurnable();
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
        GroupUtil.fillItem(this.asItem(), Items.WARPED_SLAB, group, items);
    }

    @Override
    public int getEncouragement() {
        return burnable ? FlammableChance.PLANKS.getLeft() : 0;
    }

    @Override
    public int getFlammability() {
        return burnable ? DataUtil.FlammableChance.PLANKS.getRight() : 0;
    }

    @Override
    public int getBurnTime() {
        return burnable ? DataUtil.BurnTime.SLAB : 0;
    }
}