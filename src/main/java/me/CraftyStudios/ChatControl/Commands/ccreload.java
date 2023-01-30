package me.CraftyStudios.ChatControl.Commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class ccreload implements CommandExecutor, TabCompleter {
    private final JavaPlugin plugin;

    public ccreload(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("ccreload")) {
            if (args.length == 1) {
                // Add possible subcommands to the completions list
            }
        }
        return completions;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (args.length == 0) {
                if (sender.hasPermission("craftychatcontrol.reload")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") +plugin.getConfig().getString("reload")));
                    plugin.reloadConfig();
                    return true;
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("no-permission")));
            }
        }return false;
    }
}

