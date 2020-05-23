package eu.m0dex.additionalenchantments.enchantments;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class CowardiceEnchantment extends Enchantment {

    private int maxLevel;
    private int cooldown;
    private int speedLevel;
    private double baseChance;
    private double chanceIncrease;
    private double baseDuration;
    private double durationIncrease;

    private Random random;

    public CowardiceEnchantment(Enchantment[] requiredEnchantments, Enchantment[] conflictingEnchantments) {

        super("Cowardice", EnchantmentTier.COMMON, ItemType.BOOTS, requiredEnchantments, conflictingEnchantments);

        random = new Random();

        getConf("enchantments.common.cowardice");
    }

    @Override
    public void getConf(String rootKey) {

        ConfigurationSection section = instance.getConfig().getConfigurationSection(rootKey);

        boolean configAvailable = section != null;

        if(!configAvailable)
            instance.getLogger().severe("Couldn't load configuration values for the enchant " + name + " from the config! Loading default values...");

        maxLevel = (configAvailable ? section.getInt("max-level", 3) : 3);
        cooldown = (configAvailable ? section.getInt("cooldown", 15) : 15);
        speedLevel = (configAvailable ? section.getInt("speed-level", 2) : 2);
        baseChance = (configAvailable ? section.getDouble("base-chance", 10) : 10);
        chanceIncrease = (configAvailable ? section.getDouble("chance-increase", 5) : 5);
        baseDuration = (configAvailable ? section.getDouble("base-duration", 2) : 2);
        durationIncrease = (configAvailable ? section.getDouble("duration-increase", 1) : 1);
    }

    @Override
    public void apply(Player player, ItemStack item) {

        int level = getEnchantmentLevel(item);

        if(level == 0)
            return;

        //TODO: check for cooldown

        int roll = random.nextInt(100) + 1;

        if(roll < baseChance + chanceIncrease * (level-1))
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (int)(20*(baseDuration+(level-1)*durationIncrease)), speedLevel - 1));
    }
}
