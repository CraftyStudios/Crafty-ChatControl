package me.CraftyStudios.ChatControl.Events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

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

  // Boolean to check if caps lock is enabled
  public boolean capsLockEnabled() {
    return plugin.getConfig().getBoolean("antiCapsLock-enabled");
  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent event, CommandSender sender) {
    if (capsLockEnabled() == true) {
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
  Player player = (Player) sender;
  String capsLockMessage = plugin.getConfig().getString("antiCapsLock-message");
  capsLockMessage = ChatColor.translateAlternateColorCodes('&', message);
  player.getPlayer().sendMessage(capsLockMessage);
  event.setCancelled(true);
}
    }
      }
    }
  }


