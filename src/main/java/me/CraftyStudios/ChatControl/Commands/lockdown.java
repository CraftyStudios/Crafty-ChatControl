package me.CraftyStudios.ChatControl.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.CraftyStudios.ChatControl.Main;

public class lockdown implements Listener, TabExecutor {
  private final JavaPlugin plugin;

    public lockdown(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("lockdown")) {
            if (args.length == 1) {
                }
            }
        
        return completions;
    }

  private boolean lockdownEnabled = false;
  
  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!sender.hasPermission("chatcontrol.command.lockdown")) {
      Player player = (Player) sender;
      player.getPlayer().sendMessage(Main.noPermission);
      return true;
    }
  else {
    lockdownEnabled = !lockdownEnabled;
    String status = lockdownEnabled ? "enabled" : "disabled";
    String lockdownMessage = plugin.getConfig().getString("lockdown-broadcast").replace("{status}", status);
    lockdownMessage = ChatColor.translateAlternateColorCodes('&', lockdownMessage);
    Bukkit.broadcastMessage(lockdownMessage);
  }if(args.length == 1) {
    if (args[0].equalsIgnoreCase("check")) {
      Player player = (Player) sender;
      String status = lockdownEnabled ? "enabled" : "disabled";
      String lockdownCheck = plugin.getConfig().getString("lockdown-check").replace("{status}", status);
      lockdownCheck = ChatColor.translateAlternateColorCodes('&', lockdownCheck);
      player.getPlayer().sendMessage(lockdownCheck);
      return true;
    }
  }
    return false;
  }


  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    if (lockdownEnabled && !event.getPlayer().hasPermission("chatcontrol.bypass")) {
      Player player = event.getPlayer();
      event.setCancelled(true);
      String lockdownMessage = plugin.getConfig().getString("lockdown-message");
      lockdownMessage = ChatColor.translateAlternateColorCodes('&', lockdownMessage);
      player.getPlayer().sendMessage(lockdownMessage);
    }
  }
}
