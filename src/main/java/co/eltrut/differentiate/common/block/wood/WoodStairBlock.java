package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class WoodStairBlock extends StairsBlock implements IFlammableBlock {
	protected final boolean isNetherWood;
	
	public WoodStairBlock(BlockState state, Settings settings) {
		this(state, settings, false);
	}
	
	public WoodStairBlock(BlockState state, Settings settings, boolean isNetherWood) {
		super(state, settings);
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
}