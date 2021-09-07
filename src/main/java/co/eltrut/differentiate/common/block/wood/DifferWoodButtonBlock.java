package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class DifferWoodButtonBlock extends WoodenButtonBlock {
	public DifferWoodButtonBlock(Settings settings) {
		super(settings);
	}
	
	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.WARPED_BUTTON, group, items);
	}
}