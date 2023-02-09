package me.CraftyStudios.ChatControl.Events;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatLogger extends JavaPlugin implements Listener {

    private long messageId = 0;
 
    @Override
    public void onEnable() {
      getServer().getPluginManager().registerEvents(this, this);
    }
   
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
      Player player = event.getPlayer();
      String message = event.getMessage();
   
      // Write the message to the file
      try {
        File file = new File(getDataFolder(), "chat-log.txt");
        if (!file.exists()) {
          file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        fw.write("[" + messageId + "] [" + player.getName() + "]: " + message + "\n");
          fw.close();
          messageId++;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
@Override 
public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
        sender.sendMessage("This command can only be run by a player.");
        return false;
      }
   
      Player player = (Player) sender;
   
      if (args.length != 2) {
        player.sendMessage("Usage: /lookup [id-small-range] [id-big-range]");
        return false;
      }
   
      long idSmallRange;
      long idBigRange;
   
      try {
        idSmallRange = Long.parseLong(args[0]);
        idBigRange = Long.parseLong(args[1]);
      } catch (NumberFormatException e) {
        player.sendMessage("Both arguments must be valid numbers.");
        return false;
      }
   
      if (idSmallRange > idBigRange) {
        player.sendMessage("The first argument must be less than or equal to the second argument.");
        return false;
      }
   
      try {
        File file = new File(getDataFolder(), "chat-log.txt");
        if (!file.exists()) {
          player.sendMessage("No chat log file found.");
          return false;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
          String[] parts = line.split("\\s");
          long id = Long.parseLong(parts[0].substring(1, parts[0].length() - 1));
          if (id >= idSmallRange && id <= idBigRange) {
            player.sendMessage(line);
          }
        }
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
  return true;
}
  }
  