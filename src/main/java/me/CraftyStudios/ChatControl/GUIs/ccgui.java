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
import org.bukkit.ChatColor;
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
        if (event.getSlot() == 12) {
            if (plugin.getConfig().getBoolean("antiCapsLock-enabled") == true);
                plugin.getConfig().set("antiCapsLock-enabled", false);
                plugin.saveConfig();
            } else {
                plugin.getConfig().set("antiCapsLock-enabled", true);
                plugin.saveConfig();
            }
        
        if (event.getSlot() == 14) {
            if (plugin.getConfig().getBoolean("antiSpam-enabled") == true);
                plugin.getConfig().set("antiSpam-enabled", false);
                plugin.saveConfig();
            } else {
                plugin.getConfig().set("antiSpam-enabled", true);
                plugin.saveConfig();
            }
        if (event.getSlot() == 16) {
            if (plugin.getConfig().getBoolean("autorespond-enabled") == true);
                plugin.getConfig().set("autorespond-enabled", false);
                plugin.saveConfig();
            } else {
                plugin.getConfig().set("autorespond-enabled", true);
                plugin.saveConfig();
            }
        if (event.getSlot() == 20) {
            if (plugin.getConfig().getBoolean("auto-announce-enabled") == true) {
                plugin.getConfig().set("auto-announce-enabled", false);
                plugin.saveConfig();
            } else {
                plugin.getConfig().set("auto-announce-enabled", true);
                plugin.saveConfig();
            }
        }
        if (event.getSlot() == 22) {
            if (plugin.getConfig().getBoolean("antiSwear-enabled") == true) {
                plugin.getConfig().set("antiSwear-enabled", false);
                plugin.saveConfig();
            } else {
                plugin.getConfig().set("antiSwear-enabled", true);
                plugin.saveConfig();
            }
        }
        if (event.getSlot() == 24) {
            if (plugin.getConfig().getBoolean("message-cooldown-enabled") == true) {
                plugin.getConfig().set("message-cooldown-enabled", false);
                plugin.saveConfig();
            } else {
                plugin.getConfig().set("message-cooldown-enabled", true);
                plugin.saveConfig();
            }
        }
        if (event.getSlot() == 28) {
            if (plugin.getConfig().getBoolean("message-length-limit-enabled") == true) {
                plugin.getConfig().set("message-length-limit-enabled", false);
                plugin.saveConfig();
            } else {
                plugin.getConfig().set("message-length-limit-enabled", true);
                plugin.saveConfig();
            }
        }
        if (event.getSlot() == 30) {
            if (plugin.getConfig().getBoolean("join-messages-enabled") == true) {
                plugin.getConfig().set("join-messages-enabled", false);
                plugin.saveConfig();
            } else {
                plugin.getConfig().set("join-messages-enabled", true);
                plugin.saveConfig();
            }
        }
        if (event.getSlot() == 32) {
            if (plugin.getConfig().getBoolean("leave-messages-enabled") == true) {
                plugin.getConfig().set("leave-messages-enabled", false);
                plugin.saveConfig();
            } else {
                plugin.getConfig().set("leave-messages-enabled", true);
                plugin.saveConfig();
            }
        }
        if (event.getSlot() == 34) {
            if (plugin.getConfig().getBoolean("url-blocker-enabled") == true) {
                plugin.getConfig().set("url-blocker-enabled", false);
                plugin.saveConfig();
            } else {
                plugin.getConfig().set("url-blocker-enabled", true);
                plugin.saveConfig();
            }
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
        if (chatLocked == true) {
        ItemStack chatLockedTrue = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta chatLockedTrueMeta = chatLockedTrue.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-ChatLockedDown-Name");
        chatLockedTrueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-ChatLockedDown-Lore");
        for (int j = 0; j < lore.size(); j++) {
        lore.set(j, ChatColor.translateAlternateColorCodes('&', lore.get(j)));
        }
        chatLockedTrueMeta.setLore(lore);
        chatLockedTrue.setItemMeta(chatLockedTrueMeta);
        gui.setItem(10, chatLockedTrue);
    } else {
        ItemStack chatLockedFalse = new ItemStack(Material.PAPER);
        ItemMeta chatLockedFalseMeta = chatLockedFalse.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-ChatNotLockedDown-Name");
        chatLockedFalseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-ChatNotLockedDown-Lore");
        for (int a = 0; a < lore.size(); a++) {
            lore.set(a, ChatColor.translateAlternateColorCodes('&', lore.get(a)));
        }
        chatLockedFalseMeta.setLore(lore);
        chatLockedFalse.setItemMeta(chatLockedFalseMeta);            
        gui.setItem(10, chatLockedFalse);   
    }
    }
    if (i != 12) {
        if (plugin.getConfig().getBoolean("antiCapsLock-enabled") == true) {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-CapsLockEnabled-Material"));
        ItemStack capsLockTrue = new ItemStack(itemMaterial);
        ItemMeta capsLockTrueMeta = capsLockTrue.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-CapsLockEnabled-Name");
        capsLockTrueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-CapsLockEnabled-Lore");
        for (int b = 0; b < lore.size(); b++) {
            lore.set(b, ChatColor.translateAlternateColorCodes('&', lore.get(b)));
        }
        capsLockTrueMeta.setLore(lore);
        capsLockTrue.setItemMeta(capsLockTrueMeta);
        gui.setItem(12, capsLockTrue);
    } else {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-CapsLockDisabled-Material"));
        ItemStack capsLockFalse = new ItemStack(itemMaterial);
        ItemMeta capsLockFalseMeta = capsLockFalse.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-CapsLockDisabled-Name");
        capsLockFalseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-CapsLockDisabled-Lore");
        for (int c = 0; c < lore.size(); c++) {
            lore.set(c, ChatColor.translateAlternateColorCodes('&', lore.get(c)));
        }
        capsLockFalseMeta.setLore(lore);
        capsLockFalse.setItemMeta(capsLockFalseMeta);
        gui.setItem(12, capsLockFalse);
    }
    }
    if (i != 14) {
        if (plugin.getConfig().getBoolean("antiSpam-enabled") == true) {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-SpamEnabled-Material"));
        ItemStack spamTrue = new ItemStack(itemMaterial);
        ItemMeta spamTrueMeta = spamTrue.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-SpamEnabled-Name");
        spamTrueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-SpamEnabled-Lore");
        for (int d = 0; d < lore.size(); d++) {
            lore.set(d, ChatColor.translateAlternateColorCodes('&', lore.get(d)));
        }
        spamTrueMeta.setLore(lore);
        spamTrue.setItemMeta(spamTrueMeta);
        gui.setItem(14, spamTrue);
    } else {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-SpamDisabled-Material"));
        ItemStack spamFalse = new ItemStack(itemMaterial);
        ItemMeta spamFalseMeta = spamFalse.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-SpamDisabled-Name");
        spamFalseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-SpamDisabled-Lore");
        for (int e = 0; e < lore.size(); e++) {
            lore.set(e, ChatColor.translateAlternateColorCodes('&', lore.get(e)));
        }
        spamFalseMeta.setLore(lore);
        spamFalse.setItemMeta(spamFalseMeta);
        gui.setItem(14, spamFalse);
    }
    player.openInventory(gui);

    }
    if (i != 16) {
        if (plugin.getConfig().getBoolean("autorespond-enabled") == true) {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-AutoRespondEnabled-Material"));
        ItemStack autoRespondTrue = new ItemStack(itemMaterial);
        ItemMeta autoRespondTrueMeta = autoRespondTrue.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-AutoRespondEnabled-Name");
        autoRespondTrueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-AutoRespondEnabled-Lore");
        for (int f = 0; f < lore.size(); f++) {
            lore.set(f, ChatColor.translateAlternateColorCodes('&', lore.get(f)));
        }
        autoRespondTrueMeta.setLore(lore);
        autoRespondTrue.setItemMeta(autoRespondTrueMeta);
        gui.setItem(16, autoRespondTrue);
    } else {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-AutoRespondDisabled-Material"));
        ItemStack autoRespondFalse = new ItemStack(itemMaterial);
        ItemMeta autoRespondFalseMeta = autoRespondFalse.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-AutoRespondDisabled-Name");
        autoRespondFalseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-AutoRespondDisabled-Lore");
        for (int g = 0; g < lore.size(); g++) {
            lore.set(g, ChatColor.translateAlternateColorCodes('&', lore.get(g)));
        }
        autoRespondFalseMeta.setLore(lore);
        autoRespondFalse.setItemMeta(autoRespondFalseMeta);
        gui.setItem(16, autoRespondFalse);
    }
    }
    if (i != 20) {
        if(plugin.getConfig().getBoolean("auto-announce-enabled") == true) {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-AutoAnnounceEnabled-Material"));
        ItemStack autoAnnounceTrue = new ItemStack(itemMaterial);
        ItemMeta autoAnnounceTrueMeta = autoAnnounceTrue.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-AutoAnnounceEnabled-Name");
        autoAnnounceTrueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-AutoAnnounceEnabled-Lore");
        for (int h = 0; h < lore.size(); h++) {
            lore.set(h, ChatColor.translateAlternateColorCodes('&', lore.get(h)));
        }
        autoAnnounceTrueMeta.setLore(lore);
        autoAnnounceTrue.setItemMeta(autoAnnounceTrueMeta);
        gui.setItem(20, autoAnnounceTrue);
    } else {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-AutoAnnounceDisabled-Material"));
        ItemStack autoAnnounceFalse = new ItemStack(itemMaterial);
        ItemMeta autoAnnounceFalseMeta = autoAnnounceFalse.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-AutoAnnounceDisabled-Name");
        autoAnnounceFalseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-AutoAnnounceDisabled-Lore");
        for (int j = 0; j < lore.size(); j++) {
            lore.set(j, ChatColor.translateAlternateColorCodes('&', lore.get(j)));
        }
        autoAnnounceFalseMeta.setLore(lore);
        autoAnnounceFalse.setItemMeta(autoAnnounceFalseMeta);
        gui.setItem(20, autoAnnounceFalse);
    }
    }
    if (i != 22) {
        if(plugin.getConfig().getBoolean("antiSwear-enabled") == true) {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-AntiSwearingEnabled-Material"));
        ItemStack antiSwearingTrue = new ItemStack(itemMaterial);
        ItemMeta antiSwearingTrueMeta = antiSwearingTrue.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-AntiSwearingEnabled-Name");
        antiSwearingTrueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-AntiSwearingEnabled-Lore");
        for (int k = 0; k < lore.size(); k++) {
            lore.set(k, ChatColor.translateAlternateColorCodes('&', lore.get(k)));
        }
        antiSwearingTrueMeta.setLore(lore);
        antiSwearingTrue.setItemMeta(antiSwearingTrueMeta);
        gui.setItem(22, antiSwearingTrue);
    } else {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-AntiSwearingDisabled-Material"));
        ItemStack antiSwearingFalse = new ItemStack(itemMaterial);
        ItemMeta antiSwearingFalseMeta = antiSwearingFalse.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-AntiSwearingDisabled-Name");
        antiSwearingFalseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-AntiSwearingDisabled-Lore");
        for (int l = 0; l < lore.size(); l++) {
            lore.set(l, ChatColor.translateAlternateColorCodes('&', lore.get(l)));
        }
        antiSwearingFalseMeta.setLore(lore);
        antiSwearingFalse.setItemMeta(antiSwearingFalseMeta);
        gui.setItem(22, antiSwearingFalse);
    }
    }
    if (i != 24) {
        if (plugin.getConfig().getBoolean("message-cooldown-enabled") == true) {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-MessageCooldownEnabled-Material"));
        ItemStack messageCooldownTrue = new ItemStack(itemMaterial);
        ItemMeta messageCooldownTrueMeta = messageCooldownTrue.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-MessageCooldownEnabled-Name");
        messageCooldownTrueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-MessageCooldownEnabled-Lore");
        for (int m = 0; m < lore.size(); m++) {
            lore.set(m, ChatColor.translateAlternateColorCodes('&', lore.get(m)));
        }
        messageCooldownTrueMeta.setLore(lore);
        messageCooldownTrue.setItemMeta(messageCooldownTrueMeta);
        gui.setItem(24, messageCooldownTrue);
    } else {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-MessageCooldownDisabled-Material"));
        ItemStack messageCooldownFalse = new ItemStack(itemMaterial);
        ItemMeta messageCooldownFalseMeta = messageCooldownFalse.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-MessageCooldownDisabled-Name");
        messageCooldownFalseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-MessageCooldownDisabled-Lore");
        for (int n = 0; n < lore.size(); n++) {
            lore.set(n, ChatColor.translateAlternateColorCodes('&', lore.get(n)));
        }
        messageCooldownFalseMeta.setLore(lore);
        messageCooldownFalse.setItemMeta(messageCooldownFalseMeta);
        gui.setItem(24, messageCooldownFalse);
    }
    }
    if (i != 28) {
        if(plugin.getConfig().getBoolean("message-length-limit-enabled") == true) {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-MessageLengthLimitEnabled-Material"));
        ItemStack messageLengthLimitTrue = new ItemStack(itemMaterial);
        ItemMeta messageLengthLimitTrueMeta = messageLengthLimitTrue.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-MessageLengthLimitEnabled-Name");
        messageLengthLimitTrueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-MessageLengthLimitEnabled-Lore");
        for (int o = 0; o < lore.size(); o++) {
            lore.set(o, ChatColor.translateAlternateColorCodes('&', lore.get(o)));
        }
        messageLengthLimitTrueMeta.setLore(lore);
        messageLengthLimitTrue.setItemMeta(messageLengthLimitTrueMeta);
        gui.setItem(28, messageLengthLimitTrue);
    } else {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-MessageLengthLimitDisabled-Material"));
        ItemStack messageLengthLimitFalse = new ItemStack(itemMaterial);
        ItemMeta messageLengthLimitFalseMeta = messageLengthLimitFalse.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-MessageLengthLimitDisabled-Name");
        messageLengthLimitFalseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-MessageLengthLimitDisabled-Lore");
        for (int p = 0; p < lore.size(); p++) {
            lore.set(p, ChatColor.translateAlternateColorCodes('&', lore.get(p)));
        }
        messageLengthLimitFalseMeta.setLore(lore);
        messageLengthLimitFalse.setItemMeta(messageLengthLimitFalseMeta);
        gui.setItem(28, messageLengthLimitFalse);
    }
    }
    if (i != 30) {
        if(plugin.getConfig().getBoolean("join-messages-enabled") == true) {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-JoinMessagesEnabled-Material"));
        ItemStack joinMessagesTrue = new ItemStack(itemMaterial);
        ItemMeta joinMessagesTrueMeta = joinMessagesTrue.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-JoinMessagesEnabled-Name");
        joinMessagesTrueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-JoinMessagesEnabled-Lore");
        for (int q = 0; q < lore.size(); q++) {
            lore.set(q, ChatColor.translateAlternateColorCodes('&', lore.get(q)));
        }
        joinMessagesTrueMeta.setLore(lore);
        joinMessagesTrue.setItemMeta(joinMessagesTrueMeta);
        gui.setItem(30, joinMessagesTrue);
    } else {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-JoinMessagesDisabled-Material"));
        ItemStack joinMessagesFalse = new ItemStack(itemMaterial);
        ItemMeta joinMessagesFalseMeta = joinMessagesFalse.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-JoinMessagesDisabled-Name");
        joinMessagesFalseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-JoinMessagesDisabled-Lore");
        for (int r = 0; r < lore.size(); r++) {
            lore.set(r, ChatColor.translateAlternateColorCodes('&', lore.get(r)));
        }
        joinMessagesFalseMeta.setLore(lore);
        joinMessagesFalse.setItemMeta(joinMessagesFalseMeta);
        gui.setItem(30, joinMessagesFalse);
    }
    }
    if (i != 32) {
        if(plugin.getConfig().getBoolean("leave-messages-enabled") == true) {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-LeaveMessagesEnabled-Material"));
        ItemStack leaveMessagesTrue = new ItemStack(itemMaterial);
        ItemMeta leaveMessagesTrueMeta = leaveMessagesTrue.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-LeaveMessagesEnabled-Name");
        leaveMessagesTrueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-LeaveMessagesEnabled-Lore");
        for (int s = 0; s < lore.size(); s++) {
            lore.set(s, ChatColor.translateAlternateColorCodes('&', lore.get(s)));
        }
        leaveMessagesTrueMeta.setLore(lore);
        leaveMessagesTrue.setItemMeta(leaveMessagesTrueMeta);
        gui.setItem(32, leaveMessagesTrue);
    } else {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-LeaveMessagesDisabled-Material"));
        ItemStack leaveMessagesFalse = new ItemStack(itemMaterial);
        ItemMeta leaveMessagesFalseMeta = leaveMessagesFalse.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-LeaveMessagesDisabled-Name");
        leaveMessagesFalseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-LeaveMessagesDisabled-Lore");
        for (int t = 0; t < lore.size(); t++) {
            lore.set(t, ChatColor.translateAlternateColorCodes('&', lore.get(t)));
        }
        leaveMessagesFalseMeta.setLore(lore);
        leaveMessagesFalse.setItemMeta(leaveMessagesFalseMeta);
        gui.setItem(32, leaveMessagesFalse);
    }
    }
    if (i != 34) {
        if(plugin.getConfig().getBoolean("url-blocker-enabled") == true) {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-UrlBlockerEnabled-Material"));
        ItemStack urlBlockerTrue = new ItemStack(itemMaterial);
        ItemMeta urlBlockerTrueMeta = urlBlockerTrue.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-UrlBlockerEnabled-Name");
        urlBlockerTrueMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-UrlBlockerEnabled-Lore");
        for (int u = 0; u < lore.size(); u++) {
            lore.set(u, ChatColor.translateAlternateColorCodes('&', lore.get(u)));
        }
        urlBlockerTrueMeta.setLore(lore);
        urlBlockerTrue.setItemMeta(urlBlockerTrueMeta);
        gui.setItem(34, urlBlockerTrue);
    } else {
        Material itemMaterial = Material.getMaterial(plugin.getConfig().getString("MainGui-UrlBlockerDisabled-Material"));
        ItemStack urlBlockerFalse = new ItemStack(itemMaterial);
        ItemMeta urlBlockerFalseMeta = urlBlockerFalse.getItemMeta();
        String displayName = plugin.getConfig().getString("MainGui-UrlBlockerDisabled-Name");
        urlBlockerFalseMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        List<String> lore = plugin.getConfig().getStringList("MainGui-UrlBlockerDisabled-Lore");
        for (int v = 0; v < lore.size(); v++) {
            lore.set(v, ChatColor.translateAlternateColorCodes('&', lore.get(v)));
        }
        urlBlockerFalseMeta.setLore(lore);
        urlBlockerFalse.setItemMeta(urlBlockerFalseMeta);
        gui.setItem(34, urlBlockerFalse);
    }
    }
    }
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






