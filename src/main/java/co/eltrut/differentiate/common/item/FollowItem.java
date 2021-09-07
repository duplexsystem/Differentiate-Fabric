package co.eltrut.differentiate.common.item;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class FollowItem extends Item {
	private final Item followItem;

	public FollowItem(Settings settings, Item followItem) {
		super(settings);
		this.followItem = followItem;
	}
	
	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
		GroupUtil.fillItem(this.asItem(), this.followItem, group, items);
	}
}