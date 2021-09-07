package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.common.interf.*;
import co.eltrut.differentiate.core.util.DataUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Registrator {
	private static final Logger LOGGER = LogManager.getLogger();
	public static final List<Registrator> REGISTRATORS = new ArrayList<>();
	
	private final String modid;
	private final Map<Registry<?>, IHelper> helpers = new HashMap<>();
	
	public Registrator(String modid) {
		this.modid = modid;
		REGISTRATORS.add(this);
		
		this.helpers.put(Registry.ITEM, new ItemHelper(this));
		this.helpers.put(Registry.BLOCK, new BlockHelper(this));
		this.helpers.put(Registry.BLOCK_ENTITY_TYPE, new BlockEntityHelper(this));
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
		registerAttributeBlock(Registry.BLOCK, ICompostableItem.class, Registrator::registerCompostable);
		registerAttributeItem(Registry.ITEM, ICompostableItem.class, Registrator::registerCompostable);
		LOGGER.info("Registered block and item compostables");

		registerAttributeBlock(Registry.BLOCK, IFlammableBlock.class, Registrator::registerFlammable);
		LOGGER.info("Registered block flammables");
	}
	
	public static void registerClient() {
		registerAttributeBlock(Registry.BLOCK, IRenderTypeBlock.class, Registrator::registerCutout);
		LOGGER.info("Registered block cutouts");

		registerAttributeBlock(Registry.BLOCK, IColoredBlock.class, Registrator::registerBlockColor);
		registerAttributeItem(Registry.ITEM, IColoredItem.class, Registrator::registerItemColor);
		LOGGER.info("Registered block and item colors");
	}
	
	public String getModId() {
		return this.modid;
	}

	public Identifier id(String path) {
		return new Identifier(this.modid, path);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends IHelper> T getHelper(Registry<?> registry) {
		return (T) this.helpers.get(registry);
	}
	
	public Map<Registry<?>, IHelper> getHelpers() {
		return this.helpers;
	}

	public static void registerAttributeItem(Registry<Item> registry, Class<? extends Interface> clazz, Consumer<ItemConvertible> consumer) {
		registry.stream().filter(clazz::isInstance).forEach(consumer);
	}

	public static void registerAttributeBlock(Registry<Block> registry, Class<? extends Interface> clazz, Consumer<Block> consumer) {
		registry.stream().filter(clazz::isInstance).forEach(consumer);
	}
	
	private static void registerCompostable(ItemConvertible item) {
		ICompostableItem compostableItem = (ICompostableItem)item;
		DataUtil.registerCompostable(item, compostableItem.getCompostableChance());
	}
	
	private static void registerFlammable(Block block) {
		IFlammableBlock flammableBlock = (IFlammableBlock)block;
		DataUtil.registerFlammable(block, flammableBlock.getEncouragement(), flammableBlock.getFlammability());
	}
	
	private static void registerCutout(Block block) {
		IRenderTypeBlock renderTypeBlock = (IRenderTypeBlock)block;
		DataUtil.registerCutout(block, renderTypeBlock.getRenderType());
	}
	
	private static void registerBlockColor(Block block) {
		IColoredBlock coloredBlock = (IColoredBlock)block;
		DataUtil.registerBlockColor(coloredBlock.getBlockColor(), block);
		DataUtil.registerItemColor(coloredBlock.getItemColor(), block);
	}
	
	private static void registerItemColor(ItemConvertible item) {
		IColoredItem coloredItem = (IColoredItem)item;
		DataUtil.registerItemColor(coloredItem.getItemColor(), item);
	}
}