package fr.manaa.commands.administration;

import fr.manaa.*;
import org.bukkit.command.*;

import java.io.*;

public class Reload implements CommandExecutor {
    // Main class
    private Main main;
    public Reload(Main main) {
        this.main = main;
    }

    // When player make "/zhidder"
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args[0].equalsIgnoreCase("reload")){
            // Reload config
            this.main.reloadConfig();
            sender.sendMessage("§n§f                                   ");
            sender.sendMessage("§n§f                                   ");
            sender.sendMessage("§7>> §e§lZPluginHidder V1.0 SNAPSHOT §r§6by Manaa_ has been reloaded !");
            sender.sendMessage("§7>> §6§lContacts §f: §aDiscord: Mana#6258");
            sender.sendMessage("§n§f                                   ");
            sender.sendMessage("§n§f                                   ");
            return false;
        }
        return false;
    }
}
