package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.core.util.GroupUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class ItemHelper extends AbstractHelper {

    public ItemHelper(Registrator parent) {
        super(parent);
    }

    public Item createItem(String name, Item item) {
        return Registry.register(Registry.ITEM, this.parent.id(name), item);
    }

    public Item createSimpleItem(String name, ItemGroup group, String... mods) {
        return this.createItem(name, new Item(GroupUtil.getProps(group, mods)));
    }
}