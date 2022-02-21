package dev.satyrn.wolfarmor.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public interface CustomEnchantableItem {
    boolean canApplyEnchantment(ItemStack stack, Enchantment enchantment);
}
