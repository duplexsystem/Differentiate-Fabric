package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class WoolBlock extends Block implements IFlammableBlock {
	public WoolBlock(Settings settings) {
		super(settings);
	}

	@Override
	public int getEncouragement() {
		return FlammableChance.WOOL.getLeft();
	}

	@Override
	public int getFlammability() {
		return FlammableChance.WOOL.getRight();
	}

	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.BLACK_WOOL, group, items);
	}
}