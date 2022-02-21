package dev.satyrn.wolfarmor.mixin;

import dev.satyrn.wolfarmor.item.CustomEnchantableItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    public void injectIsAcceptableItem(final ItemStack stack, final CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof CustomEnchantableItem) {
            cir.setReturnValue(((CustomEnchantableItem)stack.getItem()).canApplyEnchantment(stack, this.asEnchantment()));
            cir.cancel();
        }
    }

    private Enchantment asEnchantment() {
        return (Enchantment) (Object) this;
    }
}
