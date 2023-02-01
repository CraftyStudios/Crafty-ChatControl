package me.CraftyStudios.ChatControl.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandExecutor;

public class help implements CommandExecutor, TabCompleter {
    private final JavaPlugin plugin;
    private List<String> content;

    public help(JavaPlugin plugin) {
        this.plugin = plugin;
        this.content = plugin.getConfig().getStringList("Help-Command");
        
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


 

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    sender.sendMessage(plugin.getConfig().getString("prefix").replaceAll("&", "§") + content);
    return false;
  }
}


