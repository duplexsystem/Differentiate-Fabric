package co.eltrut.differentiate.mixin;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.Settings.class)
public interface Item$SettingsAccessor {
    @Accessor("fireproof")
    @Mutable
    boolean getFireproof();
}