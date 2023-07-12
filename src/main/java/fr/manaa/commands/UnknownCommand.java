package fr.manaa.commands;

import fr.manaa.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.help.*;

public class UnknownCommand implements Listener {
    private Main main;

    public UnknownCommand(Main main) {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
        if (!e.isCancelled()) {
            if(main.getConfig().getBoolean("plugin-messages.Unknown-command.enable")){
                Player player = e.getPlayer();
                String cmd = e.getMessage().split(" ")[0];
                HelpTopic label = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
                if (label == null) {
                    player.sendMessage(main.getConfig().getString("plugin-messages.Unknown-command.message").replace("&", "§"));
                    e.setCancelled(true);
                }
            }
        }
}
}
