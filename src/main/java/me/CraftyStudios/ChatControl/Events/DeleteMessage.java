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

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class DeleteMessage extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    getCommand("deletemessage").setExecutor(this);
  }

  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    String message = event.getMessage();
    
    TextComponent messageComponent = new TextComponent(ChatColor.RED + "[X] " + ChatColor.RESET + message);
    messageComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deletemessage " + message));
    messageComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Click to delete this message")));


    
    for (Player online : Bukkit.getOnlinePlayers()) {
      if (online.hasPermission("CraftyChatControl.message.view")) {
        online.spigot().sendMessage(ChatMessageType.CHAT, messageComponent);
      } else {
        online.sendMessage(ChatColor.GRAY + "[X] " + ChatColor.RESET + message);
      }
    }
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command can only be used by players.");
      return true;
    }
    Player player = (Player) sender;
    if (!player.hasPermission("CraftyChatControl.message.delete")) {
      player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
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
