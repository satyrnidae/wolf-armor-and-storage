package dev.satyrn.wolfarmor.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.satyrn.wolfarmor.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@net.minecraftforge.fml.common.Mod(Mod.MOD_ID)
public class ForgeMod {
    public ForgeMod() {
        EventBuses.registerModEventBus(Mod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        Mod.onInitialize();
    }
}
