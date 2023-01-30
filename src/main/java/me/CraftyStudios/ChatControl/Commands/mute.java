package me.CraftyStudios.ChatControl.Commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class mute implements CommandExecutor, TabCompleter, Listener {
    private final JavaPlugin plugin;

    public mute(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    private final Set<String> mutedPlayers = new HashSet<>();
        @EventHandler
            public void onPlayerChat(AsyncPlayerChatEvent event) {
              if (mutedPlayers.contains(event.getPlayer().getName())) {
                event.getPlayer().sendMessage("You are muted and cannot chat.");
                event.setCancelled(true);

              }
            }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("mute")) {
            if (args.length == 1) {
                // Add online players to the completions list
                for (Player player : Bukkit.getOnlinePlayers()) {
                    completions.add(player.getName());
                }
            }
        }
        return completions;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (args.length == 0) {
                if (sender.hasPermission("craftychatcontrol.mute")) {
                    if (args.length != 1) {
                        sender.sendMessage(plugin.getConfig().getString("prefix") + "Usage: /mute <player>");
                        return false;
                      }
                
                      Player target = Bukkit.getPlayer(args[0]);
                      if (target == null) {
                        sender.sendMessage(plugin.getConfig().getString("prefix") + args[0] + " is not online.");
                        return false;
                      }
                
                             if (mutedPlayers.contains(target.getName())) {
                             sender.sendMessage(plugin.getConfig().getString("prefix") + target.getName() + " is already muted.");
                             return false;
                         }
                
                      mutedPlayers.add(target.getName());
                      sender.sendMessage( plugin.getConfig().getString("prefix") + target.getName() + " has been muted.");
                      return true;
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("no-permission")));
                }return false;
                }
            }
        
            

        