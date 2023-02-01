package me.CraftyStudios.ChatControl.Commands;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import me.CraftyStudios.ChatControl.Main;

public class Edit implements CommandExecutor {
    private final JavaPlugin plugin;

 
  private final Map<Player, String> messages;

  public Edit(Map<Player, String> messages, Main plugin) {
    this.messages = messages;
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
if (plugin.getConfig().getBoolean(label, "Edit-Command" == "true")) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command can only be executed by a player.");
      return false;
    }

    Player player = (Player) sender;

    if (args.length != 1) {
      player.sendMessage("Usage: /edit <new-message>");
      return false;
    }

    String newMessage = args[0];
    messages.put(player, newMessage);
    player.sendMessage("Message edited successfully.");

    return true;
  }else {
    return false;
  }
}
  
  public void sendMessage(Player player, String message) {
    TextComponent originalMessage = new TextComponent(player.getName() + ": " + message);
    TextComponent editTag = new TextComponent(ChatColor.GRAY + " [" + ChatColor.YELLOW + "Edit" + ChatColor.GRAY + "]");
    editTag.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/edit"));
    editTag.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to edit message")));
    player.spigot().sendMessage(originalMessage, editTag);
  }
}

