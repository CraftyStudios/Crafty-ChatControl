package me.CraftyStudios.ChatControl.Commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.CraftyStudios.ChatControl.Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class cchelp implements CommandExecutor, TabCompleter {
    private final JavaPlugin plugin;

    public cchelp(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("cchelp")) {
            if (args.length == 1) {
                // Add possible subcommands to the completions list
            }
        }
        return completions;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (args.length == 0) {
                if (sender.hasPermission("craftychatcontrol.help")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCrafty Chat Control Help"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/ccgui &7- &fOpens the GUI"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/ccreload &7- &fReloads the config"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/mute &7- &fMutes a player"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/unmute &7- &fUnmutes a player"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/muteall &7- &fMutes all players"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/unmuteall &7- &fUnmutes all players"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/lockdown &7- &fLocks down the server from chatting"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c/unlockdown &7- &fUnlocks the server from chatting"));
                    return true;
                } else {
                    Player player = (Player) sender;
                    player.getPlayer().sendMessage(Main.noPermission);
            }
        }return false;
    }
}

