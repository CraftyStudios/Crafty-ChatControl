package me.CraftyStudios.ChatControl.Events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class autoResponder implements Listener {
    public JavaPlugin plugin;
// Mapping of keywords to response messages
private Map<String, String> responses;

public void loadConfig() {
    // Load the config file into memory
    FileConfiguration config = plugin.getConfig();

    // Create the HashMap
    responses = new HashMap<>();

    // Iterate through the config, getting each keyword-response pair
    for (String keyword : config.getConfigurationSection("responses").getKeys(false)) {
        responses.put(keyword, config.getString("responses." + keyword));
    }
}

@EventHandler
public void onChat(AsyncPlayerChatEvent e, Player player) {
    // Get the message
    String message = e.getMessage();
    
    // Iterate through the keywords
  for (String keyword : plugin.getConfig().getStringList("keywords")) {
            if (message.contains(keyword)) {
                String response = plugin.getConfig().getString("responses." + keyword);
                String colorCode = plugin.getConfig().getString("response-color-autoresponder", "WHITE");
                player.sendMessage(ChatColor.valueOf(colorCode) + response);
                break;
        }
    }
}
}