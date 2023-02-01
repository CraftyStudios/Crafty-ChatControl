package me.CraftyStudios.ChatControl.Events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class antiCaps implements Listener {
  private int minLength;
  private int minCaps;
  private int minCapsRatio;
  public JavaPlugin plugin;

  public antiCaps(FileConfiguration config) {
    minLength = config.getInt("antiCapsLock.minLength");
    minCaps = config.getInt("antiCapsLock.minCaps");
    minCapsRatio = config.getInt("antiCapsLock.minCapsRatio");
  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent event) {
    String message = event.getMessage();
    if (message.length() >= minLength) {
      int capsCount = 0;
      for (char c : message.toCharArray()) {
        if (Character.isUpperCase(c)) {
          capsCount++;
        }
      }
    int capsRatio = (int) ((double) capsCount / message.length() * 100);
if (capsCount >= minCaps && capsRatio >= minCapsRatio) {
  event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("antiCapsLock.message")));
  event.setCancelled(true);
}

      }
    }
  }


