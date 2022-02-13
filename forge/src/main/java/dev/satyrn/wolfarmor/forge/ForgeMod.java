package dev.satyrn.wolfarmor.forge;

import dev.satyrn.wolfarmor.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@net.minecraftforge.fml.common.Mod(Mod.MOD_ID)
public class ForgeMod {
    public ForgeMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
    }

    public void onCommonSetup(FMLCommonSetupEvent event) {
        Mod.onInitialize();
    }
}
