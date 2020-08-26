package eu.m0dex.additionalenchantments.enchantments;

import eu.m0dex.additionalenchantments.enchantments.utils.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LifestealEnchantment extends Enchantment {

    private double basePercentage;
    private double percentageIncrease;

    public LifestealEnchantment(CustomEnchantment[] requiredEnchantments, CustomEnchantment[] conflictingEnchantments) {

        super("Lifesteal", EnchantmentTier.RARE, ItemType.SWORDS, EnchantmentPriority.LOW, EnchantmentEventType.ENTITY_ENTITY_DAMAGE, requiredEnchantments, conflictingEnchantments);
    }

    @Override
    public void getConf(String rootKey) {

        ConfigurationSection section = instance.getEnchantmentConfig().getConfigurationSection(rootKey);

        boolean configAvailable = section != null;

        if(!configAvailable)
            instance.getLogger().severe("Couldn't load configuration values for the enchant " + name + " from the config! Loading default values...");

        maxLevel = (configAvailable ? section.getInt("max-level", 3) : 3);
        basePercentage = (configAvailable ? section.getDouble("base-percentage", 20) : 20);
        percentageIncrease = (configAvailable ? section.getDouble("percentage-increase", 10) : 10);
    }

    @Override
    public <T extends Event> void handleEvent(T event, int level) {

        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;

        Player player = (Player) e.getDamager();

        double heal = e.getFinalDamage() * (basePercentage + (level - 1) * percentageIncrease) / 100;

        player.setHealth(Math.min(player.getHealth() + heal, player.getMaxHealth()));
    }
}
