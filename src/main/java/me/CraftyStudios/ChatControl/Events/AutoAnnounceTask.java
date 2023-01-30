package me.CraftyStudios.ChatControl.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import me.CraftyStudios.ChatControl.Main;

import java.util.List;

public class AutoAnnounceTask extends BukkitRunnable {
  private final List<String> announcements;

  public AutoAnnounceTask(Main plugin, List<String> announcements) {
    this.announcements = announcements;
  }

  @Override
  public void run() {
    for (String announcement : announcements) {
      Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', announcement));
    }
  }
}
