package dev.satyrn.wolfarmor.mixin;

import dev.satyrn.wolfarmor.config.ModConfiguration;
import dev.satyrn.wolfarmor.entity.passive.ArmoredWolfEntity;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Allows wolves to equip armor.
 *
 * @author Isabel Maskrey
 * @since 4.0.0
 */
@Mixin(WolfEntity.class)
public class WolfEntityMixin implements ArmoredWolfEntity, InventoryChangedListener {
    private ModConfiguration configuration;
    private SimpleInventory items;

    private int getInventorySize() {
        if (!this.getChest().isEmpty()) {
            return 1 + (this.configuration.getBackpackWidth() * this.configuration.getBackpackHeight());
        }
        return 1;
    }

    private void onChestedStatusChanged() {
        final SimpleInventory simpleInventory = this.items;
        this.items = new SimpleInventory(this.getInventorySize());
        if (simpleInventory != null) {
            simpleInventory.removeListener(this);
            int maxInvSize = Math.min(simpleInventory.size(), this.items.size());

            for (int index = 0; index < maxInvSize; ++index) {
                final ItemStack stack = simpleInventory.getStack(index);
                if (!stack.isEmpty()) {
                    this.items.setStack(index, stack);
                }
            }
        }

        this.items.addListener(this);
    }

    private boolean isWolfArmor(final ItemStack stack) {
        // TODO: return stack.getItem() instanceof WolfArmorItem;
        return false;
    }

    private WolfEntity asWolf() {
        return (WolfEntity) (Object) this;
    }

    @Override
    public ItemStack getChest() {
        return this.asWolf().getDataTracker().get(CustomEntityData.CHEST);
    }

    @Override
    public void setChest(final ItemStack stack) {
        this.asWolf().getDataTracker().set(CustomEntityData.CHEST, stack);
    }

    @Override
    public void onInventoryChanged(Inventory sender) {
        //TODO: Apply armor modifiers
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectInit(CallbackInfo ci) {
        this.configuration = AutoConfig.getConfigHolder(ModConfiguration.class).getConfig();
        this.onChestedStatusChanged();
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void injectWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (!this.items.getStack(1).isEmpty()) {
            nbt.put("ArmorItem", this.items.getStack(1).writeNbt(new NbtCompound()));
        }
        final ItemStack chestItem = this.getChest();
        if (!chestItem.isEmpty()) {
            nbt.put("ChestItem", chestItem.writeNbt(new NbtCompound()));
            final NbtList items = new NbtList();
            for (int slot = 0; slot < this.items.size(); ++slot) {
                final ItemStack stackInSlot = this.items.getStack(slot);
                if (!stackInSlot.isEmpty()) {
                    final NbtCompound nbtCompound = new NbtCompound();
                    nbtCompound.putByte("Slot", (byte)slot);
                    stackInSlot.writeNbt(nbtCompound);
                }
            }
            nbt.put("Items", items);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void injectReadCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("ArmorItem", 10)) {
            final ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("ArmorItem"));
            if (!itemStack.isEmpty() && this.isWolfArmor(itemStack)) {
                this.items.setStack(1, itemStack);
            }
        }
        this.onChestedStatusChanged();
        if (nbt.contains("ChestItem", 10)) {
            final ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("ChestItem"));
            if (!itemStack.isEmpty()) {
                this.setChest(itemStack);

                final NbtList items = nbt.getList("Items", 10);
                for (int index = 0; index < items.size(); ++index) {
                    final NbtCompound nbtCompound = items.getCompound(index);
                    int slot = nbtCompound.getByte("Slot") & 255;
                    if (slot >= 1 && slot < this.items.size()) {
                        this.items.setStack(slot, ItemStack.fromNbt(nbtCompound));
                    }
                }
            }
        }
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void injectInitDataTracker(CallbackInfo ci) {
        this.asWolf().getDataTracker().startTracking(CustomEntityData.CHEST, ItemStack.EMPTY);
    }

    static {
        CustomEntityData.CHEST = DataTracker.registerData(WolfEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    }
}
