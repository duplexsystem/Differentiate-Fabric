package co.eltrut.differentiate.common.block;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class FollowBlock extends Block {
    private final Item followItem;

    public FollowBlock(Settings settings, Item followItem) {
        super(settings);
        this.followItem = followItem;
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
        GroupUtil.fillItem(this.asItem(), followItem, group, items);
    }
}