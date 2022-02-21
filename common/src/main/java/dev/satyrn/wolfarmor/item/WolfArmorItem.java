package dev.satyrn.wolfarmor.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * Base class for a wolf armor item.
 *
 * @author Isabel Maskrey
 * @since 4.0.0
 */
public class WolfArmorItem extends Item implements CustomEnchantableItem {
    private static final UUID MODIFIER = UUID.fromString("aaca9972-43f4-4b94-b546-b344bbb1fa74");
    /**
     * Custom behavior to equip wolves with armor from a dispenser.
     */
    public static final DispenserBehavior DISPENSER_BEHAVIOR = new ItemDispenserBehavior() {
        @Override
        protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
            return WolfArmorItem.dispenseArmor(pointer, stack) ? stack : super.dispenseSilently(pointer, stack);
        }
    };
    private final WolfArmorMaterial type;
    private final int protection;
    private final float toughness;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public WolfArmorItem(WolfArmorMaterial armorMaterial, Settings settings) {
        super(settings.maxDamageIfAbsent(armorMaterial.getDurability()));
        this.type = armorMaterial;
        this.protection = armorMaterial.getProtectionAmount();
        this.toughness = armorMaterial.getToughness();
        final float knockbackResistance = armorMaterial.getKnockbackResistance();

        DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR);

        final Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(MODIFIER, "Armor modifier", this.protection,
                Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(MODIFIER, "Armor toughness", this.toughness, Operation.ADDITION));
        if (knockbackResistance > Math.ulp(1.0F)) {
            builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(MODIFIER, "Armor knockback resistance",
                    knockbackResistance, Operation.ADDITION));
        }
        this.attributeModifiers = builder.build();
    }

    @Override
    public int getEnchantability() {
        return this.type.getEnchantability();
    }

    public WolfArmorMaterial getMaterial() {
        return this.type;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return this.type.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.CHEST ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public @Nullable SoundEvent getEquipSound() {
        return this.getMaterial().getEquipSound();
    }

    public int getProtection() {
        return this.protection;
    }

    public float getToughness() {
        return this.toughness;
    }

    public static boolean dispenseArmor(BlockPointer pointer, ItemStack armor) {
        final BlockPos pos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        List<WolfEntity> list = pointer.getWorld().getEntitiesByClass(WolfEntity.class, new Box(pos), EntityPredicates.VALID_LIVING_ENTITY);
        if (list.isEmpty()) {
            return false;
        } else {
            final WolfEntity wolf = list.get(0);
            ItemStack stack = armor.split(1);
            wolf.equipStack(EquipmentSlot.CHEST, stack);

            return true;
        }
    }

    @Override
    public boolean canApplyEnchantment(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.SWEEPING) {
            return false;
        }
        final EnchantmentTarget type = enchantment.type;
        return type == EnchantmentTarget.BREAKABLE || type == EnchantmentTarget.WEAPON || type == EnchantmentTarget.ARMOR
                || type == EnchantmentTarget.ARMOR_CHEST || type == EnchantmentTarget.ARMOR_FEET;
    }
}
