package co.eltrut.differentiate.mixin;

import co.eltrut.differentiate.common.block.wood.WoodType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class MixinBlockEntityType {
    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
    private void supports(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (BlockEntityType.SIGN.equals(BlockEntityType.class.cast(this))) {
            for (WoodType woodType : WoodType.woodTypes) {
                if (!woodType.compat.hasExistingVanillaSupport())
                    if (state.getBlock().equals(woodType.sign) || state.getBlock().equals(woodType.wallSign)) {
                        cir.setReturnValue(true);
                    }
            }
        }
    }
}