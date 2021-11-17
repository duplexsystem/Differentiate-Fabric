package co.eltrut.differentiate.common.interf;

import net.minecraft.client.color.item.ItemColorProvider;

public interface IColoredItem extends Interface {
    ItemColorProvider getItemColor();
}