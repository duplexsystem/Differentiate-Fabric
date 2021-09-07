package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class PlanksBlock extends Block implements IFlammableBlock {
	protected final boolean isNetherWood;
	
	public PlanksBlock(Settings settings) {
		this(settings, false);
	}
	
	public PlanksBlock(Settings settings, boolean isNetherWood) {
		super(settings);
		this.isNetherWood = isNetherWood;
	}

	@Override
	public int getEncouragement() {
		return this.isNetherWood ? 0 : FlammableChance.PLANKS.getLeft();
	}

	@Override
	public int getFlammability() {
		return this.isNetherWood ? 0 : FlammableChance.PLANKS.getRight();
	}
	
	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), Items.WARPED_PLANKS, group, items);
	}
}