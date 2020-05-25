package eu.m0dex.additionalenchantments.enchantments;

import java.util.Arrays;

public enum EnchantmentTier {
	COMMON("&a"),
	UNCOMMON("&b"),
	RARE("&d"),
	LEGENDARY("&6"),
	GODLY("&c");
	
	String value;
	int cost;
	
	EnchantmentTier(String _value) {
		value = _value;
	}
	
	public String getColor() { return value; }
	
	public static EnchantmentTier fromColourChar(char colourChar) {
		
		for(EnchantmentTier tier : EnchantmentTier.values()) {
			if(tier.getColor().equalsIgnoreCase("&" + colourChar))
				return tier;
		}
		
		return null;
	}
	
	public int isBetterThan(EnchantmentTier other) {
		
		int thisIndex = Arrays.asList(EnchantmentTier.values()).indexOf(this);
		int otherIndex = Arrays.asList(EnchantmentTier.values()).indexOf(other);
		
		return Integer.compare(otherIndex, thisIndex);
	}
}
