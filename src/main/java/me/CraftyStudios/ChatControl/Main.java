package me.CraftyStudios.ChatControl;
import me.CraftyStudios.ChatControl.Events.AutoAnnounceTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.CraftyStudios.ChatControl.utils.Logger;
import me.CraftyStudios.ChatControl.Commands.*;
import me.CraftyStudios.ChatControl.GUIs.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Main extends JavaPlugin implements Listener {
  public static String prefix;
  Map<String, Long> mutedPlayers;
  Map<String, Boolean> lockdownStatus = new HashMap<>();
  Map<Player, String> messages;
  public static String noPermission;
  Plugin plugin = this;






  public static void init(JavaPlugin plugin) {
    prefix = plugin.getConfig().getString("Prefix");
  }


    @Override
    public void onEnable() {
      List<String> announcements = getConfig().getStringList("announcements");
      BukkitScheduler scheduler = Bukkit.getScheduler();
      int interval = getConfig().getInt("announcement-interval") * 20; // convert seconds to ticks
      scheduler.runTaskTimer(this, new AutoAnnounceTask(this, announcements), 0, interval);


      noPermission = getConfig().getString("no-permission");
      noPermission = ChatColor.translateAlternateColorCodes('&', noPermission);
      
      // Plugin startup logic

      saveDefaultConfig();
      saveDefaultConfig();
      Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
      Logger.log(Logger.LogLevel.SUCCESS, "Loading Crafty Chat Control...");
      Logger.log(Logger.LogLevel.SUCCESS, "Loaded!");
      Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
      Logger.log(Logger.LogLevel.INFO, "Join our Discord!");
      Logger.log(Logger.LogLevel.INFO, "https://discord.gg/r6rmzHbPGT");
      Logger.log(Logger.LogLevel.INFO, "Request a feature or report a bug!");
      Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
      // Auto announce
      
      
      // Commands
      //getCommand("<command_name>").setExecutor(new CustomCommand(this));
      //getCommand("<command_name>").setTabCompleter(new CustomCommand(this));
      getCommand("edit").setExecutor(new Edit(messages, this));
      getCommand("cchelp").setExecutor(new cchelp(this));
      getCommand("ccreload").setExecutor(new ccreload(this));
      getCommand("deletemessage");
      getCommand("mute").setExecutor(new mute(this));
      getCommand("unmute").setExecutor(new unmute(mutedPlayers, this));
      getCommand("lockdown").setExecutor(new lockdown(this));
      // getCommand("unlockdown").setExecutor(new unlockdown(this, lockdownStatus, prefix));
      // getCommand("unlockdown").setTabCompleter(new unlockdown(this, lockdownStatus, prefix));
      getCommand("ccgui").setExecutor(new ccgui(this));

      // Events
      getServer().getPluginManager().registerEvents(this, this);
      
      // Events

      // Placeholder API






      //Placeholder API Check
       if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
        Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
        Logger.log(Logger.LogLevel.ERROR, "PlaceholderAPI not found!");
        Logger.log(Logger.LogLevel.ERROR, "Disabling plugin...");
        Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
        Bukkit.getPluginManager().disablePlugin(this);
        return;
    

  
  }
 
   
}
 
@Override
public void onDisable() {
    Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");
    Logger.log(Logger.LogLevel.SUCCESS, "Unloaded Crafty Chat Control");
    Logger.log(Logger.LogLevel.SUCCESS, "See you later!");
    Logger.log(Logger.LogLevel.OUTLINE, "------------------------------------");  
}
}




  
