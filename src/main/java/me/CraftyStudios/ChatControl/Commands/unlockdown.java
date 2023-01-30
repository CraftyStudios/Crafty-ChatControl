package me.CraftyStudios.ChatControl.Commands;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class unlockdown implements CommandExecutor, TabCompleter {
    public final JavaPlugin plugin;


  private final Map<String, Boolean> lockdownStatus;
  private final String prefix;

  public unlockdown(JavaPlugin plugin,Map<String, Boolean> lockdownStatus, String prefix) {
    this.lockdownStatus = lockdownStatus;
    this.prefix = prefix;
    this.plugin = plugin;
  }
  

  @Override
  public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
    List<String> completions = new ArrayList<>();
    if (cmd.getName().equalsIgnoreCase("unlockdown")) {
      if (args.length == 0) {
        completions.add("");
      }
    }
    return completions;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (args.length != 0) {
      sender.sendMessage(prefix + "Usage: /unlockdown");
      return false;
    }

    if (!lockdownStatus.containsValue(true)) {
      sender.sendMessage(prefix + "The server is not in lockdown mode.");
      return false;
    }

    lockdownStatus.put("status", false);
    sender.sendMessage(prefix + "The server lockdown has been lifted.");
    return true;
  }
}
