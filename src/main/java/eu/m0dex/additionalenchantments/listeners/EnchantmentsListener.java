package eu.m0dex.additionalenchantments.listeners;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener implements Listener {

    AdditionalEnchantments instance;
    EnchantmentManager enchantmentManager;

    public PlayerDamageListener(AdditionalEnchantments _instance) {

        instance = _instance;
        enchantmentManager = instance.getEnchantmentManager();
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {

        enchantmentManager.getEnchantment("cowardice").handleEvent(event);
        enchantmentManager.getEnchantment("poison").handleEvent(event);
        enchantmentManager.getEnchantment("frost").handleEvent(event);
    }
}
