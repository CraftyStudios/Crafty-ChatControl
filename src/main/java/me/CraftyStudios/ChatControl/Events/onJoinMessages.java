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

    public boolean modelEnabled() {
      return plugin.getConfig().getBoolean("join-messages-enabled");
    }
  @Override
  public void onEnable() {
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
  }
  
// Boolean to check if model is enabled in config
  

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    if (modelEnabled() == true) {
    Player player = event.getPlayer();
    String joinMessage = plugin.getConfig().getString("join-message");
    joinMessage = ChatColor.translateAlternateColorCodes('&', joinMessage).replace("{player}", player.getName());
    Bukkit.broadcastMessage(joinMessage);
        
  }
  else {
    return;
  }
}
}