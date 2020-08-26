package eu.m0dex.additionalenchantments.playerdata;

import eu.m0dex.additionalenchantments.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {

    Map<String, Long> cooldowns;

    public PlayerData() {

        cooldowns = new HashMap<>();
    }

    public boolean isOnCooldown(Enchantment enchantment) {

        if(cooldowns.containsKey(enchantment.getName())) {
            if(cooldowns.get(enchantment.getName()) >= System.currentTimeMillis())
                return true;
            else
                cooldowns.remove(enchantment.getName());
        }

        return false;
    }

    public void putOnCooldown(Enchantment enchantment) {

        cooldowns.put(enchantment.getName(), System.currentTimeMillis() + enchantment.getCooldown());
    }
}
