package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFuel;
import co.eltrut.differentiate.core.util.DataUtil;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SignItem;
import net.minecraft.util.collection.DefaultedList;

public class WoodSignItem extends SignItem implements IFuel {
    protected final boolean burnable;

    public WoodSignItem(Settings settings, Block block, Block block2, boolean isNetherWood) {
        super(settings, block, block2);
        burnable = isNetherWood;
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
        if (this.isIn(group)) {
            GroupUtil.fillItem(this.asItem(), Items.WARPED_SIGN, group, items);
        }
    }

    @Override
    public int getBurnTime() {
        return burnable ? DataUtil.BurnTime.SIGN : 0;
    }
}
