package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.interf.IFuel;
import co.eltrut.differentiate.common.interf.IRenderTypeBlock;
import co.eltrut.differentiate.core.util.DataUtil;
import co.eltrut.differentiate.core.util.GroupUtil;
import co.eltrut.differentiate.mixin.AbstractBlock$SettingsAccessor;
import net.minecraft.block.DoorBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;

public class WoodDoorBlock extends DoorBlock implements IFuel, IRenderTypeBlock {
    protected final Boolean burnable;

    protected WoodDoorBlock(Settings settings) {
        super(settings);
        burnable = ((AbstractBlock$SettingsAccessor) settings).getMaterial().isBurnable();
    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> items) {
        GroupUtil.fillItem(this.asItem(), Items.WARPED_DOOR, group, items);
    }

    @Override
    public int getBurnTime() {
        return burnable ? DataUtil.BurnTime.DOOR : 0;
    }

    @Override
    public RenderLayer getRenderType() {
        return RenderLayer.getCutoutMipped();
    }
}
