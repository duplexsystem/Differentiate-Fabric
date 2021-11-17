package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.common.interf.*;
import co.eltrut.differentiate.core.util.DataUtil;
import net.devtech.arrp.api.RRPCallback;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Registrator {
    public static final List<Registrator> REGISTRATORS = new ArrayList<>();
    private static final Logger LOGGER = LogManager.getLogger();
    private final String modid;
    private final Map<Helper, IHelper> helpers = new HashMap<>();

    public Registrator(String modid) {
        this.modid = modid;
        REGISTRATORS.add(this);

        this.helpers.put(Helper.ITEM, new ItemHelper(this));
        this.helpers.put(Helper.BLOCK, new BlockHelper(this));
        this.helpers.put(Helper.BLOCK_ENTITY, new BlockEntityHelper(this));
        this.helpers.put(Helper.RESOURCE, new ResourceHelper(this));
    }

    public static Registrator create(String modid, Consumer<Registrator> consumer) {
        Registrator registrator = new Registrator(modid);
        consumer.accept(registrator);
        return registrator;
    }

//	public void register() {
//		for (IHelper helper : this.helpers.values()) {
//			helper.register();
//		}
//	}

    public static void registerCommon() {
        LOGGER.info("Registering block and item compostables!");
        registerAttributeBlock(Registry.BLOCK, ICompostableItem.class, Registrator::registerCompostable);
        registerAttributeItem(Registry.ITEM, ICompostableItem.class, Registrator::registerCompostable);

        LOGGER.info("Registering block flammables!");
        registerAttributeBlock(Registry.BLOCK, IFlammableBlock.class, Registrator::registerFlammable);

        LOGGER.info("Registering block and item fuels!");
        registerAttributeBlock(Registry.BLOCK, IFuel.class, Registrator::registerFuelBlock);
        registerAttributeItem(Registry.ITEM, IFuel.class, Registrator::registerFuelItem);

    }

    public static void registerCommonLate() {
        LOGGER.info("Registering tags!");
        registerTag(); //maybe redo this in the future??

        LOGGER.info("Registering resources!");
        registerResource(); //same here
    }

    public static void registerClient() {
        LOGGER.info("Registering block cutouts!");
        registerAttributeBlock(Registry.BLOCK, IRenderTypeBlock.class, Registrator::registerCutout);

        LOGGER.info("Registering block and item colors!");
        registerAttributeBlock(Registry.BLOCK, IColoredBlock.class, Registrator::registerBlockColor);
        registerAttributeItem(Registry.ITEM, IColoredItem.class, Registrator::registerItemColor);
    }

    public static void registerAttributeItem(Registry<Item> registry, Class<? extends Interface> clazz, Consumer<ItemConvertible> consumer) {
        registry.stream().filter(clazz::isInstance).forEach(consumer);
    }

    public static void registerAttributeBlock(Registry<Block> registry, Class<? extends Interface> clazz, Consumer<Block> consumer) {
        registry.stream().filter(clazz::isInstance).forEach(consumer);
    }

    public static void registerTag() {
        REGISTRATORS.forEach(registrator -> {
            ResourceHelper helper = (ResourceHelper) registrator.getHelpers().get(Helper.RESOURCE);
            helper.specificIdentifierTagMap.keySet().forEach(key -> helper.resourcePack.addTag(key.identifier(), helper.specificIdentifierTagMap.get(key)));
        });
    }

    public static void registerResource() {
        REGISTRATORS.forEach(registrator -> RRPCallback.BEFORE_VANILLA.register(a -> a.add(((ResourceHelper) registrator.getHelpers().get(Helper.RESOURCE)).resourcePack)));
        REGISTRATORS.forEach(registrator -> ((ResourceHelper) registrator.getHelpers().get(Helper.RESOURCE)).resourcePack.dump());

    }

    private static void registerCompostable(ItemConvertible item) {
        ICompostableItem compostableItem = (ICompostableItem) item;
        DataUtil.registerCompostable(item, compostableItem.getCompostableChance());
    }

    private static void registerFlammable(Block block) {
        IFlammableBlock flammableBlock = (IFlammableBlock) block;
        DataUtil.registerFlammable(block, flammableBlock.getEncouragement(), flammableBlock.getFlammability());
    }

    private static void registerFuelBlock(Block block) {
        IFuel fuelBlock = (IFuel) block;
        DataUtil.registerFuel(block.asItem(), fuelBlock.getBurnTime());
    }

    private static void registerFuelItem(ItemConvertible item) {
        IFuel fuelItem = (IFuel) item;
        DataUtil.registerFuel(item.asItem(), fuelItem.getBurnTime());
    }

    private static void registerTags(ItemConvertible item) {
        IFuel fuelItem = (IFuel) item;
        DataUtil.registerFuel(item.asItem(), fuelItem.getBurnTime());
    }

    private static void registerCutout(Block block) {
        IRenderTypeBlock renderTypeBlock = (IRenderTypeBlock) block;
        DataUtil.registerCutout(block, renderTypeBlock.getRenderType());
    }

    private static void registerBlockColor(Block block) {
        IColoredBlock coloredBlock = (IColoredBlock) block;
        DataUtil.registerBlockColor(coloredBlock.getBlockColor(), block);
        DataUtil.registerItemColor(coloredBlock.getItemColor(), block);
    }

    private static void registerItemColor(ItemConvertible item) {
        IColoredItem coloredItem = (IColoredItem) item;
        DataUtil.registerItemColor(coloredItem.getItemColor(), item);
    }

    public String getModId() {
        return this.modid;
    }

    public Identifier id(String path) {
        return new Identifier(this.modid, path);
    }

    @SuppressWarnings("unchecked")
    public <T extends IHelper> T getHelper(Helper helper) {
        return (T) this.helpers.get(helper);
    }

    public Map<Helper, IHelper> getHelpers() {
        return this.helpers;
    }

    public enum Helper {
        ITEM,
        BLOCK,
        BLOCK_ENTITY,
        RESOURCE
    }
}