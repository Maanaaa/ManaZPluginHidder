package fr.manaa.commands;

import fr.manaa.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

import java.util.*;

public class CustomCommand implements Listener {
    // Main class
    private Main main;
    public CustomCommand(Main main) {
        this.main = main;
    }

    // If player make a command
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMakeCommand(PlayerCommandPreprocessEvent e){

        // Get the command
        String cmd = e.getMessage();

        // Get the sender
        Player player = (Player) e.getPlayer();

        // Get commands lists from config file
        List<String> commandsList = main.getConfig().getStringList("commands-hidden");

        // Get messages from config file
        String noPerm = Objects.requireNonNull(main.getConfig().getString("plugin-messages.NoPerm")).replace("&","§");

        // If command is a command contained in config.yml
        if(commandsList.contains(cmd)){
            e.setCancelled(true);
            // Get return message from config.yml and sending
            for(String returnMessage : main.getConfig().getStringList("plugin-messages.Hidder")) {
                player.sendMessage(returnMessage.replace("&","§").replace("%player%", player.getName()));
            }
        }
    }
}
