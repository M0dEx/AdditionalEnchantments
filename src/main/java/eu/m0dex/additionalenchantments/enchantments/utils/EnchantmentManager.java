package eu.m0dex.additionalenchantments.enchantments;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnchantmentManager {

    AdditionalEnchantments instance;

    Map<String, Enchantment> enchantments;

    public EnchantmentManager(AdditionalEnchantments _instance) {

        instance = _instance;

        enchantments = new HashMap<>();

        enchantments.put("cowardice", new CowardiceEnchantment(new Enchantment[] {}, new Enchantment[] {}));
    }

    public List<Enchantment> getEnchantmentsOnItem(ItemStack item) {

        List<Enchantment> foundEnchantments = new ArrayList<>();

        if(item == null || !item.hasItemMeta())
            return foundEnchantments;

        ItemMeta meta = item.getItemMeta();

        if(!meta.hasLore())
            return foundEnchantments;

        List<String> lore = meta.getLore();

        for(String line : lore)
            for(Enchantment enchantment : enchantments.values())
                if(line.contains(enchantment.getName()))
                    foundEnchantments.add(enchantment);

        return foundEnchantments;
    }

    public boolean enchantItem(ItemStack item, Enchantment enchantment, int level) {

        if(item == null || /*!item.hasItemMeta() ||*/ !enchantment.isApplicableToItem(item, level))
            return false;

        ItemMeta meta = item.getItemMeta();
        List<String> lore = (meta.hasLore() ? meta.getLore() : new ArrayList<>());

        lore.add(enchantment.toEnchantmentString(level));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return true;
    }

    public Enchantment getEnchantment(String name) {
        name = name.toLowerCase();

        if(enchantments.containsKey(name))
            return enchantments.get(name);

        return null;
    }
}
