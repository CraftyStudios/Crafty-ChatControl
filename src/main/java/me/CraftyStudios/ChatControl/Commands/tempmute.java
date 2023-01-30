package me.CraftyStudios.ChatControl.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.command.TabCompleter;

public class tempmute implements CommandExecutor, TabCompleter, Listener {
    private final JavaPlugin plugin;

    public tempmute(JavaPlugin plugin) {
        this.plugin = plugin;
    }
  private final Map<String, Long> mutedPlayers = new HashMap<>();


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("tempmute")) {
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

    if (cmd.getName().equalsIgnoreCase("tempmute")) {


      if (args.length != 2) {
        sender.sendMessage(plugin.getConfig().getString("prefix") + "Usage: /tempmute <player> <time>");
        return false;
      }

      Player target = Bukkit.getPlayer(args[0]);
      if (target == null) {
        sender.sendMessage(plugin.getConfig().getString("prefix") + args[0] + " is not online.");
        return false;
      }

      int time;
      try {
        time = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        sender.sendMessage(plugin.getConfig().getString("prefix") + "Invalid time.");
        return false;
      }

      long endTime = System.currentTimeMillis() + (time * 60 * 1000);
      mutedPlayers.put(target.getName(), endTime);
      sender.sendMessage(plugin.getConfig().getString("prefix") + target.getName() + " has been temporarily muted for " + time + " minutes.");
      return true;
    }
    return false;
  }

  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    long endTime = mutedPlayers.getOrDefault(event.getPlayer().getName(), 0L);
    if (endTime > System.currentTimeMillis()) {
      event.getPlayer().sendMessage("You are temporarily muted and cannot chat.");
      event.setCancelled(true);
    } else if (endTime > 0) {
      mutedPlayers.remove(event.getPlayer().getName());
    }
  }
}
