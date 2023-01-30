package me.CraftyStudios.ChatControl.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class DeleteMessageCommand implements CommandExecutor {
  private final JavaPlugin plugin;

  public DeleteMessageCommand(JavaPlugin plugin) {
      this.plugin = plugin;
  }
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command can only be executed by a player.");
      return false;
    }
    if (!sender.hasPermission("CraftyChatControl.message.delete")) {
      sender.sendMessage("You don't have permission to delete messages.");
      return false;
    }
    if (args.length != 1) {
      sender.sendMessage("Usage: /deletemessage <message>");
      return false;
    }
    String message = args[0];
    
    for (Player online : Bukkit.getOnlinePlayers()) {
      online.sendMessage(ChatColor.RED + "[Deleted Message]: " + message);
    }
    
    return true;
  }
}
