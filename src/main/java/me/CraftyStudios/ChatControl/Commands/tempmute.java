package me.CraftyStudios.ChatControl.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
      Player player = (Player) sender;
      long endTime = System.currentTimeMillis() + (time * 60 * 1000);
      mutedPlayers.put(target.getName(), endTime);
      String targetPlayer = target.getName(); 
      String muteMessage = plugin.getConfig().getString("mute-message").replace("{target}", targetPlayer).replace("{time}", args[1]);
      muteMessage = ChatColor.translateAlternateColorCodes('&', muteMessage);
      player.getPlayer().sendMessage(muteMessage);
      return true;
    }
    return false;
  }

  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    long endTime = mutedPlayers.getOrDefault(event.getPlayer().getName(), 0L);
    if (endTime > System.currentTimeMillis()) {
      Player player = (Player) event.getPlayer();
      var remainingTime = (endTime - System.currentTimeMillis()) / 1000;
      String mutedMessage = plugin.getConfig().getString("muted-message").replace("{remainingtime}", String.valueOf(remainingTime));
      mutedMessage = ChatColor.translateAlternateColorCodes('&', mutedMessage);
      player.getPlayer().sendMessage(mutedMessage);
      event.setCancelled(true);
    } else if (endTime > 0) {
      mutedPlayers.remove(event.getPlayer().getName());
      event.setCancelled(false);
    }
  }
}
