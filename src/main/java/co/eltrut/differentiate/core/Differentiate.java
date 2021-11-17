package co.eltrut.differentiate.core;

import co.eltrut.differentiate.common.block.wood.WoodType;
import co.eltrut.differentiate.core.registrator.Registrator;
import co.eltrut.differentiate.core.test.TestBlocks;
import co.eltrut.differentiate.core.test.TestItems;
import co.eltrut.differentiate.core.util.WoodTypeInitializer;
import com.google.common.reflect.Reflection;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

public class Differentiate implements ModInitializer {
    public static final String MOD_NAME = "Differentiate";
    public static final String MOD_ID = "differentiate";
    public static final Registrator REGISTRATOR = new Registrator(MOD_ID);
    public static final ConcurrentHashMap<String, Registrator> registratorMap = new ConcurrentHashMap();
    public static Logger LOGGER = LogManager.getLogger();
    protected boolean test = false;

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

    public static Registrator getRegistrator(String MOD_ID) {
        return registratorMap.computeIfAbsent(MOD_ID, id -> new Registrator(id));
    }

    @Override
    public void onInitialize() {
        log(Level.INFO, "Differentiating!");

        if (test) {
            Reflection.initialize(
                    TestBlocks.class,
                    TestItems.class
            );
        }

        log(Level.INFO, "Initializing Wood Types!");
        FabricLoader.getInstance().getEntrypoints("differentiate:woodType_initializer", WoodTypeInitializer.class).forEach(WoodTypeInitializer::initWoodSet);

        log(Level.INFO, "Generating Wood Types!");
        for (WoodType woodType : WoodType.woodTypes) {
            woodType.generate();
        }

        log(Level.INFO, "Registering Wood Types!");
        for (WoodType woodType : WoodType.woodTypes) {
            woodType.register();
        }

        Registrator.registerCommon();

        log(Level.INFO, "Generating Wood Type Resources!");
        for (WoodType woodType : WoodType.woodTypes) {
            woodType.generateResources();
            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                woodType.generateResourceClient();
            }
        }

        Registrator.registerCommonLate();
    }
}