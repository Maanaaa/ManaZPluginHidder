package fr.manaa.commands;

import fr.manaa.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.*;
import org.bukkit.plugin.*;

import java.util.*;

public class CommandHidder implements Listener {
    // Main class
    private Main main;
    public CommandHidder(Main main) {
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
            sendHiddingMessage(player);
        }
    }

    public void sendHiddingMessage(Player player){
        List<String> authors = main.getConfig().getStringList("show-authors");
        List<String> plugins = getPluginsByAuthors(authors);
        int pluginNumber = getAuthorsPluginNumber(authors);
        // Get return message from config.yml and sending
        for(String returnMessage : main.getConfig().getStringList("plugin-messages.Hidder")) {
            player.sendMessage(returnMessage.
                    replace("&","§").
                    replace("%pluginAuthorsList%", String.join("§f, §a", plugins)
                            .replace("%player%", player.getName()))
                    .replace("%pluginNumber%", ""+pluginNumber)
                    .replace("%otherPluginNumber%",""+getOtherPlugins())
                    .replace("%totalPluginNumber%", ""+getTotalPluginNumber()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTabComplete(TabCompleteEvent e) {
        if (e.getSender() instanceof Player) {
            Player player = (Player) e.getSender();
            String cmd = e.getBuffer(); // Récupérer la commande saisie

            if (cmd.startsWith("/about")) {
                e.setCancelled(true); // Annuler la complétion automatique
            }
        }
    }


    private int getTotalPluginNumber(){
        int count = 0;
        for(Plugin plugin : Bukkit.getPluginManager().getPlugins()){
            count++;
            break;
        }
        return count;
    }

    private int getOtherPlugins(){
        int count = 0;
        List<String> authors = main.getConfig().getStringList("show-authors");
        for(Plugin plugin : Bukkit.getPluginManager().getPlugins()){
            count++;
            break;
        }
        return count-getAuthorsPluginNumber(authors);
    }

    private int getAuthorsPluginNumber(List<String> authors){
        int count = 0;
        for(Plugin plugin : Bukkit.getPluginManager().getPlugins()){
            List<String> pluginAuthors = plugin.getDescription().getAuthors();
            for (String author : authors){
                if(pluginAuthors.contains(author)){
                    count++;
                    break;
                }
            }
        }
        return count;
    }
    private List<String> getPluginsByAuthors(List<String> authors) {
        List<String> plugins = new ArrayList<>();
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            List<String> pluginAuthors = plugin.getDescription().getAuthors();
            for (String author : authors) {
                if (pluginAuthors.contains(author)) {
                    plugins.add(plugin.getName());
                    break;
                }
            }
        }
        return plugins;
    }
}
