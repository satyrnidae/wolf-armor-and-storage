package dev.satyrn.wolfarmor.entity.passive;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.item.ItemStack;

public interface ArmoredWolfEntity {
    class CustomEntityData {
        public static TrackedData<ItemStack> CHEST;
    }

    ItemStack getChest();

    void setChest(ItemStack stack);
}
