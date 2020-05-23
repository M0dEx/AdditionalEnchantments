package eu.m0dex.additionalenchantments.enchantments;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;
import java.util.List;

public abstract class Enchantment {

	String name;
	EnchantmentTier tier;
	ItemType allowedItems;
	List<Enchantment> requiredEnchants;
	List<Enchantment> conflictingEnchants;

	AdditionalEnchantments instance;
	
	public static String[] romanNumerals = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };
	
	public Enchantment(String _name, EnchantmentTier _tier, ItemType _allowedItems, Enchantment[] _requiredEnchantments, Enchantment[] _conflictingEnchantments) {
		
		name = _name;
		tier = _tier;
		allowedItems = _allowedItems;
		requiredEnchants = Arrays.asList(_requiredEnchantments);
		conflictingEnchants = Arrays.asList(_conflictingEnchantments);

		instance = AdditionalEnchantments.getInstance();
	}
	
	public int getEnchantmentLevel(ItemStack item) {
		
		if(item == null || !item.hasItemMeta())
			return 0;
		
		ItemMeta meta = item.getItemMeta();
		
		if(!meta.hasLore())
			return 0;
		
		List<String> lore = meta.getLore();
		
		for(String line : lore) {
			if(line.contains(name)) {
				String[] lineSplit = line.split(" ");
				return Arrays.asList(romanNumerals).indexOf(lineSplit[lineSplit.length - 1]) + 1;
			}
		}
		
		return 0;
	}

	public boolean isApplicableToItem(ItemStack item, int level) {

		if(!allowedItems.isApplicable(item))
			return false;

		List<Enchantment> enchantmentsOnItem = instance.getEnchantmentManager().getEnchantmentsOnItem(item);

		if(getEnchantmentLevel(item) >= level)
			return false;

		for(Enchantment requiredEnchantment : requiredEnchants)
			if(!enchantmentsOnItem.contains(requiredEnchantment))
				return false;

		for(Enchantment conflictingEnchant : conflictingEnchants)
			if(enchantmentsOnItem.contains(conflictingEnchant))
				return false;

		return true;
	}

	public String getName() { return name; }
	public EnchantmentTier getTier() { return tier; }

	public abstract void getConf(String rootKey);

	public abstract void apply(Player player, ItemStack item);
}
