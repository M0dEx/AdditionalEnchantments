package eu.m0dex.additionalenchantments.listeners;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import eu.m0dex.additionalenchantments.enchantments.utils.CustomEnchantment;
import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentEventType;
import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentManager;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class EnchantmentsListener implements Listener {

    AdditionalEnchantments instance;
    EnchantmentManager enchantmentManager;

    public EnchantmentsListener(AdditionalEnchantments _instance) {

        instance = _instance;
        enchantmentManager = instance.getEnchantmentManager();
    }

    /**
     *  Handles most of damage events
     */
    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {

        Entity damaged = e.getEntity();
        Entity damager = e.getDamager();

        List<CustomEnchantment> enchantments = new ArrayList<>();

        /*
            DEFENCE ENCHANTMENTS
         */
        if(damaged instanceof Player) {
            if(damager instanceof LivingEntity || damager instanceof Projectile) {

                Player playerDamaged = (Player) damaged;
                PlayerInventory inv = playerDamaged.getInventory();

                enchantments.addAll(EnchantmentManager.getEnchantmentsOnItem(playerDamaged.getInventory().getHelmet(), EnchantmentEventType.ENTITY_ENTITY_DAMAGE));
                enchantments.addAll(EnchantmentManager.getEnchantmentsOnItem(playerDamaged.getInventory().getChestplate(), EnchantmentEventType.ENTITY_ENTITY_DAMAGE));
                enchantments.addAll(EnchantmentManager.getEnchantmentsOnItem(playerDamaged.getInventory().getLeggings(), EnchantmentEventType.ENTITY_ENTITY_DAMAGE));
                enchantments.addAll(EnchantmentManager.getEnchantmentsOnItem(playerDamaged.getInventory().getBoots(), EnchantmentEventType.ENTITY_ENTITY_DAMAGE));
            }
        }

        /*
            OFFENCE ENCHANTMENTS
         */
        if(damager instanceof Player && damaged instanceof LivingEntity) {

            /*
                MELEE WEAPONS
             */

            Player playerDamager = (Player) damager;

            enchantments.addAll(EnchantmentManager.getEnchantmentsOnItem(playerDamager.getItemInHand(), EnchantmentEventType.ENTITY_ENTITY_DAMAGE));

        } else if(damager instanceof Projectile && damaged instanceof LivingEntity) {

            /*
                BOWS
             */

            Projectile projectile = (Projectile) damager;

            if(projectile.getShooter() instanceof Player) {

                Player playerDamager = (Player) projectile.getShooter();

                enchantments.addAll(EnchantmentManager.getEnchantmentsOnItem(playerDamager.getItemInHand(), EnchantmentEventType.ENTITY_ENTITY_DAMAGE));
            }
        }

        instance.getLogger().info("Size of List<CustomEnchantment> at onEntityDamageByEntityEvent(): " + enchantments.size());

        enchantments.sort(Comparator.comparing(ench -> ench.getEnchantment().getEventPriority()));

        for(CustomEnchantment enchantment : enchantments)
            enchantment.handleEvent(e);
    }

    /**
     * Handles players shooting bows
     */
    @EventHandler(ignoreCancelled = true)
    public void onEntityShootBowEvent(EntityShootBowEvent e) {

        List<CustomEnchantment> enchantments = new ArrayList<>();

        if(e.getEntity() instanceof Player) {

            Player player = (Player) e.getEntity();

            if(e.getProjectile() instanceof Arrow) {

                Arrow projectile = (Arrow) e.getProjectile();

                enchantments.addAll(EnchantmentManager.getEnchantmentsOnItem(e.getBow(), EnchantmentEventType.ENTITY_SHOOT_BOW));
            }
        }

        instance.getLogger().info("Size of List<CustomEnchantment> at onEntityShootBowEvent(): " + enchantments.size());

        enchantments.sort(Comparator.comparing(ench -> ench.getEnchantment().getEventPriority()));

        for(CustomEnchantment enchantment : enchantments)
            enchantment.handleEvent(e);
    }

    /**
     * Handles item damage
     */
    @EventHandler(ignoreCancelled = true)
    public void onPlayerItemDamageEvent(PlayerItemDamageEvent e) {

        List<CustomEnchantment> enchantments = EnchantmentManager.getEnchantmentsOnItem(e.getItem(), EnchantmentEventType.ITEM_DAMAGE);

        instance.getLogger().info("Size of List<CustomEnchantment> at onPlayerItemDamageEvent(): " + enchantments.size());

        enchantments.sort(Comparator.comparing(ench -> ench.getEnchantment().getEventPriority()));

        for(CustomEnchantment enchantment : enchantments)
            enchantment.handleEvent(e);
    }
}
