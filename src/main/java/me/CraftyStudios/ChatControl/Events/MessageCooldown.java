package me.CraftyStudios.ChatControl.Events;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    UUID playerUUID = player.getUniqueId();
    long currentTime = System.currentTimeMillis();

    if (cooldowns.containsKey(playerUUID) && currentTime - cooldowns.get(playerUUID) < cooldownTime) {
      player.sendMessage(plugin.getConfig().getString("message-cooldown-message"));
    } else {
      cooldowns.put(playerUUID, currentTime);
    }
  }
}
