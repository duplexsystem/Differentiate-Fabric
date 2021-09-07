package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class WoodPressurePlateBlock extends PressurePlateBlock {
	public WoodPressurePlateBlock(Settings settings) {
		super(PressurePlateBlock.ActivationRule.EVERYTHING, settings);
	}
	
	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.WARPED_PRESSURE_PLATE, group, items);
	}
}