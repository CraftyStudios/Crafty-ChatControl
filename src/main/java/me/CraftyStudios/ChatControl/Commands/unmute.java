package me.CraftyStudios.ChatControl.Commands;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;

public class unmute implements CommandExecutor, TabCompleter {
    private final JavaPlugin plugin;
    private final Map<String, Long> mutedPlayers;

    public unmute(Map<String, Long> mutedPlayers, JavaPlugin plugin) {
        this.mutedPlayers = mutedPlayers;
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("unmute")) {
            if (args.length == 1) {
                // Add online players to the completions list
                for (Player player : Bukkit.getOnlinePlayers()) {
                    completions.add(player.getName());
                }
            }
        }
        return completions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(plugin.getConfig().getString("prefix") + "Usage: /unmute <player>");
            return false;
        }

        String targetName = args[0];
        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            sender.sendMessage(
                    plugin.getConfig().getString("prefix") + targetName + " is not online.");
            return false;
        }

        String targetUUID = target.getUniqueId().toString();
        if (!mutedPlayers.containsKey(targetUUID)) {
            sender.sendMessage(
                    plugin.getConfig().getString("prefix") + targetName + " is not muted.");
            return false;
        }

        mutedPlayers.remove(targetUUID);
        sender.sendMessage(
                plugin.getConfig().getString("prefix") + targetName + " has been unmuted.");
        return true;
    }
}
