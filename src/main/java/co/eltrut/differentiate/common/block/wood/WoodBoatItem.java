package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFuel;
import co.eltrut.differentiate.core.util.DataUtil;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class WoodBoatItem extends BoatItem implements IFuel {
    protected final Boolean burnable;

    public WoodBoatItem(BoatEntity.Type type, Settings settings, boolean isNetherWood) {
        super(type, settings);
        burnable = isNetherWood;
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
        if (this.isIn(group)) {
            GroupUtil.fillItem(this.asItem(), Items.DARK_OAK_BOAT, group, items);
        }
    }

    @Override
    public int getBurnTime() {
        return burnable ? DataUtil.BurnTime.BOAT : 0;
    }
}
