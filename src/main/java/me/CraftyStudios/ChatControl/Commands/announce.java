package me.CraftyStudios.ChatControl.Commands;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;

public class announce extends CommandExecutor {

  private final JavaPlugin plugin;

  public announce(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("announce.use")) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("noPermissionMessage")));
      return true;
    }
    
    if (args.length == 0) {
      sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("noMessageProvidedMessage")));
      return true;
    }
    
    String message = ChatColor.translateAlternateColorCodes('&', String.join(" ", args));
    plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("broadcastMessage").replace("{message}", message)));
    plugin.getServer().getOnlinePlayers().forEach(player -> {
      player.sendTitle(
          ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("titleMessage.title")), 
          ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("titleMessage.subtitle").replace("{message}", message)), 
          10, 70, 20);
      BossBar bossBar = plugin.getServer().createBossBar(
          ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("bossBarMessage").replace("{message}", message)), 
          BarColor.BLUE, BarStyle.SEGMENTED_10);
      bossBar.addPlayer(player);
      bossBar.setVisible(true);
    });
    return true;
  }
}
