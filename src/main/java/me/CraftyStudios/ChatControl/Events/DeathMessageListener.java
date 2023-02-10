package me.CraftyStudios.ChatControl.Events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathMessageListener implements Listener {;
   private FileConfiguration config;
    JavaPlugin plugin;

   public DeathMessageListener(JavaPlugin plugin) {
      this.plugin = plugin;
      this.config = plugin.getConfig();
   }
// Boolean to detect if model is enabled in config
    public boolean modelEnabled() {
        return config.getBoolean("death-messages-enabled");
    }
   @EventHandler
   public void onEntityDeath(EntityDeathEvent event) {
    if (modelEnabled() == true) {
      if (event instanceof PlayerDeathEvent) {
         PlayerDeathEvent playerDeathEvent = (PlayerDeathEvent) event;
         Player player = playerDeathEvent.getEntity();
         String deathMessage = "";
         String name = player.getName();
            deathMessage = deathMessage.replace("{PlayerName}", name);
        
         if (playerDeathEvent.getDeathMessage().contains("fell from a high place")) {
            deathMessage = config.getString("death-messages.fall");
         } else if (playerDeathEvent.getDeathMessage().contains("burned to death")) {
            deathMessage = config.getString("death-messages.fire");
         } else if (playerDeathEvent.getDeathMessage().contains("was killed by")) {
            deathMessage = config.getString("death-messages.kill");
            Entity killer = playerDeathEvent.getEntity().getKiller();
            deathMessage = deathMessage.replace("{KillerName}", killer.getName());

            if (killer instanceof Player) {
               Player killerPlayer = (Player) killer;
               deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
            }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was shot by")) {
            deathMessage = config.getString("death-messages.shot");
            Entity killer = playerDeathEvent.getEntity().getKiller();
            deathMessage = deathMessage.replace("{KillerName}", killer.getName());

            if (killer instanceof Player) {
               Player killerPlayer = (Player) killer;
               deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
            }
            else if (killer instanceof LivingEntity) {
                LivingEntity killerLiving = (LivingEntity) killer;
                deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
            }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was pummeled by"))
         deathMessage = config.getString("death-messages.pummeled");
         Entity killer = playerDeathEvent.getEntity().getKiller();
         if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         else if (playerDeathEvent.getDeathMessage().contains("was pricked to death")) {
        deathMessage = config.getString("death-messages.walk-into-cactus");
         }
         else if (playerDeathEvent.getDeathMessage().contains("walked into a cactus whilst trying to escape")) {
        deathMessage = config.getString("death-messages.walk-into-cactus-while-escaping");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was squashed by a falling anvil")) {
        deathMessage = config.getString("death-messages.falling-anvil");
         }
         else if (playerDeathEvent.getDeathMessage().contains("drowned")) {
        deathMessage = config.getString("death-messages.drowned");
         }
        else if (playerDeathEvent.getDeathMessage().contains("drowned whilst trying to escape")) {
        deathMessage = config.getString("death-messages.drowned-while-escaping");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
        else if (playerDeathEvent.getDeathMessage().contains("experienced kinetic energy")) {
        deathMessage = config.getString("death-messages.experienced-kinetic-energy");
         }
        else if (playerDeathEvent.getDeathMessage().contains("experienced kinetic energy whilst trying to escape")) {
        deathMessage = config.getString("death-messages.experienced-kinetic-energy-while-escaping");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
        else if (playerDeathEvent.getDeathMessage().contains("blew up")) {
        deathMessage = config.getString("death-messages.blew-up");
         }
        else if (playerDeathEvent.getDeathMessage().contains("was blown up by")) {
        deathMessage = config.getString("death-messages.blown-up-by");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }

         }
         else if (playerDeathEvent.getDeathMessage().contains("was killed by [Intentional Game Design]")) {
        deathMessage = config.getString("death-messages.intentional-game-design");
         }
         else if (playerDeathEvent.getDeathMessage().contains("hit the ground too hard")) {
        deathMessage = config.getString("death-messages.hit-the-ground-too-hard");
         }
         else if (playerDeathEvent.getDeathMessage().contains("hit the ground too hard whilst trying to escape")) {
        deathMessage = config.getString("death-messages.hit-the-ground-too-hard-while-escaping");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("fell off a ladder")) {
        deathMessage = config.getString("death-messages.fell-off-a-ladder");
         }
         else if (playerDeathEvent.getDeathMessage().contains("fell off some vines")) {
        deathMessage = config.getString("death-messages.fell-off-some-vines");
         }
         else if (playerDeathEvent.getDeathMessage().contains("fell off some weeping vines")) {
        deathMessage = config.getString("death-messages.fell-off-some-weeping-vines");
         }
         else if (playerDeathEvent.getDeathMessage().contains("fell off some twisting vines")) {
        deathMessage = config.getString("death-messages.fell-off-some-twisting-vines");
         }
         else if (playerDeathEvent.getDeathMessage().contains("fell off scaffolding")) {
        deathMessage = config.getString("death-messages.fell-off-scaffolding");
         }
         else if (playerDeathEvent.getDeathMessage().contains("fell while climbing")) {
        deathMessage = config.getString("death-messages.fell-while-climbing");
         }
         else if (playerDeathEvent.getDeathMessage().contains("death.fell.accident.water")) {
        deathMessage = config.getString("death-messages.death-fell-accident-water");
         }
         else if (playerDeathEvent.getDeathMessage().contains("fell out of the world")) {
        deathMessage = config.getString("death-messages.fell-out-of-the-world");
         }
         else if (playerDeathEvent.getDeathMessage().contains("was impaled on a stalagmite")) {
        deathMessage = config.getString("death-messages.was-impaled-on-a-stalagmite");
         }
         else if (playerDeathEvent.getDeathMessage().contains("was impaled on a stalagmite whilst fighting")) {
        deathMessage = config.getString("death-messages.was-impaled-on-a-stalagmite-while-fighting");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was squashed by a falling block")) {
        deathMessage = config.getString("death-messages.was-squashed-by-a-falling-block");
         }
         else if (playerDeathEvent.getDeathMessage().contains("was squashed by a falling stalactite")) {
        deathMessage = config.getString("death-messages.was-squashed-by-a-falling-stalactite");
         }
         else if (playerDeathEvent.getDeathMessage().contains("went up in flames")) {
        deathMessage = config.getString("death-messages.went-up-in-flames");
         }
         else if (playerDeathEvent.getDeathMessage().contains("walked into a fire whilst fighting")) {
        deathMessage = config.getString("death-messages.went-up-in-flames-while-fighting");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("burned to death")) {
        deathMessage = config.getString("death-messages.burned-to-death");
         }
         else if (playerDeathEvent.getDeathMessage().contains("was burnt to a crisp whilst fighting")) {
        deathMessage = config.getString("death-messages.burned-to-death-while-fighting");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("went off with a bang")) {
        deathMessage = config.getString("death-messages.went-off-with-a-bang");
         }
         else if (playerDeathEvent.getDeathMessage().contains("went off with a bang due to a firework fired from")) {
        deathMessage = config.getString("death-messages.went-off-with-a-bang-due-to-firework");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("tried to swim in lava")) {
        deathMessage = config.getString("death-messages.tried-to-swim-in-lava");
         }
         else if (playerDeathEvent.getDeathMessage().contains("tried to swim in lava while trying to escape")) {
        deathMessage = config.getString("death-messages.tried-to-swim-in-lava-while-trying-to-escape");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was struck by lightning")) {
        deathMessage = config.getString("death-messages.was-struck-by-lightning");
         }
         else if (playerDeathEvent.getDeathMessage().contains("was struck by lightning whilst fighting")) {
        deathMessage = config.getString("death-messages.was-struck-by-lightning-while-fighting");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("discovered the floor was lava")) {
        deathMessage = config.getString("death-messages.discovered-the-floor-was-lava");
         }
         else if (playerDeathEvent.getDeathMessage().contains("walked into danger zone due to")) {
        deathMessage = config.getString("death-messages.walked-into-danger-zone-due-to");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was killed by magic")) {
        deathMessage = config.getString("death-messages.was-killed-by-magic");
         }
         else if (playerDeathEvent.getDeathMessage().contains("was killed by magic whilst trying to escape")) {
        deathMessage = config.getString("death-messages.was-killed-by-magic-while-trying-to-escape");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("using magic")) {
        deathMessage = config.getString("death-messages.died-by-player-using-magic");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was killed by")) {
        deathMessage = config.getString("death-messages.was-killed-by");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("froze to death")) {
        deathMessage = config.getString("death-messages.froze-to-death");
         }
         else if (playerDeathEvent.getDeathMessage().contains("was frozen to death by")) {
        deathMessage = config.getString("death-messages.was-frozen-to-death-by");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was slain by")) {
        deathMessage = config.getString("death-messages.was-slain-by");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was fireballed by")) {
        deathMessage = config.getString("death-messages.was-fireballed-by");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was stung to death")) {
        deathMessage = config.getString("death-messages.was-stung-to-death");
         }
         else if (playerDeathEvent.getDeathMessage().contains("was stung to death by")) {
        deathMessage = config.getString("death-messages.was-stung-to-death-by");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was shot by a skull from")) {
        deathMessage = config.getString("death-messages.was-shot-by-a-skull-from");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was obliterated by a sonically-charged shriek")) {
        deathMessage = config.getString("death-messages.was-obliterated-by-a-sonically-charged-shriek");
         }
         else if (playerDeathEvent.getDeathMessage().contains("was obliterated by a sonically-charged shriek whilst trying to escape")) {
        deathMessage = config.getString("death-messages.was-obliterated-by-a-sonically-charged-shriek-while-trying-to-escape");
         }
         else if (playerDeathEvent.getDeathMessage().contains("starved to death")) {
        deathMessage = config.getString("death-messages.starved-to-death");
         }
         else if (playerDeathEvent.getDeathMessage().contains("starved to death whilst fighting")) {
        deathMessage = config.getString("death-messages.starved-to-death-while-fighting");
         }
         else if (playerDeathEvent.getDeathMessage().contains("suffocated in a wall")) {
        deathMessage = config.getString("death-messages.suffocated-in-a-wall");
         }
         else if (playerDeathEvent.getDeathMessage().contains("suffocated in a wall whilst fighting")) {
        deathMessage = config.getString("death-messages.suffocated-in-a-wall-while-fighting");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was squished too much")) {
        deathMessage = config.getString("death-messages.was-squished-too-much");
         }
         else if (playerDeathEvent.getDeathMessage().contains("was squished by")) {
        deathMessage = config.getString("death-messages.was-squished-by");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was poked to death by a sweet berry bush")) {
        deathMessage = config.getString("death-messages.was-poked-to-death-by-a-sweet-berry-bush");
         }
         else if (playerDeathEvent.getDeathMessage().contains("was poked to death by a sweet berry bush whilst trying to escape")) {
        deathMessage = config.getString("death-messages.was-poked-to-death-by-a-sweet-berry-bush-while-trying-to-escape");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was killed trying to hurt")) {
        deathMessage = config.getString("death-messages.was-killed-trying-to-hurt");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was killed by") && playerDeathEvent.getDeathMessage().contains("trying to hurt")) {
        deathMessage = config.getString("death-messages.was-killed-by-trying-to-hurt");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was impaled by")) {
        deathMessage = config.getString("death-messages.was-impaled-by");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("was impaled by") && playerDeathEvent.getDeathMessage().contains("with")) {
        deathMessage = config.getString("death-messages.was-impaled-by-with");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("fell out of the world")){
        deathMessage = config.getString("death-messages.fell-out-of-the-world");
         }
         else if (playerDeathEvent.getDeathMessage().contains("didn't want to live in the same world as")){
        deathMessage = config.getString("death-messages.didnt-want-to-live-in-the-same-world-as");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("withered away")){
        deathMessage = config.getString("death-messages.withered-away");
         }
         else if (playerDeathEvent.getDeathMessage().contains("withered away whilst fighting")){
        deathMessage = config.getString("death-messages.withered-away-while-fighting");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         else if (playerDeathEvent.getDeathMessage().contains("died")){
        deathMessage = config.getString("death-messages.died");
         }
         else if (playerDeathEvent.getDeathMessage().contains("died because of")){
        deathMessage = config.getString("death-messages.died-because-of");
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            deathMessage = deathMessage.replace("{DeathItem}", killerPlayer.getInventory().getItemInMainHand().getType().name());
         }
         else if (killer instanceof LivingEntity) {
             LivingEntity killerLiving = (LivingEntity) killer;
             deathMessage = deathMessage.replace("{MobKiller}", killerLiving.getCustomName() == null ? killerLiving.getType().name() : killerLiving.getCustomName());
         }
         }
         playerDeathEvent.setDeathMessage(deathMessage);
         Bukkit.broadcastMessage(deathMessage);
      }
   }
}
}