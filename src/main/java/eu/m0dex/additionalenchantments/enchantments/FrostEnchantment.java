package eu.m0dex.additionalenchantments.enchantments;

import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentTier;
import eu.m0dex.additionalenchantments.enchantments.utils.ItemType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PoisonEnchantment extends Enchantment {

    private int cooldown;
    private int speedLevel;
    private double baseChance;
    private double chanceIncrease;
    private double baseDuration;
    private double durationIncrease;

    private Random random;

    public PoisonEnchantment(Enchantment[] requiredEnchantments, Enchantment[] conflictingEnchantments) {

        super("Frost", EnchantmentTier.COMMON, ItemType.SWORDS, requiredEnchantments, conflictingEnchantments);

        random = new Random();

        getConf("enchantments.uncommon.frost");
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
    public <T extends Event> void handleEvent(T event) {
        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;

        if(!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof LivingEntity))
            return;

        Player player = (Player) e.getDamager();

        if(player.getItemInHand() == null || !allowedItems.isApplicable(player.getItemInHand()))
            return;

        int level = getEnchantmentLevel(player.getItemInHand());

        if(level == 0)
            return;

        //TODO: check for cooldown

        int roll = random.nextInt(100) + 1;

        if(roll < baseChance + chanceIncrease * (level-1))
            ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int)(20*(baseDuration+(level-1)*durationIncrease)), speedLevel - 1));
    }
}
