package eu.m0dex.additionalenchantments.enchantments;

import eu.m0dex.additionalenchantments.enchantments.utils.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PoisonEnchantment extends Enchantment {

    private int effectLevel;
    private double baseChance;
    private double chanceIncrease;
    private double baseDuration;
    private double durationIncrease;

    private Random random;

    public PoisonEnchantment(CustomEnchantment[] requiredEnchantments, CustomEnchantment[] conflictingEnchantments) {

        super("Poison", EnchantmentTier.COMMON, ItemType.SWORDS, EnchantmentPriority.LOW, EnchantmentEventType.ENTITY_ENTITY_DAMAGE, requiredEnchantments, conflictingEnchantments);

        random = new Random();
    }

    @Override
    public void getConf(String rootKey) {

        ConfigurationSection section = instance.getEnchantmentConfig().getConfigurationSection(rootKey);

        boolean configAvailable = section != null;

        if(!configAvailable)
            instance.getLogger().severe("Couldn't load configuration values for the enchant " + name + " from the config! Loading default values...");

        maxLevel = (configAvailable ? section.getInt("max-level", 3) : 3);
        cooldown = (configAvailable ? section.getInt("cooldown", 15) : 15);
        effectLevel = (configAvailable ? section.getInt("poison-level", 2) : 2);
        baseChance = (configAvailable ? section.getDouble("base-chance", 10) : 10);
        chanceIncrease = (configAvailable ? section.getDouble("chance-increase", 5) : 5);
        baseDuration = (configAvailable ? section.getDouble("base-duration", 2) : 2);
        durationIncrease = (configAvailable ? section.getDouble("duration-increase", 1) : 1);
    }

    @Override
    public <T extends Event> void handleEvent(T event, int level) {

        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;

        Player player = (Player) e.getDamager();

        int roll = random.nextInt(100) + 1;

        if(roll <= baseChance + chanceIncrease * (level-1) && !isOnCooldown(player)) {
            ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int) (20 * (baseDuration + (level - 1) * durationIncrease)), effectLevel - 1));
            putOnCooldown(player);
        }
    }
}
