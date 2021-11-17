package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.common.interf.IFuel;
import co.eltrut.differentiate.core.util.DataUtil;
import co.eltrut.differentiate.core.util.GroupUtil;
import co.eltrut.differentiate.mixin.AbstractBlock$SettingsAccessor;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class WoodPillarBlock extends PillarBlock implements IFlammableBlock, IFuel {
    protected final Boolean burnable;
    private final Item itemToAppend;

    public WoodPillarBlock(Settings settings, Item itemToAppend) {
        super(settings);
        burnable = ((AbstractBlock$SettingsAccessor) settings).getMaterial().isBurnable();
        this.itemToAppend = itemToAppend;
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
        GroupUtil.fillItem(this.asItem(), itemToAppend, group, items);

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
