package eu.m0dex.additionalenchantments.enchantments;

import eu.m0dex.additionalenchantments.enchantments.utils.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ShotgunEnchantment extends Enchantment {

    private int baseArrows;
    private int arrowsIncrease;
    private int baseSpread;
    private int spreadDecrease;
    private double baseChance;
    private double chanceIncrease;

    private Random random;

    public ShotgunEnchantment(CustomEnchantment[] requiredEnchantments, CustomEnchantment[] conflictingEnchantments) {

        super("Shotgun", EnchantmentTier.RARE, ItemType.BOW, EnchantmentPriority.LOWEST, EnchantmentEventType.ENTITY_SHOOT_BOW, requiredEnchantments, conflictingEnchantments);

        random = new Random();
    }

    @Override
    public void getConf(String rootKey) {

        ConfigurationSection section = instance.getEnchantmentConfig().getConfigurationSection(rootKey);

        boolean configAvailable = section != null;

        if(!configAvailable)
            instance.getLogger().severe("Couldn't load configuration values for the enchant " + name + " from the config! Loading default values...");

        maxLevel = (configAvailable ? section.getInt("max-level", 3) : 3);
        cooldown = (configAvailable ? section.getInt("cooldown", 5) : 5);
        baseChance = (configAvailable ? section.getDouble("base-chance", 25) : 25);
        chanceIncrease = (configAvailable ? section.getDouble("chance-increase", 5) : 5);
        baseArrows = (configAvailable ? section.getInt("base-arrows", 3) : 3);
        arrowsIncrease = (configAvailable ? section.getInt("arrows-increase", 1) : 1);
        baseSpread = (configAvailable ? section.getInt("base-spread", 12) : 12);
        spreadDecrease = (configAvailable ? section.getInt("spread-decrease", 3) : 3);
    }

    @Override
    public <T extends Event> void handleEvent(T event, int level) {

        EntityShootBowEvent e = (EntityShootBowEvent) event;

        Player player = (Player) e.getEntity();
        Arrow projectile = (Arrow) e.getProjectile();

        int roll = random.nextInt(100) + 1;

        if(roll <= baseChance + chanceIncrease * (level-1) && !isOnCooldown(player)) {

            new BukkitRunnable() {

                @Override
                public void run() {
                    for (int i = 0; i < baseArrows + (level - 1) * arrowsIncrease; i++) {
                        Arrow arrow = projectile.getWorld().spawnArrow(projectile.getLocation(), projectile.getVelocity(), (float) projectile.getVelocity().length(), (float) (baseSpread - (level - 1) * spreadDecrease));
                        arrow.setShooter(projectile.getShooter());
                        arrow.setFireTicks(projectile.getFireTicks());
                        arrow.setKnockbackStrength(projectile.getKnockbackStrength());
                        arrow.setCritical(projectile.isCritical());
                    }
                }
            }.runTaskLater(instance, 1);

            putOnCooldown(player);
        }
    }
}
