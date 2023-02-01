# chat-control



# Save for later code:
```
package me.CraftyStudios.ChatControl.Commands;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Edit implements CommandExecutor {
  private final Map<String, String> messages;

  public Edit(Map<String, String> messages) {
    this.messages = messages;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command can only be executed by a player.");
      return false;
    }

    Player player = (Player) sender;

    if (args.length != 2) {
      player.sendMessage("Usage: /edit <message-id> <new-message>");
      return false;
    }

    String messageId = args[0];
    String newMessage = args[1];

    if (!messages.containsKey(messageId)) {
      player.sendMessage("Invalid message ID.");
      return false;
    }

    messages.put(messageId, newMessage);
    player.sendMessage("Message edited successfully.");

    return true;
  }
}


private final Map<String, String> messages = new HashMap<>();

public void storeMessage(Player player, String message) {
  String messageId = generateMessageId();
  messages.put(messageId, message);
  player.sendMessage("Your message ID is: " + messageId);
}

private String generateMessageId() {
  return UUID.randomUUID().toString();
}
```