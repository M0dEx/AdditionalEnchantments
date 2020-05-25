<<<<<<< HEAD
package eu.m0dex.additionalenchantments.enchantments;

import eu.m0dex.additionalenchantments.enchantments.utils.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
=======
package eu.m0dex.additionalenchantments.enchantments.utils;

import eu.m0dex.additionalenchantments.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c

import java.util.Random;

public class PoisonEnchantment extends Enchantment {

<<<<<<< HEAD
    private int cooldown;
    private int effectLevel;
    private double baseChance;
    private double chanceIncrease;
    private double baseDuration;
    private double durationIncrease;

    private Random random;

    public PoisonEnchantment(CustomEnchantment[] requiredEnchantments, CustomEnchantment[] conflictingEnchantments) {

        super("Poison", EnchantmentTier.COMMON, ItemType.SWORDS, EnchantmentPriority.LOWEST, EnchantmentEventType.ENTITY_ENTITY_DAMAGE, requiredEnchantments, conflictingEnchantments);
=======
    private Random random;

    public PoisonEnchantment(Enchantment[] requiredEnchantments, Enchantment[] conflictingEnchantments) {

        super("Poison", EnchantmentTier.UNCOMMON, ItemType.SWORDS, requiredEnchantments, conflictingEnchantments);
>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c

        random = new Random();

        getConf("enchantments.uncommon.poison");
    }

    @Override
    public void getConf(String rootKey) {

<<<<<<< HEAD
        ConfigurationSection section = instance.getConfig().getConfigurationSection(rootKey);

        boolean configAvailable = section != null;

        if(!configAvailable)
            instance.getLogger().severe("Couldn't load configuration values for the enchant " + name + " from the config! Loading default values...");

        maxLevel = (configAvailable ? section.getInt("max-level", 3) : 3);
        cooldown = (configAvailable ? section.getInt("cooldown", 15) : 15);
        effectLevel = (configAvailable ? section.getInt("effect-level", 2) : 2);
        baseChance = (configAvailable ? section.getDouble("base-chance", 10) : 10);
        chanceIncrease = (configAvailable ? section.getDouble("chance-increase", 5) : 5);
        baseDuration = (configAvailable ? section.getDouble("base-duration", 2) : 2);
        durationIncrease = (configAvailable ? section.getDouble("duration-increase", 1) : 1);
    }

    @Override
    public <T extends Event> void handleEvent(T event, int level) {

        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;

        //TODO: Check for cooldown

        int roll = random.nextInt(100) + 1;

        if(roll < baseChance + chanceIncrease * (level-1))
            ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int)(20*(baseDuration+(level-1)*durationIncrease)), effectLevel - 1));
=======
    }

    @Override
    public void apply(Player player, ItemStack item) {

    }

    @Override
    public <T extends Event> void handleEvent(T event) {

>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c
    }
}
