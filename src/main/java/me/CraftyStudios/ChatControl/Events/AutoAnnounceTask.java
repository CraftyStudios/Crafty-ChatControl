package me.CraftyStudios.ChatControl.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.CraftyStudios.ChatControl.Main;

import java.util.List;

public class AutoAnnounceTask extends BukkitRunnable {
  private final List<String> announcements;
  private final JavaPlugin plugin;

  public AutoAnnounceTask(Main plugin, List<String> announcements) {
    this.announcements = announcements;
    this.plugin = plugin;
  }
// Boolean in config to enable/disable auto-announcements
public boolean isAutoAnnounceEnabled() {
    return plugin.getConfig().getBoolean("auto-announce-enabled");
  }
  @Override
  public void run() {
    if (isAutoAnnounceEnabled() == true) {
    for (String announcement : announcements) {
      Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', announcement));
    }
    }
    else {
        return;
    }
  }
}
