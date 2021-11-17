package co.eltrut.differentiate.core;

import co.eltrut.differentiate.common.block.wood.WoodTypeEarlyProxy;
import co.eltrut.differentiate.core.util.WoodTypeInitializer;
import com.chocohead.mm.api.ClassTinkerers;
import com.chocohead.mm.api.EnumAdder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

import java.util.List;
import java.util.Locale;

public class DifferentiateEarlyRiser implements Runnable {
    @Override
    public void run() {
        FabricLoader.getInstance().getEntrypoints("differentiate:woodType_initializer", WoodTypeInitializer.class).forEach(nameList -> WoodTypeEarlyProxy.woodTypeNames.addAll(List.of(nameList.initNames())));

        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        String boat = remapper.mapClassName("intermediary", "net.minecraft.class_1690$class_1692");
        String block = 'L' + remapper.mapClassName("intermediary", "net.minecraft.class_2248") + ';';

        EnumAdder enumBuilder = ClassTinkerers.enumBuilder(boat, block, "Ljava/lang/String;");

        for (String woodName : WoodTypeEarlyProxy.woodTypeNames) {
            System.out.println("test");
            enumBuilder.addEnum(woodName.toUpperCase(Locale.ROOT), () -> new Object[]{null, woodName});
        }

        enumBuilder.build();
        System.out.println("test3");
    }
}
