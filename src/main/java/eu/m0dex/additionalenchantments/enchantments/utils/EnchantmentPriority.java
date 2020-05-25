package eu.m0dex.additionalenchantments.enchantments.utils;

public enum EnchantmentPriority {

    LOWEST(5),
    LOW(4),
    MEDIUM(3),
    HIGH(2),
    HIGHEST(1);

    int priority;

    EnchantmentPriority(int _priority) { priority = _priority; }

    public int getPriority() {
        return priority;
    }
}
