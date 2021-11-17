package co.eltrut.differentiate.core.util;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import org.apache.commons.lang3.tuple.Pair;

public class DataUtil {
    public static void registerCompostable(ItemConvertible item, float compostableChance) {
        CompostingChanceRegistry.INSTANCE.add(item, compostableChance);
    }

    public static void registerFlammable(Block block, int encouragement, int flammability) {
        FlammableBlockRegistry.getDefaultInstance().add(block, encouragement, flammability);
    }

    public static void registerFuel(Item item, int burnTime) {
        if (burnTime > 0) {
            FuelRegistry.INSTANCE.add(item, burnTime);
        }
    }

    public static void registerCutout(Block block, RenderLayer layer) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, layer);
    }

    public static void registerBlockColor(BlockColorProvider color, Block... blocks) {
        ColorProviderRegistry.BLOCK.register(color, blocks);
    }

    public static void registerItemColor(ItemColorProvider color, ItemConvertible... items) {
        ColorProviderRegistry.ITEM.register(color, items);
    }

    public static class CompostableChance {
        public static final float SEEDS = 0.3F;
        public static final float PLANTS = 0.65F;
        public static final float BAKED_GOODS = 0.85F;
        public static final float PIES = 1.0F;
    }

    public static class FlammableChance {
        public static final Pair<Integer, Integer> WOOD = Pair.of(5, 5);
        public static final Pair<Integer, Integer> PLANKS = Pair.of(5, 20);
        public static final Pair<Integer, Integer> BOOKSHELF = Pair.of(30, 20);
        public static final Pair<Integer, Integer> LEAVES = Pair.of(30, 60);
        public static final Pair<Integer, Integer> WOOL = Pair.of(30, 60);
        public static final Pair<Integer, Integer> CARPET = Pair.of(60, 20);
        public static final Pair<Integer, Integer> FLOWER = Pair.of(60, 100);
    }

    public static class BurnTime {
        public static final int WOOD = 300;
        public static final int SLAB = 150;
        public static final int DOOR = 200;
        public static final int BUTTON = 100;
        public static final int SIGN = 150;
        public static final int BOAT = 1200;
    }
}