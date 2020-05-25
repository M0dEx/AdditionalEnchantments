package eu.m0dex.additionalenchantments.enchantments;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import eu.m0dex.additionalenchantments.enchantments.utils.*;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public abstract class Enchantment {

	String name;
	EnchantmentTier tier;
	ItemType allowedItems;
	EnchantmentPriority priority;
	EnchantmentEventType eventType;
	int maxLevel;
	List<CustomEnchantment> requiredEnchants;
	List<CustomEnchantment> conflictingEnchants;

	AdditionalEnchantments instance;
	
	public static String[] romanNumerals = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };
	
	public Enchantment(String _name, EnchantmentTier _tier, ItemType _allowedItems, EnchantmentPriority _priority, EnchantmentEventType _eventType, CustomEnchantment[] _requiredEnchantments, CustomEnchantment[] _conflictingEnchantments) {
		
		name = _name;
		tier = _tier;
		allowedItems = _allowedItems;
		priority = _priority;
		eventType = _eventType;
		requiredEnchants = Arrays.asList(_requiredEnchantments);
		conflictingEnchants = Arrays.asList(_conflictingEnchantments);

		instance = AdditionalEnchantments.getInstance();
	}

	public boolean isApplicableToItem(List<CustomEnchantment> enchantmentsOnItem, ItemStack item, int level) {

		if(item == null || item.getItemMeta() == null || !allowedItems.isApplicable(item) || level > maxLevel)
			return false;

		for(CustomEnchantment enchantment : enchantmentsOnItem)
			if(enchantment.getEnchantment() == this && level < enchantment.getLevel())
				return false;

		for(CustomEnchantment requiredEnchantment : requiredEnchants)
			if(!enchantmentsOnItem.contains(requiredEnchantment))
				return false;

		for(CustomEnchantment conflictingEnchantment : conflictingEnchants)
			if(enchantmentsOnItem.contains(conflictingEnchantment))
				return false;

		return true;
	}

	public String getName() {
		return name;
	}
	public EnchantmentTier getTier() {
		return tier;
	}
	public int getMaxLevel() {
		return maxLevel;
	}
	public EnchantmentPriority getEventPriority() {
		return priority;
	}
	public EnchantmentEventType getEventType() {
		return eventType;
	}

	public abstract void getConf(String rootKey);

	public abstract <T extends Event> void handleEvent(T event, int level);

	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Enchantment))
			return false;

		Enchantment other = (Enchantment) object;

		return this.getName().equals(other.getName());
	}
}
