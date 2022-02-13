package dev.satyrn.wolfarmor.item;

import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

/**
 * Provides various details about a wolf armor item.
 *
 * @author Isabel Maskrey
 * @since 4.0.0+1.18.alpha.1
 */
public interface WolfArmorMaterial {
    int getDurability();

    int getProtectionAmount();

    int getEnchantability();

    SoundEvent getEquipSound();

    Ingredient getRepairIngredient();

    String getName();

    float getToughness();

    float getKnockbackResistance();
}
