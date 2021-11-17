package co.eltrut.differentiate.mixin;

import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SignType.class)
public interface SignTypeRegisterInvoker {
    @Invoker
    static SignType invokeRegister(SignType type) {
        throw new AssertionError();
    }
}
