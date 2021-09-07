package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.block.SlabBlock;

public class WoodSlabBlock extends SlabBlock implements IFlammableBlock {
	protected final boolean isNetherWood;
	
	public WoodSlabBlock(Settings settings) {
		this(settings, false);
	}
	
	public WoodSlabBlock(Settings settings, boolean isNetherWood) {
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