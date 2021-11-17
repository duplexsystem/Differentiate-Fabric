package co.eltrut.differentiate.common.block.wood;

import co.eltrut.differentiate.common.type.DifferSignType;
import co.eltrut.differentiate.core.Differentiate;
import co.eltrut.differentiate.core.registrator.BlockHelper;
import co.eltrut.differentiate.core.registrator.ItemHelper;
import co.eltrut.differentiate.core.registrator.Registrator;
import co.eltrut.differentiate.core.registrator.ResourceHelper;
import co.eltrut.differentiate.mixin.BoatEntity$TypeAccessor;
import co.eltrut.differentiate.mixin.SignTypeRegisterInvoker;
import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Pair;
import net.minecraft.util.SignType;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;


/*
 * Originated (but heavily modified) from Terrestria
 */

public class WoodType {
    public static CopyOnWriteArrayList<WoodType> woodTypes = new CopyOnWriteArrayList<>();
    private final String MOD_ID;
    public WoodPillarBlock strippedLog;
    public WoodPillarBlock strippedWood;
    public WoodPillarBlock log;
    public WoodPillarBlock wood;
    public WoodPlanksBlock planks;
    public WoodSlabBlock slab;
    public WoodStairsBlock stairs;
    public WoodFenceBlock fence;
    public WoodFenceGateBlock fenceGate;
    public WoodDoorBlock door;
    public WoodButtonBlock button;
    public WoodPressurePlateBlock pressurePlate;
    public SignBlock sign;
    public WallSignBlock wallSign;
    public WoodSignItem signItem;
    public WoodTrapdoorBlock trapdoor;
    public WoodBoatItem boat;
    public BoatEntity.Type boatType;
    public LogSlabBlock strippedWoodSlab;
    public LogStairsBlock strippedWoodStairs;
    public LogWallBlock strippedWoodWall;
    public LogSlabBlock woodSlab;
    public LogStairsBlock woodStairs;
    public LogWallBlock woodWall;
    public String name;
    public WoodTypeColor colors;
    public WoodTypeSettings settings;
    public WoodTypeCompat compat;
    public WoodTypeProvider underride;
    public WoodTypeProvider override;

    public WoodType(String name, WoodTypeColor colors, WoodTypeSettings settings, WoodTypeCompat compat, WoodTypeProvider underride, WoodTypeProvider override, String MOD_ID) {
        this.strippedLog = null;
        this.strippedWood = null;
        this.log = null;
        this.wood = null;
        this.planks = null;
        this.slab = null;
        this.stairs = null;
        this.fence = null;
        this.fenceGate = null;
        this.door = null;
        this.button = null;
        this.pressurePlate = null;
        this.sign = null;
        this.wallSign = null;
        this.signItem = null;
        this.trapdoor = null;
        this.strippedWoodSlab = null;
        this.strippedWoodStairs = null;
        this.strippedWoodWall = null;
        this.woodSlab = null;
        this.woodStairs = null;
        this.woodWall = null;
        this.name = name;
        this.colors = colors;
        this.settings = settings;
        this.compat = compat;
        this.underride = underride;
        this.override = override;
        this.MOD_ID = MOD_ID;
        woodTypes.add(this);
    }

    public static WoodType create(String name, WoodTypeColor colors, WoodTypeSettings settings, WoodTypeCompat compat, WoodTypeProvider underride, WoodTypeProvider override, String MOD_ID) {
        return new WoodType(name, colors, settings, compat, underride, override, MOD_ID);
    }

    private static WoodPillarBlock createLogBlock(MapColor topMapColor, MapColor sideMapColor, boolean isNetherWood, Item itemToAppend) {
        return new WoodPillarBlock(AbstractBlock.Settings.of(isNetherWood ? Material.NETHER_WOOD : Material.WOOD, (state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sounds(isNetherWood ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD), itemToAppend);
    }

    public WoodType generate() {
        underride.applyProvier(this);

        if (!compat.hasExistingVanillaSupport()) {
            strippedLog = createLogBlock(colors.plankColor(), colors.plankColor(), settings.isNetherWood(), Items.STRIPPED_WARPED_STEM);
            strippedWood = createLogBlock(colors.plankColor(), colors.plankColor(), settings.isNetherWood(), Items.STRIPPED_WARPED_HYPHAE);

            log = createLogBlock(colors.plankColor(), colors.logColor(), settings.isNetherWood(), Items.WARPED_STEM);
            wood = createLogBlock(colors.logColor(), colors.logColor(), settings.isNetherWood(), Items.WARPED_HYPHAE);

            planks = new WoodPlanksBlock(FabricBlockSettings.copyOf(settings.isNetherWood() ? Blocks.WARPED_PLANKS : Blocks.OAK_PLANKS).mapColor(colors.plankColor()));
            slab = new WoodSlabBlock(FabricBlockSettings.copyOf(settings.isNetherWood() ? Blocks.WARPED_SLAB : Blocks.OAK_SLAB).mapColor(colors.plankColor()));
            stairs = new WoodStairsBlock(planks.getDefaultState(), FabricBlockSettings.copyOf(settings.isNetherWood() ? Blocks.WARPED_STAIRS : Blocks.OAK_STAIRS).mapColor(colors.plankColor()));
            fence = new WoodFenceBlock(FabricBlockSettings.copyOf(settings.isNetherWood() ? Blocks.WARPED_FENCE : Blocks.OAK_FENCE).mapColor(colors.plankColor()));
            fenceGate = new WoodFenceGateBlock(FabricBlockSettings.copyOf(settings.isNetherWood() ? Blocks.WARPED_FENCE_GATE : Blocks.OAK_FENCE_GATE).mapColor(colors.plankColor()));
            door = new WoodDoorBlock(FabricBlockSettings.copyOf(settings.isNetherWood() ? Blocks.WARPED_DOOR : Blocks.OAK_DOOR).mapColor(colors.plankColor()));
            button = new WoodButtonBlock(FabricBlockSettings.copyOf(settings.isNetherWood() ? Blocks.WARPED_BUTTON : Blocks.OAK_BUTTON).mapColor(colors.plankColor()));
            pressurePlate = new WoodPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(settings.isNetherWood() ? Blocks.WARPED_PRESSURE_PLATE : Blocks.OAK_PRESSURE_PLATE).mapColor(colors.plankColor()));
            trapdoor = new WoodTrapdoorBlock(FabricBlockSettings.copyOf(settings.isNetherWood() ? Blocks.WARPED_TRAPDOOR : Blocks.OAK_TRAPDOOR).mapColor(colors.plankColor()));

            SignType signType = SignTypeRegisterInvoker.invokeRegister(new DifferSignType(name));
            sign = new SignBlock(AbstractBlock.Settings.of(settings.isNetherWood() ? Material.NETHER_WOOD : Material.WOOD).noCollision().strength(1.0F).sounds(settings.isNetherWood() ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD), signType);
            wallSign = new WallSignBlock(AbstractBlock.Settings.of(settings.isNetherWood() ? Material.NETHER_WOOD : Material.WOOD).noCollision().strength(1.0F).sounds(settings.isNetherWood() ? BlockSoundGroup.NETHER_STEM : BlockSoundGroup.WOOD), signType);
            signItem = new WoodSignItem(new Item.Settings().maxCount(16).group(ItemGroup.DECORATIONS), sign, wallSign, settings.isNetherWood());

            boatType = ClassTinkerers.getEnum(BoatEntity.Type.class, name.toUpperCase(Locale.ROOT));
            boat = new WoodBoatItem(boatType, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION), settings.isNetherWood());
            ((BoatEntity$TypeAccessor) (Object) boatType).setbaseBlock(planks);
        }

        strippedWoodSlab = new LogSlabBlock(FabricBlockSettings.copyOf(strippedLog).mapColor(colors.plankColor()));
        strippedWoodStairs = new LogStairsBlock(strippedLog.getDefaultState(), FabricBlockSettings.copyOf(strippedLog).mapColor(colors.plankColor()));
        strippedWoodWall = new LogWallBlock(FabricBlockSettings.copyOf(strippedLog).mapColor(colors.plankColor()));

        woodSlab = new LogSlabBlock(FabricBlockSettings.copyOf(slab).mapColor(colors.logColor()));
        woodStairs = new LogStairsBlock(log.getDefaultState(), FabricBlockSettings.copyOf(stairs).mapColor(colors.logColor()));
        woodWall = new LogWallBlock(FabricBlockSettings.copyOf(log).mapColor(colors.logColor()));

        override.applyProvier(this);
        return this;
    }

    public String getLogName(boolean isWood) {
        String mushroomName = isWood ? "hyphae" : "stem";
        String treeName = isWood ? "log" : "wood";
        return settings.isMushroomWood() ? mushroomName : treeName;
    }

    public WoodType register() {
        BlockHelper blockHelper = Differentiate.getRegistrator(MOD_ID).getHelper(Registrator.Helper.BLOCK);
        ItemHelper itemHelper = Differentiate.getRegistrator(MOD_ID).getHelper(Registrator.Helper.ITEM);

        if (!compat.hasExistingVanillaSupport()) {
            blockHelper.createBlock("stripped_" + name + "_" + getLogName(false), strippedLog, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            blockHelper.createBlock("stripped_" + name + "_" + getLogName(true), strippedWood, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            blockHelper.createBlock(name + "_" + getLogName(false), log, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            blockHelper.createBlock(name + "_" + getLogName(true), wood, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

            blockHelper.createBlock(name + "_planks", planks, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            blockHelper.createBlock(name + "_slab", slab, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            blockHelper.createBlock(name + "_stairs", stairs, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
            blockHelper.createBlock(name + "_fence", fence, new FabricItemSettings().group(ItemGroup.DECORATIONS));
            blockHelper.createBlock(name + "_fence_gate", fenceGate, new FabricItemSettings().group(ItemGroup.REDSTONE));
            blockHelper.createBlock(name + "_door", door, new FabricItemSettings().group(ItemGroup.REDSTONE));
            blockHelper.createBlock(name + "_button", button, new FabricItemSettings().group(ItemGroup.REDSTONE));
            blockHelper.createBlock(name + "_pressure_plate", pressurePlate, new FabricItemSettings().group(ItemGroup.REDSTONE));
            blockHelper.createBlock(name + "_trapdoor", trapdoor, new FabricItemSettings().group(ItemGroup.REDSTONE));

            blockHelper.createBlockWithoutItem(name + "_sign", sign);
            blockHelper.createBlockWithoutItem(name + "_wall_sign", wallSign);

            itemHelper.createItem(name + "_sign", this.signItem);

            itemHelper.createItem(name + "_boat", boat);
        }

        blockHelper.createBlock("stripped_" + name + "_" + getLogName(false) + "_slab", strippedWoodSlab, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
        blockHelper.createBlock("stripped_" + name + "_" + getLogName(false) + "_stairs", strippedWoodStairs, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
        blockHelper.createBlock("stripped_" + name + "_" + getLogName(false) + "_wall", strippedWoodWall, new FabricItemSettings().group(ItemGroup.DECORATIONS));
        blockHelper.createBlock(name + "_" + getLogName(false) + "_slab", woodSlab, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
        blockHelper.createBlock(name + "_" + getLogName(false) + "_stairs", woodStairs, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));
        blockHelper.createBlock(name + "_" + getLogName(false) + "_wall", woodWall, new FabricItemSettings().group(ItemGroup.DECORATIONS));

        return this;
    }

    public void generateResources() {
        ResourceHelper resourceHelper = Differentiate.getRegistrator(MOD_ID).getHelper(Registrator.Helper.RESOURCE);

        if (!compat.hasExistingVanillaSupport()) {
            resourceHelper.addTag("blocks/" + name + "_" + getLogName(false), Registry.BLOCK.getId(log));
            resourceHelper.addTag("blocks/" + name + "_" + getLogName(false), Registry.BLOCK.getId(wood));
            resourceHelper.addTag("blocks/" + name + "_" + getLogName(false), Registry.BLOCK.getId(strippedLog));
            resourceHelper.addTag("blocks/" + name + "_" + getLogName(false), Registry.BLOCK.getId(strippedWood));

            if (settings.isNetherWood()) {
                resourceHelper.addMinecraftTag("blocks/logs", name + "_" + getLogName(false));
            } else {
                resourceHelper.addTag("blocks/logs_that_burn", name + "_" + getLogName(false));
            }

            resourceHelper.addMinecraftTag("blocks/fence_gates", Registry.BLOCK.getId(fenceGate));

            resourceHelper.addMinecraftTag("blocks/planks", Registry.BLOCK.getId(planks));

            resourceHelper.addMinecraftTag("blocks/standing_signs", Registry.BLOCK.getId(sign));
            resourceHelper.addMinecraftTag("blocks/wall_signs", Registry.BLOCK.getId(wallSign));

            resourceHelper.addMinecraftTag("blocks/wooden_buttons", Registry.BLOCK.getId(button));

            resourceHelper.addMinecraftTag("blocks/wooden_doors", Registry.BLOCK.getId(door));

            resourceHelper.addMinecraftTag("blocks/wooden_fences", Registry.BLOCK.getId(fence));

            resourceHelper.addMinecraftTag("blocks/wooden_pressure_plates", Registry.BLOCK.getId(pressurePlate));

            resourceHelper.addMinecraftTag("blocks/wooden_slabs", Registry.BLOCK.getId(slab));

            resourceHelper.addMinecraftTag("blocks/wooden_stairs", Registry.BLOCK.getId(stairs));

            resourceHelper.addMinecraftTag("blocks/wooden_trapdoor", Registry.BLOCK.getId(trapdoor));


            resourceHelper.addTag("items/" + name + "_" + getLogName(false), Registry.ITEM.getId(log.asItem()));
            resourceHelper.addTag("items/" + name + "_" + getLogName(false), Registry.ITEM.getId(wood.asItem()));
            resourceHelper.addTag("items/" + name + "_" + getLogName(false), Registry.ITEM.getId(strippedLog.asItem()));
            resourceHelper.addTag("items/" + name + "_" + getLogName(false), Registry.ITEM.getId(strippedWood.asItem()));

            if (settings.isNetherWood()) {
                resourceHelper.addMinecraftTag("items/logs", name + "_" + getLogName(false));
            } else {
                resourceHelper.addMinecraftTag("items/logs_that_burn", name + "_" + getLogName(false));
            }

            resourceHelper.addMinecraftTag("items/boats", Registry.ITEM.getId(boat));

            resourceHelper.addMinecraftTag("items/signs", Registry.ITEM.getId(signItem));

            resourceHelper.addMinecraftTag("items/planks", Registry.ITEM.getId(planks.asItem()));

            resourceHelper.addMinecraftTag("items/wooden_buttons", Registry.ITEM.getId(button.asItem()));

            resourceHelper.addMinecraftTag("items/wooden_doors", Registry.ITEM.getId(door.asItem()));

            resourceHelper.addMinecraftTag("items/wooden_fences", Registry.ITEM.getId(fence.asItem()));

            resourceHelper.addMinecraftTag("items/wooden_pressure_plates", Registry.ITEM.getId(pressurePlate.asItem()));

            resourceHelper.addMinecraftTag("items/wooden_slabs", Registry.ITEM.getId(slab.asItem()));

            resourceHelper.addMinecraftTag("items/wooden_stairs", Registry.ITEM.getId(stairs.asItem()));

            resourceHelper.addMinecraftTag("blocks/wooden_trapdoor", Registry.ITEM.getId(trapdoor.asItem()));


            resourceHelper.addWoodRecipe(name + "_" + getLogName(true), log, wood);
            resourceHelper.addWoodRecipe("stripped_" + name + "_" + getLogName(true), strippedLog, strippedWood);


            resourceHelper.addPlankRecipe(name + "_planks", name + "_" + getLogName(false), planks);

            resourceHelper.addSlabRecipe(name + "_slab", planks, slab);

            resourceHelper.addStairsRecipe(name + "_stairs", planks, stairs);

            resourceHelper.shapedDoubleBlock(name + "_fence", planks.asItem(), Items.STICK, fence.asItem(), 3,
                    "#x#",
                    "#x#");

            resourceHelper.shapedDoubleBlock(name + "_fence_gate", planks.asItem(), Items.STICK, fenceGate.asItem(), 1,
                    "x#x",
                    "x#x");

            resourceHelper.shapedSingleBlock(name + "_door", planks.asItem(), door.asItem(), 3,
                    "##",
                    "##",
                    "##");

            resourceHelper.shapelessSingleBlock(name + "_button", planks.asItem(), button.asItem(), 1);

            resourceHelper.shapedSingleBlock(name + "_pressure_plate", planks.asItem(), pressurePlate.asItem(), 1,
                    "##");

            resourceHelper.shapedSingleBlock(name + "_trapdoor", planks.asItem(), trapdoor.asItem(), 2,
                    "###",
                    "###");

            resourceHelper.shapedDoubleBlock(name + "_sign", planks.asItem(), Items.STICK, sign.asItem(), 1,
                    "###",
                    "###",
                    " x ");

            resourceHelper.shapedSingleBlock(name + "_boat", planks.asItem(), boat, 1,
                    "# #",
                    "###");


            resourceHelper.defaultBlockLoot("stripped_" + name + "_" + getLogName(false), strippedLog);

            resourceHelper.defaultBlockLoot("stripped_" + name + "_" + getLogName(true), strippedWood);

            resourceHelper.defaultBlockLoot(name + "_" + getLogName(false), log);

            resourceHelper.defaultBlockLoot(name + "_" + getLogName(true), wood);

            resourceHelper.defaultBlockLoot(name + "_planks", planks);

            resourceHelper.slabBlockLoot(name + "_slab", slab);

            resourceHelper.defaultBlockLoot(name + "_stairs", stairs);

            resourceHelper.defaultBlockLoot(name + "_fence", fence);

            resourceHelper.defaultBlockLoot(name + "_fence_gate", fenceGate);

            resourceHelper.doorBlockLoot(name + "_door", door);

            resourceHelper.defaultBlockLoot(name + "_button", button);

            resourceHelper.defaultBlockLoot(name + "_pressure_plate", pressurePlate);

            resourceHelper.defaultBlockLoot(name + "_trapdoor", trapdoor);
        }
        resourceHelper.addTag("blocks/slabs", Registry.BLOCK.getId(woodSlab));
        resourceHelper.addTag("blocks/slabs", Registry.BLOCK.getId(strippedWoodSlab));

        resourceHelper.addTag("blocks/stairs", Registry.BLOCK.getId(woodStairs));
        resourceHelper.addTag("blocks/stairs", Registry.BLOCK.getId(strippedWoodStairs));

        resourceHelper.addTag("blocks/walls", Registry.BLOCK.getId(woodWall));
        resourceHelper.addTag("blocks/walls", Registry.BLOCK.getId(strippedWoodWall));

        resourceHelper.addTag("items/slabs", Registry.ITEM.getId(woodSlab.asItem()));
        resourceHelper.addTag("items/slabs", Registry.ITEM.getId(strippedWoodSlab.asItem()));

        resourceHelper.addTag("items/stairs", Registry.ITEM.getId(woodStairs.asItem()));
        resourceHelper.addTag("items/stairs", Registry.ITEM.getId(strippedWoodStairs.asItem()));

        resourceHelper.addTag("items/walls", Registry.ITEM.getId(woodWall.asItem()));
        resourceHelper.addTag("items/walls", Registry.ITEM.getId(strippedWoodWall.asItem()));

        resourceHelper.addSlabRecipe(name + "_" + getLogName(false) + "_slab", wood, woodSlab);
        resourceHelper.addSlabRecipe("stripped_" + name + "_" + getLogName(false) + "_slab", strippedWood, strippedWoodSlab);

        resourceHelper.addStairsRecipe(name + "_" + getLogName(false) + "_stairs", wood, woodStairs);
        resourceHelper.addStairsRecipe("stripped_" + name + "_" + getLogName(false) + "_stairs", strippedWood, strippedWoodStairs);

        resourceHelper.addWallRecipe(name + "_" + getLogName(false) + "_wall", wood, woodWall);
        resourceHelper.addWallRecipe("stripped_" + name + "_" + getLogName(false) + "_wall", strippedWood, strippedWoodWall);


        resourceHelper.slabBlockLoot(name + "_" + getLogName(false) + "_slab", woodSlab);
        resourceHelper.slabBlockLoot("stripped_" + name + "_" + getLogName(false) + "_slab", strippedWoodSlab);

        resourceHelper.defaultBlockLoot(name + "_" + getLogName(false) + "_stairs", woodStairs);
        resourceHelper.defaultBlockLoot("stripped_" + name + "_" + getLogName(false) + "_stairs", strippedWoodStairs);

        resourceHelper.defaultBlockLoot(name + "_" + getLogName(false) + "_wall", woodWall);
        resourceHelper.defaultBlockLoot("stripped_" + name + "_" + getLogName(false) + "_wall", strippedWoodWall);
    }

    public void generateResourceClient() {
        ResourceHelper resourceHelper = Differentiate.getRegistrator(MOD_ID).getHelper(Registrator.Helper.RESOURCE);

        if(!compat.hasExistingVanillaSupport()) {
            System.out.println("test");

            //might want to refractor to have less duplicate code
            resourceHelper.defaultBlockItemModelPair("stripped_" + name + "_" + getLogName(false), "minecraft:block/cube_column",
                    List.of(new Pair("end", "stripped_" + name + "_" + getLogName(false) + "_top"),
                            new Pair("side", "stripped_" + name + "_" + getLogName(false))));
            resourceHelper.defaultBlockItemModelPair("stripped_" + name + "_" + getLogName(false), "minecraft:block/cube_column_horizontal",
                    List.of(new Pair("end", "stripped_" + name + "_" + getLogName(false) + "_top"),
                            new Pair("side", "stripped_" + name + "_" + getLogName(false))));

            resourceHelper.defaultBlockItemModelPair("stripped_" + name + "_" + getLogName(true), "minecraft:block/cube_column",
                    List.of(new Pair("end", "stripped_" + name + "_" + getLogName(false)),
                            new Pair("side", "stripped_" + name + "_" + getLogName(false))));

            resourceHelper.defaultBlockItemModelPair(name + "_" + getLogName(false), "minecraft:block/cube_column",
                    List.of(new Pair("end", name + "_" + getLogName(false) + "_top"),
                            new Pair("side", name + "_" + getLogName(false))));
            resourceHelper.defaultBlockItemModelPair(name + "_" + getLogName(false), "minecraft:block/cube_column_horizontal",
                    List.of(new Pair("end", name + "_" + getLogName(false) + "_top"),
                            new Pair("side", name + "_" + getLogName(false))));

            resourceHelper.defaultBlockItemModelPair(name + "_" + getLogName(true), "minecraft:block/cube_column",
                    List.of(new Pair("end", name + "_" + getLogName(false)),
                            new Pair("side", name + "_" + getLogName(false))));

            resourceHelper.defaultBlockItemModelPair(name + "_planks", "minecraft:block/cube_all",
                    List.of(new Pair("all", name + "_planks")));

            resourceHelper.defaultBlockItemModelPair(name + "_slab", "minecraft:block/slab",
                    List.of(new Pair("bottom", name + "_planks"),
                            new Pair("top", name + "_planks"),
                            new Pair("side", name + "_planks")));
            resourceHelper.defaultBlockModel(name + "_slab_top", "minecraft:block/slab_top",
                    List.of(new Pair("bottom", name + "_planks"),
                            new Pair("top", name + "_planks"),
                            new Pair("side", name + "_planks")));

            resourceHelper.defaultBlockItemModelPair(name + "_stairs", "minecraft:block/stairs",
                    List.of(new Pair("bottom", name + "_planks"),
                            new Pair("top", name + "_planks"),
                            new Pair("side", name + "_planks")));
            resourceHelper.defaultBlockModel(name + "_stairs_inner", "minecraft:block/inner_stairs",
                    List.of(new Pair("bottom", name + "_planks"),
                            new Pair("top", name + "_planks"),
                            new Pair("side", name + "_planks")));
            resourceHelper.defaultBlockModel(name + "_stairs_outer", "minecraft:block/outer_stairs",
                    List.of(new Pair("bottom", name + "_planks"),
                            new Pair("top", name + "_planks"),
                            new Pair("side", name + "_planks")));

            resourceHelper.defaultBlockItemModelPair(name + "_fence_inventory", "minecraft:block/fence_inventory",
                    List.of(new Pair("texture", name + "_planks")));
            resourceHelper.defaultBlockModel(name + "_fence_post", "minecraft:block/fence_inventory",
                    List.of(new Pair("texture", name + "_planks")));
            resourceHelper.defaultBlockModel(name + "_fence_side", "minecraft:block/fence_inventory",
                    List.of(new Pair("texture", name + "_planks")));
            resourceHelper.defaultBlockItemModelPair(name + "_fence_gate", "minecraft:block/template_fence_gate",
                    List.of(new Pair("texture", name + "_planks")));
            resourceHelper.defaultBlockModel(name + "_fence_gate_open", "minecraft:block/template_fence_gate_open",
                    List.of(new Pair("texture", name + "_planks")));
            resourceHelper.defaultBlockModel(name + "_fence_gate_wall", "minecraft:block/template_fence_gate_wall",
                    List.of(new Pair("texture", name + "_planks")));
            resourceHelper.defaultBlockModel(name + "_fence_gate_wall_open", "minecraft:block/template_fence_gate_wall_open",
                    List.of(new Pair("texture", name + "_planks")));

            resourceHelper.defaultBlockModel(name + "_door_bottom", "minecraft:block/door_bottom",
                    List.of(new Pair("top", name + "_door_top"),
                            new Pair("bottom", name + "_door_bottom")));
            resourceHelper.defaultBlockModel(name + "_door_bottom_hinge", "minecraft:block/door_bottom_rh",
                    List.of(new Pair("top", name + "_door_top"),
                            new Pair("bottom", name + "_door_bottom")));
            resourceHelper.defaultBlockModel(name + "_door_top", "minecraft:block/door_top",
                    List.of(new Pair("top", name + "_door_top"),
                            new Pair("bottom", name + "_door_bottom")));
            resourceHelper.defaultBlockModel(name + "_door_top_hinge", "minecraft:block/door_top_rh",
                    List.of(new Pair("top", name + "_door_top"),
                            new Pair("bottom", name + "_door_bottom")));
            resourceHelper.defaultItemModel(name + "_door", name + "_door");

            resourceHelper.defaultBlockItemModelPair(name + "_button_inventory", "minecraft:block/button_inventory",
                    List.of(new Pair("texture", name + "_planks")));
            resourceHelper.defaultBlockModel(name + "_button", "minecraft:block/button",
                    List.of(new Pair("texture", name + "_planks")));
            resourceHelper.defaultBlockModel(name + "_button_pressed", "minecraft:block/button_pressed",
                    List.of(new Pair("texture", name + "_planks")));

            resourceHelper.defaultBlockItemModelPair(name + "_pressure_plate", "minecraft:block/pressure_plate_up",
                    List.of(new Pair("texture", name + "_planks")));
            resourceHelper.defaultBlockModel(name + "_pressure_plate_down", "minecraft:block/pressure_plate_down",
                    List.of(new Pair("texture", name + "_planks")));

            resourceHelper.defaultBlockItemModelPair(name + "_trapdoor_bottom", "minecraft:block/template_orientable_trapdoor_bottom",
                    List.of(new Pair("texture", name + "_trapdoor")));
            resourceHelper.defaultBlockModel(name + "_trapdoor_open", "minecraft:block/template_orientable_trapdoor_open",
                    List.of(new Pair("texture", name + "_trapdoor")));
            resourceHelper.defaultBlockModel(name + "_trapdoor_top", "minecraft:block/template_orientable_trapdoor_top",
                    List.of(new Pair("texture", name + "_trapdoor")));

            resourceHelper.noParentBlockModel(name + "_sign",
                    List.of(new Pair("particle", name + "_planks")));
            resourceHelper.defaultItemModel(name + "_sign", name + "_sign");

            resourceHelper.defaultItemModel(name + "boat", name + "_boat");


            resourceHelper.pillarBlockState("stripped_" + name + "_" + getLogName(false));
            resourceHelper.pillarBlockState("stripped_" + name + "_" + getLogName(true));

            resourceHelper.pillarBlockState(name + "_" + getLogName(false));
            resourceHelper.pillarBlockState(name + "_" + getLogName(true));

            resourceHelper.defaultBlockState(name + "_planks", "");

            resourceHelper.slabBlockState(name + "_slab", name + "_planks");

            resourceHelper.stairsBlockState(name + "_stairs");

            resourceHelper.fenceBlockState(name + "_fence");

            resourceHelper.fenceGateBlockState(name + "_fence_gate");

            resourceHelper.doorBlockState(name + "_door");
        }

        resourceHelper.defaultBlockItemModelPair(name + "_" + getLogName(false) + "_slab", "minecraft:block/slab",
                List.of(new Pair("bottom", name + "_" + getLogName(false)),
                        new Pair("top", name + "_" + getLogName(false)),
                        new Pair("side", name + "_" + getLogName(false))));
        resourceHelper.defaultBlockModel(name + "_" + getLogName(false) + "_slab_top", "minecraft:block/slab_top",
                List.of(new Pair("bottom", name + "_" + getLogName(false)),
                        new Pair("top", name + "_" + getLogName(false)),
                        new Pair("side", name + "_" + getLogName(false))));
        resourceHelper.defaultBlockItemModelPair("stripped_" + name + "_" + getLogName(false) + "_slab", "minecraft:block/slab",
                List.of(new Pair("bottom", "stripped_" + name + "_" + getLogName(false)),
                        new Pair("top", "stripped_" + name + "_" + getLogName(false)),
                        new Pair("side", "stripped_" + name + "_" + getLogName(false))));
        resourceHelper.defaultBlockModel(name + "_" + getLogName(false) + "_slab_top", "minecraft:block/slab_top",
                List.of(new Pair("bottom", "stripped_" + name + "_" + getLogName(false)),
                        new Pair("top", "stripped_" + name + "_" + getLogName(false)),
                        new Pair("side", "stripped_" + name + "_" + getLogName(false))));

        resourceHelper.defaultBlockItemModelPair(name + "_" + getLogName(false) + "_stairs", "minecraft:block/stairs",
                List.of(new Pair("bottom", name + "_" + getLogName(false)),
                        new Pair("top", name + "_" + getLogName(false)),
                        new Pair("side", name + "_" + getLogName(false))));
        resourceHelper.defaultBlockModel(name + "_" + getLogName(false) + "_stairs_inner", "minecraft:block/inner_stairs",
                List.of(new Pair("bottom", name + "_" + getLogName(false)),
                        new Pair("top", name + "_" + getLogName(false)),
                        new Pair("side", name + "_" + getLogName(false))));
        resourceHelper.defaultBlockModel(name + "_" + getLogName(false) + "_stairs_outer", "minecraft:block/outer_stairs",
                List.of(new Pair("bottom", name + "_" + getLogName(false)),
                        new Pair("top", name + "_" + getLogName(false)),
                        new Pair("side", name + "_" + getLogName(false))));
        resourceHelper.defaultBlockItemModelPair("stripped_" + name + "_" + getLogName(false) + "_stairs", "minecraft:block/stairs",
                List.of(new Pair("bottom", "stripped_" + name + "_" + getLogName(false)),
                        new Pair("top", "stripped_" + name + "_" + getLogName(false)),
                        new Pair("side", "stripped_" + name + "_" + getLogName(false))));
        resourceHelper.defaultBlockModel("stripped_" + name + "_" + getLogName(false) + "_stairs_inner", "minecraft:block/inner_stairs",
                List.of(new Pair("bottom", "stripped_" + name + "_" + getLogName(false)),
                        new Pair("top", "stripped_" + name + "_" + getLogName(false)),
                        new Pair("side", "stripped_" + name + "_" + getLogName(false))));
        resourceHelper.defaultBlockModel("stripped_" + name + "_" + getLogName(false) + "_stairs_outer", "minecraft:block/outer_stairs",
                List.of(new Pair("bottom", "stripped_" + name + "_" + getLogName(false)),
                        new Pair("top", "stripped_" + name + "_" + getLogName(false)),
                        new Pair("side", "stripped_" + name + "_" + getLogName(false))));

        resourceHelper.defaultBlockItemModelPair(name + "_wall_inventory", "minecraft:block/wall_inventory",
                List.of(new Pair("texture", name + getLogName(false))));
        resourceHelper.defaultBlockModel(name + "_wall_post", "minecraft:block/wall_post",
                List.of(new Pair("texture", name + getLogName(false))));
        resourceHelper.defaultBlockModel(name + "_will_side", "minecraft:block/will_side",
                List.of(new Pair("texture", name + getLogName(false))));
        resourceHelper.defaultBlockModel(name + "_will_side_tall", "minecraft:block/will_side_tall",
                List.of(new Pair("texture", name + getLogName(false))));
        resourceHelper.defaultBlockItemModelPair("stripped_" + name + "_wall_inventory", "minecraft:block/wall_inventory",
                List.of(new Pair("texture", "stripped_" + name + getLogName(false))));
        resourceHelper.defaultBlockModel("stripped_" + name + "_wall_post", "minecraft:block/wall_post",
                List.of(new Pair("texture", "stripped_" + name + getLogName(false))));
        resourceHelper.defaultBlockModel("stripped_" + name + "_will_side", "minecraft:block/will_side",
                List.of(new Pair("texture", "stripped_" + name + getLogName(false))));
        resourceHelper.defaultBlockModel("stripped_" + name + "_will_side_tall", "minecraft:block/will_side_tall",
                List.of(new Pair("texture", "stripped_" + name + getLogName(false))));
    }

    public record WoodTypeColor(MapColor logColor, MapColor plankColor) {
    }

    public record WoodTypeSettings(boolean isNetherWood, boolean isMushroomWood) {
    }

    public record WoodTypeCompat(boolean hasExistingVanillaSupport, boolean hasBookshelfSupport,
                                 boolean hasChestSupport, boolean hasLadderSupport, boolean hasBeehiveSupport,
                                 boolean hasPantriesSupport) {
    }
}
