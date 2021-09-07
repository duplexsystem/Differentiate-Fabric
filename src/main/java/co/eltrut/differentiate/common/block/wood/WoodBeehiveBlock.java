package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.block.BeehiveBlock;

public class WoodBeehiveBlock extends BeehiveBlock implements IFlammableBlock {
	protected final boolean isNetherWood;
	
	public WoodBeehiveBlock(Settings settings) {
		this(settings, false);
	}
	
	public WoodBeehiveBlock(Settings settings, boolean isNetherWood) {
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