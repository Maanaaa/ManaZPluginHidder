package fr.manaa.loading;

import fr.manaa.*;
import fr.manaa.commands.*;
import fr.manaa.commands.administration.*;

import java.util.*;

public class PluginInitializer {

    // Main class
    private Main main;
    public PluginInitializer(Main main){this.main = main;}

    // Initialize
    public void initialize(){
        // Saving config file config.yml
        this.main.saveDefaultConfig();

        // Register class for command to reload config.yml
        Objects.requireNonNull(main.getCommand("zpluginhidder")).setExecutor(new Reload(main));

        // Register class for /plugin command
        this.main.getServer().getPluginManager().registerEvents(new CommandHidder(main), main);

        // Register class for Unknown Command message
        this.main.getServer().getPluginManager().registerEvents(new UnknownCommand(main), main);
    }

}
