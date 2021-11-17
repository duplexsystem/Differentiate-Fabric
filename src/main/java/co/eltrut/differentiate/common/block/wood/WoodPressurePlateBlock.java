package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFuel;
import co.eltrut.differentiate.core.util.DataUtil;
import co.eltrut.differentiate.core.util.GroupUtil;
import co.eltrut.differentiate.mixin.AbstractBlock$SettingsAccessor;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class WoodPressurePlateBlock extends PressurePlateBlock implements IFuel {
    protected final Boolean burnable;

    public WoodPressurePlateBlock(ActivationRule everything, Settings settings) {
        super(everything, settings);
        burnable = ((AbstractBlock$SettingsAccessor) settings).getMaterial().isBurnable();
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
        GroupUtil.fillItem(this.asItem(), Items.WARPED_PRESSURE_PLATE, group, items);
    }

    @Override
    public int getBurnTime() {
        return burnable ? DataUtil.BurnTime.WOOD : 0;
    }
}