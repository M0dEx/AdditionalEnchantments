package eu.m0dex.additionalenchantments.enchantments;

import eu.m0dex.additionalenchantments.enchantments.utils.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BerserkEnchantment extends Enchantment {

    private double baseScaling;
    private double scalingIncrease;

    public BerserkEnchantment(CustomEnchantment[] requiredEnchantments, CustomEnchantment[] conflictingEnchantments) {

        super("Berserk", EnchantmentTier.RARE, ItemType.SWORDS, EnchantmentPriority.MEDIUM, EnchantmentEventType.ENTITY_ENTITY_DAMAGE, requiredEnchantments, conflictingEnchantments);
    }

    @Override
    public void getConf(String rootKey) {

        ConfigurationSection section = instance.getEnchantmentConfig().getConfigurationSection(rootKey);

        boolean configAvailable = section != null;

        if(!configAvailable)
            instance.getLogger().severe("Couldn't load configuration values for the enchant " + name + " from the config! Loading default values...");

        maxLevel = (configAvailable ? section.getInt("max-level", 5) : 5);
        baseScaling = (configAvailable ? section.getDouble("base-scaling", 1) : 1);
        scalingIncrease = (configAvailable ? section.getDouble("scaling-increase", 0.0625) : 0.0625);

    }

    @Override
    public <T extends Event> void handleEvent(T event, int level) {

        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;

        Player player = (Player) e.getDamager();

        double lostHealth = 1 - (player.getHealth() / player.getMaxHealth());
        double damage = e.getDamage();

        damage = (baseScaling + scalingIncrease * (level - 1)) * (1 + lostHealth) * damage;

        e.setDamage(damage);
    }
}
