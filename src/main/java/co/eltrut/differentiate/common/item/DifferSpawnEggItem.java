package co.eltrut.differentiate.common.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;

public class DifferSpawnEggItem extends SpawnEggItem {

    private final EntityType<?> defaultType;

    public DifferSpawnEggItem(EntityType<?> defaultType, int color1, int color2, Settings settings) {
        super(null, color1, color2, settings);
        this.defaultType = defaultType;
    }

    @Override
    public EntityType<?> getEntityType(@Nullable NbtCompound nbt) {
        if (nbt != null && nbt.contains("EntityTag", 10)) {
            NbtCompound nbtCompound = nbt.getCompound("EntityTag");
            if (nbtCompound.contains("id", 8)) {
                return EntityType.get(nbtCompound.getString("id")).orElse(this.defaultType);
            }
        }

        return this.defaultType;
    }

}
