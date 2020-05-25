package eu.m0dex.additionalenchantments.enchantments.utils;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import eu.m0dex.additionalenchantments.enchantments.Enchantment;
import eu.m0dex.additionalenchantments.utils.Common;
import org.bukkit.event.Event;

import java.util.Arrays;

public class CustomEnchantment {

    private Enchantment enchantment;
    private int level;

    public CustomEnchantment(Enchantment _enchantment, int _level) {
        enchantment = _enchantment;
        level = _level;
    }

    public <T extends Event> void handleEvent(T event) {
        enchantment.handleEvent(event, level);
    }

    public static CustomEnchantment fromString(String string) {
        String[] split = string.split(Common.stripColours(string));

        if(split.length != 2)
            return null;

        Enchantment _enchantment = AdditionalEnchantments.getInstance().getEnchantmentManager().getEnchantment(split[0].toLowerCase());
        int _level = Arrays.asList(Enchantment.romanNumerals).indexOf(split[1]);

        if(_enchantment == null || _level == -1 || _level > _enchantment.getMaxLevel())
            return null;

        return new CustomEnchantment(_enchantment, _level);
    }

    @Override
    public String toString() {
        return Common.applyColours(enchantment.getTier().getColor() + enchantment.getName() + " " + Enchantment.romanNumerals[level - 1]);
    }

    @Override
    public boolean equals(Object object) {

        if(!(object instanceof CustomEnchantment))
            return false;

        CustomEnchantment other = (CustomEnchantment) object;

        return this.getEnchantment() == other.getEnchantment();
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int _level) {
        if(_level >= 0 || _level < enchantment.getMaxLevel())
            level = _level;
    }
}
