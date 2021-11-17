package co.eltrut.differentiate.core.registrator;

import co.eltrut.differentiate.core.Differentiate;
import com.google.gson.JsonObject;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.*;
import net.devtech.arrp.json.loot.JCondition;
import net.devtech.arrp.json.loot.JFunction;
import net.devtech.arrp.json.loot.JLootTable;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.devtech.arrp.json.recipe.*;
import net.devtech.arrp.json.tags.JTag;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class ResourceHelper extends AbstractHelper {
    public static List<Integer> list360 = List.of(0, 90, 180, 270);
    private static final List<Integer> directionList = List.of(0, 1, 2, 4);
    private static final List<Boolean> trueFalse = List.of(true, false);
    private static final JsonObject doubleTypeJsonObj = new JsonObject();
    private static final JsonObject lowerHalfJsonObj = new JsonObject();

    static {
        doubleTypeJsonObj.addProperty("type", "double");
        lowerHalfJsonObj.addProperty("half", "lower");
    }

    public RuntimeResourcePack resourcePack = RuntimeResourcePack.create(this.parent.getModId() + ":" + Differentiate.MOD_ID);
    protected ConcurrentHashMap<RuntimeResourcePackSpecificIdentifier, JTag> specificIdentifierTagMap = new ConcurrentHashMap<>();

    public ResourceHelper(Registrator parent) {
        super(parent);
    }

    private static String directionIntToString(int direction) {
        if (direction == 0) return "east";
        else if (direction == 1) return "south";
        else if (direction == 2) return "west";
        else if (direction == 3) return "north";
        return "";
    }

    private static Pair<Integer, Integer> stairFlipper(Integer direction, Boolean bottom, Boolean left) {
        if (!(bottom || left)) {
            if (direction == 3) direction = 0;
            else direction += 1;
        }

        Integer x = bottom ? 0 : 180;
        Integer y = (direction) * 90;
        return new Pair<>(x, y);
    }

    public synchronized void addTag(String name, String tag) {
        addTag(name, this.parent.id(tag));
    }

    public synchronized void addTag(String name, Identifier tag) {
        specificIdentifierTagMap.computeIfAbsent(new RuntimeResourcePackSpecificIdentifier(this.parent.id(name)), id -> JTag.tag()).add(tag);
    }

    public synchronized void addMinecraftTag(String name, String tag) {
        addMinecraftTag(name, this.parent.id(tag));
    }

    public synchronized void addMinecraftTag(String name, Identifier tag) {
        specificIdentifierTagMap.computeIfAbsent(new RuntimeResourcePackSpecificIdentifier(new Identifier(name)), id -> JTag.tag()).add(tag);
    }

    public void addWoodRecipe(String name, Block log, Block wood) {
        shapedSingleBlock(name, log.asItem(), wood.asItem(), 3,
                "## ",
                "## ");
    }

    public void addPlankRecipe(String name, String logs, Block planks) {
        shapelessSingleBlock(name, this.parent.id(logs), planks.asItem(), 4);
    }

    public void addSlabRecipe(String name, Block block, Block slab) {
        shapedSingleBlock(name, block.asItem(), slab.asItem(), 6,
                "###");
    }

    public void addStairsRecipe(String name, Block block, Block stairs) {
        shapedSingleBlock(name, block.asItem(), stairs.asItem(), 4,
                "#  ",
                "## ",
                "###");
    }

    public void addWallRecipe(String name, Block block, Block wall) {
        shapedSingleBlock(name, block.asItem(), wall.asItem(), 6,
                "###",
                "###");
    }

    public void shapelessSingleBlock(String name, Item inBlock, Item outBlock, int num) {
        resourcePack.addRecipe(this.parent.id(name), JRecipe.shapeless(
                JIngredients.ingredients()
                        .add(
                                JIngredient.ingredient()
                                        .item(inBlock)
                        ),
                JResult.itemStack(outBlock, num)
        ));
    }

    public void shapelessSingleBlock(String name, Identifier inBlocks, Item outBlock, int num) {
        resourcePack.addRecipe(this.parent.id(name), JRecipe.shapeless(
                JIngredients.ingredients()
                        .add(
                                JIngredient.ingredient()
                                        .tag(inBlocks.toString())
                        ),
                JResult.itemStack(outBlock, num)
        ));
    }

    public void shapedSingleBlock(String name, Item inBlock, Item outBlock, int num, String... shape) {
        resourcePack.addRecipe(this.parent.id(name), JRecipe.shaped(
                JPattern.pattern(
                        shape
                ),
                JKeys.keys()
                        .key("#",
                                JIngredient.ingredient()
                                        .item(inBlock.asItem())
                        ),
                JResult.itemStack(outBlock.asItem(), num)
        ));
    }

    public void shapedDoubleBlock(String name, Item inBlock1, Item inBlock2, Item outBlock, int num, String... shape) {
        resourcePack.addRecipe(this.parent.id(name), JRecipe.shaped(
                JPattern.pattern(
                        shape
                ),
                JKeys.keys()
                        .key("#",
                                JIngredient.ingredient()
                                        .item(inBlock1.asItem())
                        )
                        .key("x",
                                JIngredient.ingredient()
                                        .item(inBlock2.asItem())
                        ),
                JResult.itemStack(outBlock.asItem(), num)
        ));
    }

    public void slabBlockLoot(String name, Block block) {
        resourcePack.addLootTable(this.parent.id("blocks/" + name), JLootTable.loot("minecraft:block")
                .pool(JLootTable.pool()
                        .rolls(1)
                        .bonus(0)
                        .entry(JLootTable.entry()
                                .type("minecraft:item")
                                .function(new JFunction("minecraft:set_count")
                                        .condition(new JCondition("minecraft:block_state_property")
                                                .parameter("block", this.parent.id(name))
                                                .parameter("properties", doubleTypeJsonObj))
                                        .parameter("count", 2)
                                        .parameter("add", false))
                                .function(new JFunction("minecraft:explosion_decay"))
                                .name(this.parent.id(name).toString())

                        )));
    }

    public void doorBlockLoot(String name, Block block) {
        resourcePack.addLootTable(this.parent.id("blocks/" + name), JLootTable.loot("minecraft:block")
                .pool(JLootTable.pool()
                        .rolls(1)
                        .bonus(0)
                        .entry(JLootTable.entry()
                                .type("minecraft:item")
                                .condition(new JCondition("minecraft:block_state_property")
                                        .parameter("block", this.parent.id(name))
                                        .parameter("properties", lowerHalfJsonObj))
                                .name(this.parent.id(name).toString()))
                        .condition(new JCondition("minecraft:survives_explosion")
                        )));
    }

    public void defaultBlockLoot(String name, Block block) {
        resourcePack.addLootTable(this.parent.id("blocks/" + name), JLootTable.loot("minecraft:block")
                .pool(JLootTable.pool()
                        .rolls(1)
                        .bonus(0)
                        .entry(JLootTable.entry()
                                .type("minecraft:item")
                                .name(this.parent.id(name).toString()))
                        .condition(new JCondition("minecraft:survives_explosion")
                        )));
    }

    public void defaultBlockItemModelPair(String name, String parent, List<Pair<String, String>> texturesList) {
        defaultBlockModel(name, parent, texturesList);
        defaultBlockItemModel(name, name);
    }

    public void defaultBlockModel(String name, String parent, List<Pair<String, String>> texturesList) {
        System.out.println(this.parent.id("block/" + name));
        JTextures textures = JModel.textures();
        for (Pair<String, String> texturePair : texturesList) {
            textures.var(texturePair.getLeft(), this.parent.id("block/" + texturePair.getRight()).toString());
        }

        resourcePack.addModel(JModel.model(parent).
                        textures(textures),
                this.parent.id("block/" + name));
    }

    public void noParentBlockModel(String name, List<Pair<String, String>> texturesList) {
        JTextures textures = JModel.textures();
        for (Pair<String, String> texturePair : texturesList) {
            textures.var(texturePair.getLeft(), this.parent.id("block/" + texturePair.getRight()).toString());
        }

        resourcePack.addModel(JModel.model().
                        textures(textures),
                this.parent.id("block/" + name));
    }

    public void defaultBlockItemModel(String name, String parent) {
        resourcePack.addModel(JModel.model(this.parent.id("block/" + parent)),
                this.parent.id("item/" + name));
    }

    public void defaultItemModel(String name, String itemTexture) {
        resourcePack.addModel(
                JModel.model("minecraft:item/generated")
                        .textures(JModel.textures()
                                .layer0(this.parent.id("item/" + itemTexture).toString())
                        ),
                this.parent.id("item/" + name)
        );
    }

    public void pillarBlockState(String name) {
        System.out.println(this.parent.id("block/" + name).toString() + "pillar");
        resourcePack.addBlockState(JState.state(
                    JState.variant()
                                .put("axis=y", JState.model(this.parent.id("block/" + name)))
                                .put("axis=z", JState.model(this.parent.id("block/" + name)).x(90))
                                .put("axis=x", JState.model(this.parent.id("block/" + name)).x(90).y(90))),
                this.parent.id(name));
    }

    public void slabBlockState(String name, String planks) {
        resourcePack.addBlockState(JState.state().add(
                    JState.variant()
                            .put("type=bottom", JState.model(this.parent.id("block/" + name)))
                            .put("type=top", JState.model(this.parent.id("block/" + name + "_top")))
                            .put("type=double", JState.model(this.parent.id("block/" + planks)))),
                this.parent.id(name));
    }

    public void defaultBlockState(String name, String state) {
        resourcePack.addBlockState(JState.state(
                    JState.variant()
                            .put(state, JState.model(this.parent.id("block/" + name)))),
                this.parent.id(name));
    }

    public void stairsBlockState(String name) {
        Identifier straightID = this.parent.id( "block/" + name);
        Identifier innerID = this.parent.id( "block/" + name + "_inner");
        Identifier outerID = this.parent.id( "block/" + name + "_outer");
        // just put me out of my misery already // shamelessly stolen from RegistrARRP
        resourcePack.addBlockState(JState.state().add(
                JState.variant()
                    .put("facing=east,half=bottom,shape=inner_left", JState.model(innerID).y(270).uvlock())
                    .put("facing=east,half=bottom,shape=inner_right", JState.model(innerID))
                    .put("facing=east,half=bottom,shape=outer_left", JState.model(outerID).y(270).uvlock())
                    .put("facing=east,half=bottom,shape=outer_right", JState.model(outerID))
                    .put("facing=east,half=bottom,shape=straight", JState.model(straightID))
                    .put("facing=east,half=top,shape=inner_left", JState.model(innerID).x(180).uvlock())
                    .put("facing=east,half=top,shape=inner_right", JState.model(innerID).x(180).y(90).uvlock())
                    .put("facing=east,half=top,shape=outer_left", JState.model(outerID).x(180).uvlock())
                    .put("facing=east,half=top,shape=outer_right", JState.model(outerID).x(180).y(90).uvlock())
                    .put("facing=east,half=top,shape=straight", JState.model(straightID).x(180).uvlock())
                    .put("facing=north,half=bottom,shape=inner_left", JState.model(innerID).y(180).uvlock())
                    .put("facing=north,half=bottom,shape=inner_right", JState.model(innerID).y(270).uvlock())
                    .put("facing=north,half=bottom,shape=outer_left", JState.model(outerID).y(180).uvlock())
                    .put("facing=north,half=bottom,shape=outer_right", JState.model(outerID).y(270).uvlock())
                    .put("facing=north,half=bottom,shape=straight", JState.model(straightID).y(270).uvlock())
                    .put("facing=north,half=top,shape=inner_left", JState.model(innerID).x(180).y(270).uvlock())
                    .put("facing=north,half=top,shape=inner_right", JState.model(innerID).x(180).uvlock())
                    .put("facing=north,half=top,shape=outer_left", JState.model(outerID).x(180).y(270).uvlock())
                    .put("facing=north,half=top,shape=outer_right", JState.model(outerID).x(180).uvlock())
                    .put("facing=north,half=top,shape=straight", JState.model(straightID).x(180).y(270).uvlock())
                    .put("facing=south,half=bottom,shape=inner_left", JState.model(innerID))
                    .put("facing=south,half=bottom,shape=inner_right", JState.model(innerID).y(90).uvlock())
                    .put("facing=south,half=bottom,shape=outer_left", JState.model(outerID))
                    .put("facing=south,half=bottom,shape=outer_right", JState.model(outerID).y(90).uvlock())
                    .put("facing=south,half=bottom,shape=straight", JState.model(straightID).y(90).uvlock())
                    .put("facing=south,half=top,shape=inner_left", JState.model(innerID).x(180).y(90).uvlock())
                    .put("facing=south,half=top,shape=inner_right", JState.model(innerID).x(180).y(180).uvlock())
                    .put("facing=south,half=top,shape=outer_left", JState.model(outerID).x(180).y(90).uvlock())
                    .put("facing=south,half=top,shape=outer_right", JState.model(outerID).x(180).y(180).uvlock())
                    .put("facing=south,half=top,shape=straight", JState.model(straightID).x(180).y(90).uvlock())
                    .put("facing=west,half=bottom,shape=inner_left", JState.model(innerID).y(90).uvlock())
                    .put("facing=west,half=bottom,shape=inner_right", JState.model(innerID).y(180).uvlock())
                    .put("facing=west,half=bottom,shape=outer_left", JState.model(outerID).y(90).uvlock())
                    .put("facing=west,half=bottom,shape=outer_right", JState.model(outerID).y(180).uvlock())
                    .put("facing=west,half=bottom,shape=straight", JState.model(straightID).y(180).uvlock())
                    .put("facing=west,half=top,shape=inner_left", JState.model(innerID).x(180).y(180).uvlock())
                    .put("facing=west,half=top,shape=inner_right", JState.model(innerID).x(180).y(270).uvlock())
                    .put("facing=west,half=top,shape=outer_left", JState.model(outerID).x(180).y(180).uvlock())
                    .put("facing=west,half=top,shape=outer_right", JState.model(outerID).x(180).y(270).uvlock())
                    .put("facing=west,half=top,shape=straight", JState.model(straightID).x(180).y(180).uvlock())),
            this.getParent().id(name));
        // my brain is 🦀 now //me too
    }

    public void fenceBlockState(String name) {
        Identifier postID = this.parent.id( "block/" + name + "_post");
        Identifier sideID = this.parent.id( "block/" + name + "_side");
        resourcePack.addBlockState(JState.state()
                .add(JState.multipart().addModel(JState.model(postID)))
                .add(JState.multipart().addModel(JState.model(sideID).uvlock()).when(JState.when().add("north", "true")))
                .add(JState.multipart().addModel(JState.model(sideID).y(90).uvlock()).when(JState.when().add("east", "true")))
                .add(JState.multipart().addModel(JState.model(sideID).y(180).uvlock()).when(JState.when().add("south", "true")))
                .add(JState.multipart().addModel(JState.model(sideID).y(270).uvlock()).when(JState.when().add("west", "true"))), this.parent.id(name));
    }

    public void doorBlockState(String name) {
        resourcePack.addBlockState()
    }
//    public void stairsBlockState(String name) {
//        JVariant jVariant = new JVariant();
//        List<String> stateList = List.of("inner", "outter", "straight");
//        for (Integer direction : directionList) {
//            for (Boolean bottom : trueFalse) {
//                for (Boolean left : trueFalse) {
//                    for (String state : stateList) {
//                        Pair<Integer, Integer> xy = stairFlipper(direction, bottom, left);
//                        JBlockModel blockModel = new JBlockModel(this.parent.id("block/" + name + (state == "straight" ? "" : "_" + state)));
//                        if (!(xy.getLeft() == xy.getRight() && xy.getLeft() == 0)) {
//                            blockModel.uvlock();
//                        }
//                        if (xy.getLeft() != 0) {
//                            blockModel.x(xy.getLeft());
//                        }
//                        if (xy.getRight() != 0) {
//                            blockModel.y(xy.getRight());
//                        }
//                        jVariant.put("facing=" + directionIntToString(direction) + ",half=" + (bottom ? "bottom" : "top") + ",shape=" + state + (state == "straight" ? "" : "_" + (left ? "left" : "right")), blockModel);
//                    }
//                }
//            }
//        }
//        resourcePack.addBlockState(new JState().add(jVariant), this.parent.id(name));
//    }
//
//    public void fenceBlockState(String name) {
//        JMultipart jMultipart = new JMultipart();
//        jMultipart.addModel(new JBlockModel(this.parent.id("block/" + name + "_post")));
//
//        for (Integer direction : directionList) {
//            Integer shiftedDirection = direction;
//            if (shiftedDirection == 3) shiftedDirection = 0;
//            else shiftedDirection += 1;
//
//            JBlockModel model = new JBlockModel(this.parent.id("block/" + name + "_side"));
//            if (direction != 0) {
//                model.y(direction * 90);
//            }
//            jMultipart.addModel(model.uvlock()).when(new JWhen().add(directionIntToString(shiftedDirection), "true"));
//        }
//        resourcePack.addBlockState(new JState().add(jMultipart), this.parent.id(name));
//    }
//
//    public void fenceGateBlockState(String name) {
//        JVariant jVariant = new JVariant();
//        for (Integer direction : directionList) {
//            Integer shiftedDirection = direction;
//            if (shiftedDirection == 0) shiftedDirection = 3;
//            else shiftedDirection -= 1;
//
//            for (Boolean inWall : trueFalse) {
//                for (Boolean open : trueFalse) {
//                    jVariant.put("facing=" + directionIntToString(shiftedDirection) + ",in_wall=" + inWall.toString().toLowerCase(Locale.ROOT) + ",open=" + open.toString().toLowerCase(Locale.ROOT),
//                            new JBlockModel(this.parent.id("block/" + name + (inWall ? "_wall" : "") + (open ? "_open" : ""))).uvlock().y(direction));
//                }
//            }
//        }
//        resourcePack.addBlockState(new JState().add(jVariant), this.getParent().id(name));
//    }
//
//    public void doorBlockState(String name) {
//        JVariant jVariant = new JVariant();
//        for (Integer direction : directionList) {
//            for (Boolean bottom : trueFalse) {
//                for (Boolean left : trueFalse) {
//                    for (Boolean open : trueFalse) {
//                        Integer shiftedDirection = direction;
//                        if (open) {
//                            if (left) {
//                                if (shiftedDirection == 0) shiftedDirection = 3;
//                                else shiftedDirection -= 1;
//                            } else {
//                                if (shiftedDirection == 3) shiftedDirection = 0;
//                                else shiftedDirection += 1;
//                            }
//                        }
//                        JBlockModel jModel = new JBlockModel(this.parent.id("block/" + name + (bottom ? "_bottom" : "_top") + ((left == open) ? "_hinge" : "")));
//                        if (direction != 0) {
//                            jModel.y(direction * 90);
//                        }
//                        jVariant.put("facing=" + directionIntToString(shiftedDirection) + ",half=" + (bottom ? "lower" : "upper") + ",hinge=" + (left ? "left" : "right") + ",open=" + open.toString().toLowerCase(Locale.ROOT),
//                                jModel);
//                    }
//                }
//            }
//        }
//        resourcePack.addBlockState(new JState().add(jVariant), this.getParent().id(name));
//    }

    public void iteratingBlockState(String name, List<List<String>> stateList, List<String> modelList, List<Integer> xList, List<Integer> yList, List<Boolean> uvList) {
        List<String> statesList = new ArrayList<>();

        for (int i = 0; i < stateList.size(); i++) {
            for (int j = 0; j < stateList.get(i).size(); j++) {
                if (statesList.get(i) != null) {
                    statesList.add(i, statesList.get(i) + "," + stateList.get(i).get(j));
                } else {
                    statesList.add(i, stateList.get(i).get(j));
                }

            }
        }

        JState jstate = new JState();

        for (String state : statesList) {
            for (String model : modelList) {
                for (Integer x : xList) {
                    for (Integer y : yList) {
                        for (Boolean uv : uvList) {
                            JBlockModel blockModel = new JBlockModel(this.parent.id("block/" + model));
                            if (uv) {
                                blockModel.uvlock();
                            }
                            if (x != 0) {
                                blockModel.x(x);
                            }
                            if (y != 0) {
                                blockModel.y(y);
                            }
                            jstate.add(new JVariant().put(state, blockModel));
                        }
                    }
                }
            }
        }
        resourcePack.addBlockState(jstate, this.parent.id(name));
    }

    public record RuntimeResourcePackSpecificIdentifier(Identifier identifier) {
    }
}
