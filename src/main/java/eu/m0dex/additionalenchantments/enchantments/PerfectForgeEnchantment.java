package eu.m0dex.additionalenchantments.enchantments;

import eu.m0dex.additionalenchantments.enchantments.utils.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;

public class PerfectForgeEnchantment extends Enchantment {

    private double baseReduction;
    private double reductionIncrease;

    public PerfectForgeEnchantment(CustomEnchantment[] requiredEnchantments, CustomEnchantment[] conflictingEnchantments) {

        super("Perfect Forge", EnchantmentTier.LEGENDARY, ItemType.ARMOR, EnchantmentPriority.LOWEST, EnchantmentEventType.ITEM_DAMAGE, requiredEnchantments, conflictingEnchantments);
    }

    @Override
    public void getConf(String rootKey) {

        ConfigurationSection section = instance.getEnchantmentConfig().getConfigurationSection(rootKey);

        boolean configAvailable = section != null;

        if(!configAvailable)
            instance.getLogger().severe("Couldn't load configuration values for the enchant " + name + " from the config! Loading default values...");

        maxLevel = (configAvailable ? section.getInt("max-level", 5) : 5);
        baseReduction = (configAvailable ? section.getDouble("base-reduction", 20) : 20);
        reductionIncrease = (configAvailable ? section.getDouble("reduction-increase", 15) : 15);

    }

    @Override
    public <T extends Event> void handleEvent(T event, int level) {

        PlayerItemDamageEvent e = (PlayerItemDamageEvent) event;

        int itemDamage = (int)(e.getDamage() * (100 - (baseReduction + (level - 1) * reductionIncrease)) / 100);

        if(instance.getSettings().debug)
            instance.getLogger().info("Original item damage: " + e.getDamage() + ", damage after Perfect Forge: " + itemDamage);

        e.setDamage(itemDamage);
    }
}
