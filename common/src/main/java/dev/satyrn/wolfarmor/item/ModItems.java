package dev.satyrn.wolfarmor.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.satyrn.wolfarmor.Mod;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class ModItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Mod.MOD_ID, Registry.ITEM_KEY);

    public static final RegistrySupplier<Item> LEATHER_WOLF_ARMOR = ITEMS.register("leather_wolf_armor", () -> new DyeableWolfArmorItem(WolfArmorMaterials.LEATHER, new Settings().maxCount(1).group(ItemGroup.MISC)));
    public static final RegistrySupplier<Item> CHAINMAIL_WOLF_ARMOR = ITEMS.register("chainmail_wolf_armor", () -> new WolfArmorItem(WolfArmorMaterials.CHAIN, new Settings().maxCount(1).group(ItemGroup.MISC)));
    public static final RegistrySupplier<Item> IRON_WOLF_ARMOR = ITEMS.register("iron_wolf_armor", () -> new WolfArmorItem(WolfArmorMaterials.IRON, new Settings().maxCount(1).group(ItemGroup.MISC)));
    public static final RegistrySupplier<Item> GOLD_WOLF_ARMOR = ITEMS.register("gold_wolf_armor", () -> new WolfArmorItem(WolfArmorMaterials.GOLD, new Settings().maxCount(1).group(ItemGroup.MISC)));
    public static final RegistrySupplier<Item> DIAMOND_WOLF_ARMOR = ITEMS.register("diamond_wolf_armor", () -> new WolfArmorItem(WolfArmorMaterials.DIAMOND, new Settings().maxCount(1).group(ItemGroup.MISC)));
    public static final RegistrySupplier<Item> NETHERITE_WOLF_ARMOR = ITEMS.register("netherite_wolf_armor", () -> new WolfArmorItem(WolfArmorMaterials.NETHERITE, new Settings().maxCount(1).group(ItemGroup.MISC)));

    public static void registerItems() {
        ITEMS.register();
    }
}
