package co.eltrut.differentiate.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractBlock.Settings.class)
public interface AbstractBlock$SettingsAccessor {
    @Accessor("material")
    @Mutable
    Material getMaterial();
}
