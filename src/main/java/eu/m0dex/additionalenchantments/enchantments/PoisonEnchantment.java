package eu.m0dex.additionalenchantments.enchantments.utils;

import eu.m0dex.additionalenchantments.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class PoisonEnchantment extends Enchantment {

    private Random random;

    public PoisonEnchantment(Enchantment[] requiredEnchantments, Enchantment[] conflictingEnchantments) {

        super("Poison", EnchantmentTier.UNCOMMON, ItemType.SWORDS, requiredEnchantments, conflictingEnchantments);

        random = new Random();

        getConf("enchantments.uncommon.poison");
    }

    @Override
    public void getConf(String rootKey) {

    }

    @Override
    public void apply(Player player, ItemStack item) {

    }

    @Override
    public <T extends Event> void handleEvent(T event) {

    }
}
