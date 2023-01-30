package me.CraftyStudios.ChatControl.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandExecutor;

public class help implements CommandExecutor, TabExecutor {
    private final JavaPlugin plugin;

    public help(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("help")) {
            if (args.length == 1) {
   
                }
            }
        
        return completions;
    }
    List<String> content = plugin.getConfig().getStringList("Help-Command");

 

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    sender.sendMessage(plugin.getConfig().getString("prefix").replaceAll("&", "§") + content);
  }

}