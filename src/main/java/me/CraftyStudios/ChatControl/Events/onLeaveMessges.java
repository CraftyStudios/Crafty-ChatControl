package me.CraftyStudios.ChatControl.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class onLeaveMessges extends JavaPlugin implements Listener {
    public JavaPlugin plugin;

    public boolean modelEnabled() {
      return plugin.getConfig().getBoolean("leave-messages-enabled");
    }
  @Override
  public void onEnable() {
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
  }

    public void onPlayerLeave(PlayerQuitEvent event) {
        if (modelEnabled() == true) {
        Player player = event.getPlayer();
        String leaveMessage = plugin.getConfig().getString("leave-message");
        leaveMessage = ChatColor.translateAlternateColorCodes('&', leaveMessage).replace("{player}", player.getName());
        Bukkit.broadcastMessage(leaveMessage);
            
    }
    else {
        return;
    }
    
}
}
