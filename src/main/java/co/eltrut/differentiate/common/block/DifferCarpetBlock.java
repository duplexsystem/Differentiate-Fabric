package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.CarpetBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class DifferCarpetBlock extends CarpetBlock implements IFlammableBlock {
	public DifferCarpetBlock(Settings settings) {
		super(settings);
	}

	@Override
	public int getEncouragement() {
		return FlammableChance.CARPET.getLeft();
	}

	@Override
	public int getFlammability() {
		return FlammableChance.CARPET.getRight();
	}

	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.BLACK_CARPET, group, items);
	}
}