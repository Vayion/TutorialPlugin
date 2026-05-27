package de.vayion.tutorial.tools;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Locker implements Listener {

    public Locker() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(!player.isOp()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200000, 1));
            }
        });
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(!event.getPlayer().isOp()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }


    public void disable() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(!player.isOp()) {
                player.removePotionEffect(PotionEffectType.BLINDNESS);
            }
        });
        HandlerList.unregisterAll(this);
    }

}
