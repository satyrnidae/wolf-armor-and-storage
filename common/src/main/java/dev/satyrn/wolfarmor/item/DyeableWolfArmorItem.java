package dev.satyrn.wolfarmor.item;

import net.minecraft.item.DyeableItem;

/**
 * A wolf armor item that can be dyed.
 *
 * @author Isabel Maskrey
 * @since 4.0.0
 */
public class DyeableWolfArmorItem extends WolfArmorItem implements DyeableItem {
    public DyeableWolfArmorItem(WolfArmorMaterial material, Settings settings) {
        super(material, settings);
    }
}
