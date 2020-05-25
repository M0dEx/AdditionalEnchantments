package eu.m0dex.additionalenchantments.listeners;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
<<<<<<< HEAD
import eu.m0dex.additionalenchantments.enchantments.utils.CustomEnchantment;
import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentEventType;
import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentManager;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EnchantmentsListener implements Listener {
=======
import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener implements Listener {
>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c

    AdditionalEnchantments instance;
    EnchantmentManager enchantmentManager;

<<<<<<< HEAD
    public EnchantmentsListener(AdditionalEnchantments _instance) {
=======
    public PlayerDamageListener(AdditionalEnchantments _instance) {
>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c

        instance = _instance;
        enchantmentManager = instance.getEnchantmentManager();
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {

<<<<<<< HEAD
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();

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
        if(damager instanceof Player) {

            Player playerDamager = (Player) damager;

            enchantments.addAll(EnchantmentManager.getEnchantmentsOnItem(playerDamager.getItemInHand(), EnchantmentEventType.ENTITY_ENTITY_DAMAGE));

        } else if(damager instanceof Projectile) {

            Projectile projectile = (Projectile) damager;

            if(projectile.getShooter() instanceof Player) {

                Player playerDamager = (Player) projectile.getShooter();

                enchantments.addAll(EnchantmentManager.getEnchantmentsOnItem(playerDamager.getItemInHand(), EnchantmentEventType.ENTITY_ENTITY_DAMAGE));
            }
        }

        enchantments.sort(Comparator.comparing(ench -> ench.getEnchantment().getEventPriority().getPriority()));

        for(CustomEnchantment enchantment : enchantments)
            enchantment.handleEvent(event);
=======
        enchantmentManager.getEnchantment("cowardice").handleEvent(event);
        enchantmentManager.getEnchantment("poison").handleEvent(event);
        enchantmentManager.getEnchantment("frost").handleEvent(event);
>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c
    }
}
