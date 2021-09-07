package co.eltrut.differentiate.core.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.Item;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class BlockUtil {
	public static BlockState transferAllBlockStates(BlockState initial, BlockState end) {
		BlockState state = end;

		for (Property property : initial.getProperties()) {
			state = state.with(property, initial.get(property));
		}
		return state;
	}

	public static void registerDispenserBehavior(Item item, Block block, DispenserBehavior newBehavior) {
		DispenserBehavior oldBehavior = DispenserBlock.BEHAVIORS.get(item);
		DispenserBlock.registerBehavior(item, (source, stack) -> {
			Direction dir = source.getBlockState().get(DispenserBlock.FACING);
			BlockPos pos = source.getPos().offset(dir);
			BlockState state = source.getWorld().getBlockState(pos);

			return state.isOf(block) ? newBehavior.dispense(source, stack) : oldBehavior.dispense(source, stack);
		});
	}
}