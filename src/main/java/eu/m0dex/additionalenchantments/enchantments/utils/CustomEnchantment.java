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

        if(_level <= 0)
            level = 1;
        else if(_level > enchantment.getMaxLevel())
            level = enchantment.getMaxLevel();
        else
            level = _level;
    }

    public <T extends Event> void handleEvent(T event) {
        enchantment.handleEvent(event, level);
    }

    public static CustomEnchantment fromString(String string) {

        string = Common.stripColours(string);

        int sepIndex = string.lastIndexOf(" ");

        if(sepIndex == -1)
            return null;

        String name = string.substring(0, sepIndex).replace(" ", "");
        int _level = Arrays.asList(Enchantment.romanNumerals).indexOf(string.substring(sepIndex + 1)) + 1;

        AdditionalEnchantments.getInstance().getLogger().info("Parsed enchantment name: " + name + ", level: " + _level);

        Enchantment _enchantment = AdditionalEnchantments.getInstance().getEnchantmentManager().getEnchantment(name);

        if (_enchantment == null || _level == 0)
            return null;

        return new CustomEnchantment(_enchantment, _level);
    }

    @Override
    public String toString() {
        return Common.applyColours(enchantment.getTier().getColor() + enchantment.getName() + " " + Enchantment.romanNumerals[level - 1]);
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof CustomEnchantment))
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
}
