package me.CraftyStudios.ChatControl.Events;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageCooldown implements Listener {
  public JavaPlugin plugin;
  private final Map<UUID, Long> cooldowns = new HashMap<>();
  private final long cooldownTime;

  public MessageCooldown(JavaPlugin plugin) {
    this.plugin = plugin;
    this.cooldownTime = plugin.getConfig().getLong("message-cooldown-time");
  }

  // Boolean to check if the model is enabled in config
  public boolean isEnabled() {
    return plugin.getConfig().getBoolean("message-cooldown-enabled");
  }
  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    if (isEnabled() == true) {
    
    Player player = event.getPlayer();
    UUID playerUUID = player.getUniqueId();
    long currentTime = System.currentTimeMillis();

    if (cooldowns.containsKey(playerUUID) && currentTime - cooldowns.get(playerUUID) < cooldownTime) {
      String cooldownMessage = plugin.getConfig().getString("message-cooldown-message");
      cooldownMessage = cooldownMessage.replace("{time}", String.valueOf((cooldownTime - (currentTime - cooldowns.get(playerUUID))) / 1000));ChatColor.translateAlternateColorCodes('&', cooldownMessage);
      player.getPlayer().sendMessage(cooldownMessage);
      event.setCancelled(true);
    } else {
      cooldowns.put(playerUUID, currentTime);
    }
  }
  else {
    return;
  }
}
}
