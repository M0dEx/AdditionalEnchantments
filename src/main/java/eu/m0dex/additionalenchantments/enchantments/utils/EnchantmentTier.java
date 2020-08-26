package eu.m0dex.additionalenchantments.enchantments.utils;

import java.util.Arrays;

public enum EnchantmentTier {

	GODLY("&c"),
	LEGENDARY("&6"),
	RARE("&d"),
	UNCOMMON("&b"),
	COMMON("&a");
	
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
}
