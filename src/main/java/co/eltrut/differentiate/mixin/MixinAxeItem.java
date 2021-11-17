package co.eltrut.differentiate.mixin;

import co.eltrut.differentiate.common.block.wood.WoodType;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.Map;

@Mixin(AxeItem.class)
public class MixinAxeItem {
    @Shadow
    @Final
    @Mutable
    protected static Map<Block, Block> STRIPPED_BLOCKS;

    static {
        STRIPPED_BLOCKS = new HashMap<>(STRIPPED_BLOCKS);
        for (WoodType woodType : WoodType.woodTypes) {
            if (!woodType.compat.hasExistingVanillaSupport()) {
                STRIPPED_BLOCKS.put(woodType.strippedLog, woodType.log);
                STRIPPED_BLOCKS.put(woodType.strippedWood, woodType.wood);
            }
            STRIPPED_BLOCKS.put(woodType.strippedWoodSlab, woodType.woodSlab);
            STRIPPED_BLOCKS.put(woodType.strippedWoodStairs, woodType.woodStairs);
            STRIPPED_BLOCKS.put(woodType.strippedWoodWall, woodType.woodWall);
        }
    }
}
