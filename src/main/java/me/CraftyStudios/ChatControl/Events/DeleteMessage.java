package me.CraftyStudios.ChatControl.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.CraftyStudios.ChatControl.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class DeleteMessage implements Listener {
  private final JavaPlugin plugin;

  public DeleteMessage(JavaPlugin plugin) {
      this.plugin = plugin;
  }

  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    String message = event.getMessage();
    
    TextComponent messageComponent = new TextComponent(ChatColor.GRAY + "[" + ChatColor.RED + "X" + ChatColor.GRAY + "] " + ChatColor.RESET + message);
    messageComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deletemessage " + message));
    messageComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to delete this message")));


    
    for (Player online : Bukkit.getOnlinePlayers()) {
      if (plugin.getConfig().getBoolean("DeleteMessage") == true) {
        if (online.hasPermission("CraftyChatControl.message.delete")) {
          online.spigot().sendMessage(ChatMessageType.CHAT, messageComponent);
        } else {
          online.sendMessage(ChatColor.RESET + message);
        }
      } else {
        online.sendMessage(ChatColor.RESET + message);
      }
    }
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command can only be used by players.");
      return true;
    }
    Player player = (Player) sender;
    if (!player.hasPermission("CraftyChatControl.message.delete")) {
      player.getPlayer().sendMessage(Main.noPermission);
      return true;
    }
    if (args.length == 0) {
      player.sendMessage(ChatColor.RED + "Usage: /deletemessage <message>");
      return true;
    }
    String message = args[0];
    for (Player online : Bukkit.getOnlinePlayers()) {
      online.sendMessage(ChatColor.RED + "[Deleted Message]: " + message);
    }return false;
  }
}
