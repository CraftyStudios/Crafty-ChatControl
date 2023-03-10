package me.CraftyStudios.ChatControl.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class URLBlocker implements Listener {
    private JavaPlugin plugin;

    public URLBlocker(JavaPlugin plugin) {
        this.plugin = plugin;
    }

      private static final String URL_REGEX = "(?:(?:https?|ftp|file)://|www\\.|ftp\\.)[-A-Z0-9+&@#/%=_|$?!:,.]*[A-Z0-9+&@#/%=_|$]|(?<![A-Za-z0-9])\\.[A-Za-z]{2,}(?![A-Za-z0-9])";
// Boolean to check if model is enabled in config
    public boolean isEnabled() {
        return plugin.getConfig().getBoolean("url-blocker-enabled");
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (isEnabled() == true) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        
        if (!player.hasPermission("chatcontrol.urlblocker.bypass")) {
       
        if (message.matches(URL_REGEX)) {
            String urlBlockerMessage = plugin.getConfig().getString("url-blocker-message");
            urlBlockerMessage = urlBlockerMessage.replace("{player}", player.getName());
            player.sendMessage(urlBlockerMessage);
            event.setCancelled(true);
        }
        }
    }
}
}


