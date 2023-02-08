package me.CraftyStudios.ChatControl.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class NicknameSystem implements CommandExecutor {

public JavaPlugin plugin;


private final HashMap<Player, String> nicknames = new HashMap<>();

@Override
public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
        sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
        return true;
    }
    
    Player player = (Player) sender;
    if (!player.hasPermission("chatcontrol.command.nick")) {
        if (args.length == 0) {
        

        // Remove the player's nickname
        nicknames.remove(player);
        player.setDisplayName(player.getName());
        String response = plugin.getConfig().getString("nickname-reset-message");
        response = ChatColor.translateAlternateColorCodes('&', response);
        player.getPlayer().sendMessage(response);
    } else if (args.length == 1) {
        String nickname = args[0];
        // Set the player's nickname
        String newNickMessage = plugin.getConfig().getString("nickname-set-message");
        newNickMessage = ChatColor.translateAlternateColorCodes('&', newNickMessage);
        player.sendMessage(newNickMessage);
        nicknames.put(player, nickname);
        player.setDisplayName(nickname);
    }
    
    // Update the player list
    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
        onlinePlayer.setPlayerListName(nicknames.getOrDefault(onlinePlayer, onlinePlayer.getName()));
    }
    
    return true;
}
	return false;

}

}