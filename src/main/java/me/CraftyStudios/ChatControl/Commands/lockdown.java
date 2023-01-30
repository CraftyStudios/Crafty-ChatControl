package me.CraftyStudios.ChatControl.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

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
                // Add online players to the completions list
                }
            }
        
        return completions;
    }

  private boolean lockdownEnabled = false;

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    lockdownEnabled = !lockdownEnabled;
    String status = lockdownEnabled ? "enabled" : "disabled";
    Bukkit.broadcastMessage("Server lockdown has been " + status + ".");
    return true;
  }

  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    if (lockdownEnabled && !event.getPlayer().hasPermission("chatcontrol.bypass")) {
      event.setCancelled(true);
      event.getPlayer().sendMessage("Server is currently in lockdown. You cannot chat.");
    }
  }
}
