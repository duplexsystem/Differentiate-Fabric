package co.eltrut.differentiate.core.registrator;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class EntityHelper extends AbstractHelper {
	public EntityHelper(Registrator parent) {
		super(parent);
	}
	
	public <E extends LivingEntity> EntityType<E> createSimpleEntity(String name, EntityType.EntityFactory<E> factory, SpawnGroup classification, float width, float height) {
		return Registry.register(Registry.ENTITY_TYPE, this.parent.id(name), FabricEntityTypeBuilder.create(classification, factory)
				.dimensions(EntityDimensions.changing(width, height))
				.trackRangeChunks(64)
				.forceTrackedVelocityUpdates(true)
				.trackedUpdateRate(3)
				.build());
	}
}