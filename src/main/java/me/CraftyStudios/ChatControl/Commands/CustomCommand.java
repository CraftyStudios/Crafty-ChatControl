package me.CraftyStudios.ChatControl.Commands;


import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomCommand implements CommandExecutor {
    private JavaPlugin plugin;

    public CustomCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("commands");
        if (section == null) {
            return false;
        }

        for (String key : section.getKeys(false)) {
            if (cmd.getName().equalsIgnoreCase(key)) {
                List<String> messages = section.getStringList(key);
                for (String message : messages) {
                    sender.sendMessage(message.replace("&", "§"));
                }
                return true;
            }
        }
        return false;
    }
}
