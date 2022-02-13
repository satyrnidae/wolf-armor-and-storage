package dev.satyrn.wolfarmor.config;

import dev.satyrn.wolfarmor.Mod;
import dev.satyrn.wolfarmor.util.WolfHungerSettings;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;

/**
 * Main configuration class for the mod.
 *
 * @author Isabel Maskrey
 * @since 1.0.0
 */
@Config(name = Mod.MOD_ID)
public class ModConfiguration implements ConfigData {
    // Whether the crafting recipes should be enabled.
    boolean allowCrafting = true;
    // The maximum number of tamed wolves that a player may have at one time.
    // Defaults to 0, or "any number".
    @BoundedDiscrete(min = 0, max = 10)
    int maxTamedWolves = 0;

    // Options for wolf backpacks.
    @CollapsibleObject(startExpanded = true)
    Backpack backpack = new Backpack();

    // Options for modified wolf behavior.
    @CollapsibleObject(startExpanded = true)
    Behavior behavior = new Behavior();

    // Client-side options. Setting these on the server has no effect.
    @CollapsibleObject
    Client client = new Client();

    public int getMaxTamedWolves() {
        return this.maxTamedWolves;
    }

    public boolean isBackpackEnabled() {
        return this.backpack.enableBackpacks;
    }

    public boolean isEnderChestBackpackEnabled() {
        return this.backpack.allowEnderChests;
    }

    public int getBackpackWidth() {
        return this.backpack.width;
    }

    public int getBackpackHeight() {
        return this.backpack.height;
    }

    public boolean isHowlAtMoonEnabled() {
        return this.behavior.howlAtMoon;
    }

    public boolean isAutoEatEnabled() {
        return this.behavior.autoEat;
    }

    public WolfHungerSettings getHungerSettings() {
        return this.behavior.hungerSetting;
    }

    public boolean shouldShowArmorModel() {
        return this.client.showArmorModel;
    }

    public boolean shouldShowChestModel() {
        return this.client.showChestModel;
    }

    public boolean shouldShowStatsInInventory() {
        return this.client.showStatsInInventory;
    }

    public boolean shouldShowStatsInNameplate() {
        return this.client.showStatsInNameplate;
    }

    /**
     * Wolf backpack options.
     */
    static class Backpack {
        // Whether wolf backpacks are enabled.
        boolean enableBackpacks = true;
        // Whether Ender Chests can be applied as wolf backpacks.
        boolean allowEnderChests = true;
        // The width of the backpack.
        @BoundedDiscrete(min = 1, max = 5)
        int width = 3;
        // The height of the backpack.
        @BoundedDiscrete(min = 1, max = 3)
        int height = 2;
    }

    /**
     * Wolf behavior toggles
     */
    static class Behavior {
        // Whether wolves should howl at the moon.
        boolean howlAtMoon = true;
        // Whether wolves will eat food from their packs when they are hurt or hungry
        boolean autoEat = true;
        WolfHungerSettings hungerSetting = WolfHungerSettings.DIFFICULTY_DEPENDANT;
    }

    /**
     * Options for clients.
     */
    static class Client {
        // Whether the armor model should be shown
        boolean showArmorModel = true;
        // Whether the chest model should be shown
        boolean showChestModel = true;
        // Whether wolf stats should be shown in the inventory
        boolean showStatsInInventory = true;
        // Whether wolf stats should be shown in the nameplate
        boolean showStatsInNameplate = true;
    }
}
