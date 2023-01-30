package me.CraftyStudios.ChatControl.GUIs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.*;





public class ccgui implements Listener, CommandExecutor, TabCompleter {
    private boolean chatLocked = false;
    private final JavaPlugin plugin;

    public boolean isChatLocked() {
        return chatLocked;
    }

    public ccgui(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event, boolean chatLocked) {
        if (!event.getView().getTitle().equals(plugin.getConfig().getString("MainGui-Title"))) return;
        event.setCancelled(true);

        ItemStack item = event.getCurrentItem();
        if (item == null || item.getType() == Material.BLACK_STAINED_GLASS_PANE) return;
            event.setCancelled(true);

        if (event.getSlot() == 10) {
               if (chatLocked = true) {
                   chatLocked = false;
               } else {
                   chatLocked = true;
               }
            event.setCancelled(true);
           }

        }
    

public void openGUI(Player player, boolean chatLocked) {
    Inventory gui = Bukkit.createInventory(null, 45, plugin.getConfig().getString("MainGui-Title"));
    ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    ItemMeta meta = filler.getItemMeta();
    meta.setDisplayName("§8");
    filler.setItemMeta(meta);
    for (int i = 0; i < 45; i++) {
    if (i != 10) {
        if (chatLocked = true) {
            ItemStack chatLockedTrue = new ItemStack(Material.PAPER);
            ItemMeta chatLockedTrueMeta = chatLockedTrue.getItemMeta();
            chatLockedTrueMeta.setDisplayName(plugin.getConfig().getString("MainGui-ChatLockedDown-Name"));
            List<String> lore = plugin.getConfig().getStringList("MainGui-ChatLockedDown-Lore");
            chatLockedTrueMeta.setLore(lore);
            chatLockedTrue.setItemMeta(chatLockedTrueMeta);
            gui.setItem(4, chatLockedTrue);
        } else {
            ItemStack chatLockedFalse = new ItemStack(Material.PAPER);
            ItemMeta chatLockedFalseMeta = chatLockedFalse.getItemMeta();
            chatLockedFalseMeta.setDisplayName(plugin.getConfig().getString("MainGui-ChatNotLockedDown-Name"));
            List<String> lore = plugin.getConfig().getStringList("MainGui-ChatNotLockedDown-Lore");
            chatLockedFalseMeta.setLore(lore);
            chatLockedFalse.setItemMeta(chatLockedFalseMeta);
            gui.setItem(4, chatLockedFalse);
       
    }
    }
    }
    player.openInventory(gui);
    
}

      




    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("ccgui")) {
            if (args.length == 1) {
                // Add possible subcommands to the completions list
            }
        }
        return completions;
    }

   


	private void openGUI(Player player) {
	}
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ccgui")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("ccgui.use")) {
                    openGUI(player);
                } else {
                    player.sendMessage(plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("no-permission"));
                }
            } else {
                sender.sendMessage("You must be a player to use this command!");
            }
            }
        return false;
        }
    }






