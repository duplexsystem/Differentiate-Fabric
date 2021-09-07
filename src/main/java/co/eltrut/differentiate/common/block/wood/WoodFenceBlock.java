package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.block.FenceBlock;

public class WoodFenceBlock extends FenceBlock implements IFlammableBlock {
	protected final boolean isNetherWood;
	
	public WoodFenceBlock(Settings settings) {
		this(settings, false);
	}
	
	public WoodFenceBlock(Settings settings, boolean isNetherWood) {
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
}