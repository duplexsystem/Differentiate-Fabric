package co.eltrut.differentiate.mixin;

import co.eltrut.differentiate.common.block.wood.WoodType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public abstract class MixinBoatEntity {
    @Shadow
    public abstract BoatEntity.Type getBoatType();

    @Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
    public void addEM(CallbackInfoReturnable<Item> cir) {
        for (WoodType woodType : WoodType.woodTypes) {
            if (!woodType.compat.hasExistingVanillaSupport()) {
                if (this.getBoatType().equals(woodType.boatType)) {
                    cir.setReturnValue(woodType.boat);
                }
            }
        }
    }
}
