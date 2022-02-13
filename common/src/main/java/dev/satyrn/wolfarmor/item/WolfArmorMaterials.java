package dev.satyrn.wolfarmor.item;

import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.function.Supplier;

public enum WolfArmorMaterials implements WolfArmorMaterial {

    LEATHER ("leather", 5, 7, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0F, 0F, () -> Ingredient.ofItems(Items.LEATHER)),
    // TODO: COPPER ("copper", 10, 10, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0F, 0F, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    // TODO: EXPOSED_COPPER ("exposed_copper", 8, 10, 19, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0F, 0.1F, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    // TODO: WEATHERED_COPPER ("weathered_copper", 6, 10, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0F, 0.25F, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    // TODO: OXIDIZED_COPPER ("oxidized_copper", 4, 10, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0F, 0.5F, () -> Ingredient.ofItems(Items.COPPER_INGOT)),
    CHAIN ("chainmail", 15, 12, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0F, 0F, () -> Ingredient.ofItems(Items.IRON_INGOT)),
    IRON ("iron", 15, 15, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0F, 0F, () -> Ingredient.ofItems(Items.IRON_INGOT)),
    GOLD ("gold", 7, 11, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0F, 0F, () -> Ingredient.ofItems(Items.GOLD_INGOT)),
    DIAMOND ("diamond", 33, 20, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2F, 0F, () -> Ingredient.ofItems(Items.DIAMOND)),
    NETHERITE ("netherite", 37, 20, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3F, 0.1F, () -> Ingredient.ofItems(Items.NETHERITE_INGOT));

    // Same durability as chest armor
    private static final int BASE_DURABILITY = 15;
    private final String name;
    private final int durabilityMultiplier;
    private final int protectionAmount;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredientSupplier;

    WolfArmorMaterials(String name, int durabilityMultiplier, int protectionAmount, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmount = protectionAmount;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = repairIngredientSupplier;
    }

    @Override
    public int getDurability() {
        return BASE_DURABILITY * this.durabilityMultiplier;
    }

    @Override
    public int getProtectionAmount() {
        return this.protectionAmount;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredientSupplier.get();
    }

    @Override
    public String getName() {
        return this.name;
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
