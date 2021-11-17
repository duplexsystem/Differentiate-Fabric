package co.eltrut.differentiate.core.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class GroupUtil {
    public static int getIndex(Item item, DefaultedList<ItemStack> items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }

    public static void fillItem(Item item, Item followItem, ItemGroup group, DefaultedList<ItemStack> items) {
        int index = getIndex(followItem, items);
        if (index != -1) {
            items.add(++index, new ItemStack(item));
        } else {
            items.add(new ItemStack(item));
        }
    }

    public static Item.Settings getProps(ItemGroup group, String... mods) {
        return CompactUtil.areModsLoaded(mods) ? new Item.Settings().group(group) : new Item.Settings().group(null);
    }

    public static class Groups {
        public static final ItemGroup SLABS = ItemGroup.BUILDING_BLOCKS;
        public static final ItemGroup STAIRS = ItemGroup.BUILDING_BLOCKS;
        public static final ItemGroup WALLS = ItemGroup.DECORATIONS;

        public static Item.Settings getSlabProps(String... mods) {
            return GroupUtil.getProps(SLABS, mods);
        }

        public static Item.Settings getStairProps(String... mods) {
            return GroupUtil.getProps(STAIRS, mods);
        }

        public static Item.Settings getWallProps(String... mods) {
            return GroupUtil.getProps(WALLS, mods);
        }
    }
}