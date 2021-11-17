package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class StonePressurePlateBlock extends PressurePlateBlock {
    public StonePressurePlateBlock(Settings settings) {
        super(ActivationRule.MOBS, settings);
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
        GroupUtil.fillItem(this.asItem(), Items.POLISHED_BLACKSTONE_PRESSURE_PLATE, group, items);
    }
}