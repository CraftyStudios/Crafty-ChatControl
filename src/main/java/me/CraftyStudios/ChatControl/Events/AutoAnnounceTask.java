package me.CraftyStudios.ChatControl.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.CraftyStudios.ChatControl.Main;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class AutoAnnounceTask implements Runnable {
  private final List<String> announcements;
  private final JavaPlugin plugin;
  private int index;
  
  public AutoAnnounceTask(Main plugin, List<String> announcements) {
    this.announcements = announcements;
    this.plugin = plugin;
    this.index = 0;
  }
  
  // Boolean in config to enable/disable auto-announcements
  public boolean isAutoAnnounceEnabled() {
    return plugin.getConfig().getBoolean("auto-announce-enabled");
  }
  
  @Override
  public void run() {
    if (isAutoAnnounceEnabled() == true) {
      if (index >= announcements.size()) {
        index = 0;
      }
      
      String announcement = announcements.get(index);
      Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', announcement));
      index++;
    }
  }
}
