package eu.m0dex.additionalenchantments.enchantments;

<<<<<<< HEAD
import eu.m0dex.additionalenchantments.enchantments.utils.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
=======
import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentTier;
import eu.m0dex.additionalenchantments.enchantments.utils.ItemType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

<<<<<<< HEAD
public class FrostEnchantment extends Enchantment {

    private int cooldown;
    private int effectLevel;
=======
public class PoisonEnchantment extends Enchantment {

    private int cooldown;
    private int speedLevel;
>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c
    private double baseChance;
    private double chanceIncrease;
    private double baseDuration;
    private double durationIncrease;

    private Random random;

<<<<<<< HEAD
    public FrostEnchantment(CustomEnchantment[] requiredEnchantments, CustomEnchantment[] conflictingEnchantments) {

        super("Frost", EnchantmentTier.COMMON, ItemType.SWORDS, EnchantmentPriority.LOWEST, EnchantmentEventType.ENTITY_ENTITY_DAMAGE, requiredEnchantments, conflictingEnchantments);
=======
    public PoisonEnchantment(Enchantment[] requiredEnchantments, Enchantment[] conflictingEnchantments) {

        super("Frost", EnchantmentTier.COMMON, ItemType.SWORDS, requiredEnchantments, conflictingEnchantments);
>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c

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
<<<<<<< HEAD
        effectLevel = (configAvailable ? section.getInt("effect-level", 2) : 2);
=======
        speedLevel = (configAvailable ? section.getInt("speed-level", 2) : 2);
>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c
        baseChance = (configAvailable ? section.getDouble("base-chance", 10) : 10);
        chanceIncrease = (configAvailable ? section.getDouble("chance-increase", 5) : 5);
        baseDuration = (configAvailable ? section.getDouble("base-duration", 2) : 2);
        durationIncrease = (configAvailable ? section.getDouble("duration-increase", 1) : 1);
    }

    @Override
<<<<<<< HEAD
    public <T extends Event> void handleEvent(T event, int level) {

        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;

        //TODO: Check for cooldown
=======
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
>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c

        int roll = random.nextInt(100) + 1;

        if(roll < baseChance + chanceIncrease * (level-1))
<<<<<<< HEAD
            ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int)(20*(baseDuration+(level-1)*durationIncrease)), effectLevel - 1));
=======
            ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int)(20*(baseDuration+(level-1)*durationIncrease)), speedLevel - 1));
>>>>>>> 15a89794d4f796fdfe19580f67d53512ed01611c
    }
}
