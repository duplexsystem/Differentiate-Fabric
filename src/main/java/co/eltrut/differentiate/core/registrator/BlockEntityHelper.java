package co.eltrut.differentiate.core.registrator;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.HashSet;

public class BlockEntityHelper extends AbstractHelper {
	public BlockEntityHelper(Registrator parent) {
		super(parent);
	}
	
	public <T extends BlockEntity> BlockEntityType<T> createBlockEntity(String name, BlockEntityType.BlockEntityFactory<? extends T> tileEntity, Block[] blocks) {
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, this.parent.id(name), new BlockEntityType<T>(tileEntity, new HashSet<Block>(Arrays.asList(blocks)), null));
	}
}