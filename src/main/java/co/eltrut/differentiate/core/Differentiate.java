package co.eltrut.differentiate.core;

import co.eltrut.differentiate.core.registrator.Registrator;
import co.eltrut.differentiate.core.test.TestBlocks;
import co.eltrut.differentiate.core.test.TestItems;
import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Differentiate  implements ModInitializer {
    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_NAME = "Differentiate";
    public static final String MOD_ID = "differentiate";

    public static final Registrator REGISTRATOR = new Registrator(MOD_ID);

    protected boolean test = false;

    @Override
    public void onInitialize() {
        if (test) {
            Reflection.initialize(
                    TestBlocks.class,
                    TestItems.class
            );
        }

        Registrator.registerCommon();

        log(Level.INFO, "Differentiating!");
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}