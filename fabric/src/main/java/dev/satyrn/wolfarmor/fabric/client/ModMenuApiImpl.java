package dev.satyrn.wolfarmor.fabric.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.satyrn.wolfarmor.config.ModConfiguration;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuApiImpl implements ModMenuApi {
    /**
     * Used to construct a new config screen instance when your mod's
     * configuration button is selected on the mod menu screen. The
     * screen instance parameter is the active mod menu screen.
     *
     * @return A factory for constructing config screen instances.
     */
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(ModConfiguration.class, parent).get();
    }
}
