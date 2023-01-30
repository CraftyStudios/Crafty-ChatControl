package me.CraftyStudios.ChatControl.Events;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class swearDetect extends JavaPlugin implements Listener {

public JavaPlugin plugin;

private boolean isHashed = plugin.getConfig().getBoolean("hash-swear-words");



public boolean isHashed() {
        return isHashed;
    }

    public void setHashed(boolean isHashed) {
        this.isHashed = isHashed;
    }
public swearDetect(JavaPlugin plugin) {
        this.plugin = plugin;
    }



List<String> swearWords = plugin.getConfig().getStringList("swear-words");

@EventHandler
public void onPlayerChat(AsyncPlayerChatEvent event) {
    if (isHashed == true) {
    String message = event.getMessage();
    for (String swearWord : swearWords) {
        if (message.contains(swearWord)) {
            event.getPlayer().sendMessage(plugin.getConfig().getString("swear-message-warning"));
            event.setCancelled(true);
            return;
        }
    }
    } else {
        String message = event.getMessage();
        for (String swearWord : swearWords) {
        int length = swearWord.length();
        String asterisks = new String(new char[length]).replace("\0", "*");
        message = message.replace(swearWord, asterisks);
}
        event.setMessage(message);

    }
}

}
    
    