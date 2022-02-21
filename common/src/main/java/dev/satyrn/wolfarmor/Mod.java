package dev.satyrn.wolfarmor;

import com.google.common.base.Suppliers;
import dev.architectury.registry.level.GameRuleFactory;
import dev.architectury.registry.level.GameRuleRegistry;
import dev.architectury.registry.registries.Registries;
import dev.satyrn.wolfarmor.config.ModConfiguration;
import dev.satyrn.wolfarmor.item.ModItems;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class Mod {
    public static final String MOD_ID = "wolfarmor";
    public static final Supplier<Registries> REGISTRIES = Suppliers.memoize(() -> Registries.get(MOD_ID));
    public static @Nullable GameRules.Key<GameRules.BooleanRule> RULE_ALLOW_WOLF_STARVATION = null;

    public static void onInitialize() {
        //TODO: Common code for initializing both mod loaders
        RULE_ALLOW_WOLF_STARVATION = GameRuleRegistry.register(MOD_ID + ":allowWolfStarvation", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));

        AutoConfig.register(ModConfiguration.class, JanksonConfigSerializer::new);
        ModItems.registerItems();
    }
}
