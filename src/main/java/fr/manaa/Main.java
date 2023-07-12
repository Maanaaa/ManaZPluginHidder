package fr.manaa;

import fr.manaa.loading.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private final PluginInitializer pluginInitializer = new PluginInitializer(this);




    @Override
    public void onEnable() {
        // Plugin startup logic
        pluginInitializer.initialize();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
