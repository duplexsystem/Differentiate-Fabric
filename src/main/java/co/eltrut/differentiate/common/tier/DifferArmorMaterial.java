package co.eltrut.differentiate.common.tier;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public class DifferArmorMaterial implements ArmorMaterial {
	
	private static final int[] MAX_DAMAGE_ARRAY = {13, 15, 16, 11};
	private final Identifier name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final float knockbackResistance;
	private final Lazy<Ingredient> repairMaterial;

	public DifferArmorMaterial(Identifier name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
		this.name = name;
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReductionAmountArray;
		this.enchantability = enchantability;
		this.soundEvent = soundEvent;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairMaterial = new Lazy<>(repairMaterial);
	}

	@Override
	public int getDurability(EquipmentSlot slot) { //
		return MAX_DAMAGE_ARRAY[slot.getEntitySlotId()] * this.maxDamageFactor;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot) { //
		return this.damageReductionAmountArray[slot.getEntitySlotId()];
	}

	@Override
	public int getEnchantability() { //
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairMaterial.get();
	}

	@Override
	public String getName() {
		return this.name.toString();
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

}
