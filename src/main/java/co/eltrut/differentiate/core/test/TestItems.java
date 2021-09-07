package co.eltrut.differentiate.core.test;

import co.eltrut.differentiate.core.Differentiate;
import co.eltrut.differentiate.core.registrator.ItemHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class TestItems {
    private static final ItemHelper HELPER = Differentiate.REGISTRATOR.getHelper(Registry.ITEM);

    public static final Item TEST_ITEM = HELPER.createSimpleItem("test_item", ItemGroup.BUILDING_BLOCKS);
}