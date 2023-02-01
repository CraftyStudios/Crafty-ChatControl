package me.CraftyStudios.ChatControl.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class onJoinMessages extends JavaPlugin implements Listener {
    public JavaPlugin plugin;
  @Override
  public void onEnable() {
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    player.sendMessage(
        ChatColor.translateAlternateColorCodes('&', 
        plugin.getConfig().getString("join-message")));
  }
}
