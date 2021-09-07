package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFlammableBlock;
import co.eltrut.differentiate.core.util.DataUtil.FlammableChance;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LogStairBlock extends StairsBlock implements IFlammableBlock {
	protected final boolean isNetherWood;
	protected final Block strippedBlock;

	public LogStairBlock(BlockState state, Settings settings) {
		this(null, state, settings);
	}
	
	public LogStairBlock(BlockState state, Settings settings, boolean isNetherWood) {
		this(null, state, settings, isNetherWood);
	}
	
	public LogStairBlock(Block strippedBlock, BlockState state, Settings settings) {
		this(strippedBlock, state, settings, false);
	}
	
	public LogStairBlock(Block strippedBlock, BlockState state, Settings settings, boolean isNetherWood) {
		super(state, settings);
		this.strippedBlock = strippedBlock;
		this.isNetherWood = isNetherWood;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack heldStack = player.getEquippedStack(hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

		if(heldStack.isEmpty()) {
			return ActionResult.FAIL;
		}

		Item held = heldStack.getItem();
		if(!(held instanceof MiningToolItem)) {
			return ActionResult.FAIL;
		}

		MiningToolItem tool = (MiningToolItem) held;

		if(strippedBlock != null && (tool.isSuitableFor(state) || tool.getMiningSpeedMultiplier(heldStack, state) > 1.0F)) {
			world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);

			if(!world.isClient) {
				BlockState target = strippedBlock.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS));

				world.setBlockState(pos, target);

				heldStack.damage(1, player, consumedPlayer -> consumedPlayer.sendToolBreakStatus(hand));
			}

			return ActionResult.SUCCESS;
		}

		return ActionResult.FAIL;
	}

	@Override
	public int getEncouragement() {
		return this.isNetherWood ? 0 : FlammableChance.WOOD.getLeft();
	}

	@Override
	public int getFlammability() {
		return this.isNetherWood ? 0 : FlammableChance.WOOD.getRight();
	}
}