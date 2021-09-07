package co.eltrut.differentiate.common.repo;

import net.minecraft.block.Block;

public class VariantBlocks {
	
	private final Block baseBlock;
	private final Block slabBlock;
	private final Block stairsBlock;
	private final Block wallBlock;
	
	private VariantBlocks(Block baseBlock, Block slabBlock, Block stairsBlock, Block wallBlock) {
		this.baseBlock = baseBlock;
		this.slabBlock = slabBlock;
		this.stairsBlock = stairsBlock;
		this.wallBlock = wallBlock;
	}
	
	public Block getBlock() {
		return this.baseBlock;
	}
	
	public Block getSlabBlock() {
		return this.slabBlock;
	}
	
	public Block getStairsBlock() {
		return this.stairsBlock;
	}
	
	public Block getWallBlock() {
		return this.wallBlock;
	}
	
	public static class Builder {
		
		private Block baseBlock;
		private Block slabBlock;
		private Block stairsBlock;
		private Block wallBlock;
		
		public Builder() {
			this.setAllNull();
		}
		
		public Builder setBlock(Block baseBlock) {
			this.baseBlock = baseBlock;
			return this;
		}
		
		public Builder setSlabBlock(Block slabBlock) {
			this.slabBlock = slabBlock;
			return this;
		}
		
		public Builder setStairsBlock(Block stairsBlock) {
			this.stairsBlock = stairsBlock;
			return this;
		}
		
		public Builder setWallBlock(Block wallBlock) {
			this.wallBlock = wallBlock;
			return this;
		}
		
		public VariantBlocks build() {
			return new VariantBlocks(this.baseBlock, this.slabBlock, this.stairsBlock, this.wallBlock);
		}
		
		private void setAllNull() {
			this.baseBlock = null;
			this.slabBlock = null;
			this.stairsBlock = null;
			this.wallBlock = null;
		}
		
	}

}
