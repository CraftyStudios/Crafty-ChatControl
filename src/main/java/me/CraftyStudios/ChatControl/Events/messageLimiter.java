package me.CraftyStudios.ChatControl.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class messageLimiter implements Listener {
    public JavaPlugin plugin;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        
        // Check if the feature is enabled in config
        if (plugin.getConfig().getBoolean("message-length-limit-enabled")) {
            if (!p.hasPermission("chatcontrol.bypass.message-length-limit")) {
                if (message.length() > plugin.getConfig().getInt("max-message-length")) {
                    Player player = (Player) p;
                    String messageLengthLimitMessage = plugin.getConfig().getString("message-length-limit-message");
                    messageLengthLimitMessage = ChatColor.translateAlternateColorCodes('&', messageLengthLimitMessage).replace("{max-message-length}", String.valueOf(plugin.getConfig().getInt("max-message-length")));
                    player.getPlayer().sendMessage(messageLengthLimitMessage);
                 e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + plugin.getConfig().getString("message-length-limit-message"));
            }
        }
    }
}

}
