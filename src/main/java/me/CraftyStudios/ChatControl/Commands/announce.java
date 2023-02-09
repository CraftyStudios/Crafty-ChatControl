package me.CraftyStudios.ChatControl.Commands;

import org.bukkit.plugin.java.JavaPlugin;

import me.CraftyStudios.ChatControl.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;

public class announce extends JavaPlugin {

  private final JavaPlugin plugin;

  public announce(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("announce.use")) {
      Player player = (Player) sender;
      player.getPlayer().sendMessage(Main.noPermission);
      return true;
    }
    else {
    
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
}
