package eu.m0dex.additionalenchantments.enchantments.utils;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import eu.m0dex.additionalenchantments.enchantments.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class EnchantmentManager {

    AdditionalEnchantments instance;

    Map<String, Enchantment> enchantments;

    public EnchantmentManager(AdditionalEnchantments _instance) {

        instance = _instance;

        enchantments = new HashMap<>();

        enchantments.put("cowardice", new CowardiceEnchantment(new CustomEnchantment[] {}, new CustomEnchantment[] {}));
        enchantments.put("poison", new PoisonEnchantment(new CustomEnchantment[] {}, new CustomEnchantment[] {}));
        enchantments.put("frost", new FrostEnchantment(new CustomEnchantment[] {}, new CustomEnchantment[] {}));
        enchantments.put("marksman", new MarksmanEnchantment(new CustomEnchantment[] {}, new CustomEnchantment[] {}));
        enchantments.put("shotgun", new ShotgunEnchantment(new CustomEnchantment[] {}, new CustomEnchantment[] {}));
        enchantments.put("berserk", new BerserkEnchantment(new CustomEnchantment[] {}, new CustomEnchantment[] {}));
        enchantments.put("lifesteal", new LifestealEnchantment(new CustomEnchantment[] {}, new CustomEnchantment[] {}));
        enchantments.put("perfectforge", new PerfectForgeEnchantment(new CustomEnchantment[] {}, new CustomEnchantment[] {}));
    }

    /**
     * Gets all custom enchantments on the specified item
     *
     * @param item Item to be checked for enchantments
     * @return A list of custom enchantments
     */
    public static List<CustomEnchantment> getEnchantmentsOnItem(ItemStack item) {

        List<CustomEnchantment> foundEnchantments = new ArrayList<>();

        if(item == null)
            return foundEnchantments;

        ItemMeta meta = item.getItemMeta();

        if(!meta.hasLore())
            return foundEnchantments;

        List<String> lore = meta.getLore();

        for(String line : lore) {
            CustomEnchantment enchantment = CustomEnchantment.fromString(line);
            if(enchantment != null)
                foundEnchantments.add(enchantment);
        }

        return foundEnchantments;
    }

    /**
     * Gets all custom enchantments on the specified item which are handled on the specified event type
     *
     * @param item Item to be checked for enchantments
     * @param eventType Event type of the enchantment
     * @return A list of custom enchantments
     */
    public static List<CustomEnchantment> getEnchantmentsOnItem(ItemStack item, EnchantmentEventType eventType) {

        List<CustomEnchantment> foundEnchantments = new ArrayList<>();

        if(item == null || item.getType() == Material.AIR)
            return foundEnchantments;

        ItemMeta meta = item.getItemMeta();

        if(!meta.hasLore())
            return foundEnchantments;

        List<String> lore = meta.getLore();

        for(String line : lore) {
            CustomEnchantment enchantment = CustomEnchantment.fromString(line);
            if(enchantment != null && enchantment.getEnchantment().getEventType() == eventType)
                foundEnchantments.add(enchantment);
        }

        return foundEnchantments;
    }

    /**
     * Puts the enchantments on the ItemStack
     *
     * @param item Item to put the enchantments on
     * @param enchantments List of enchantments
     */
    private static void putEnchantmentsOnItem(ItemStack item, List<CustomEnchantment> enchantments) {

        enchantments.sort(Comparator.comparing(CustomEnchantment::getEnchantment));

        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        for(CustomEnchantment enchantment : enchantments)
            lore.add(enchantment.toString());

        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    /**
     * Checks if it is possible to enchant the item with the specified enchantment of the specified level and if so, enchants it
     *
     * @param item Item to enchant
     * @param enchantment Enchantment to put on the item
     * @return True if enchanting the item was successful, false if it was not
     */
    public static boolean enchantItem(ItemStack item, CustomEnchantment enchantment) {

        List<CustomEnchantment> enchantmentsOnItem = getEnchantmentsOnItem(item);

        if(!enchantment.getEnchantment().isApplicableToItem(enchantmentsOnItem, item, enchantment.getLevel()))
            return false;

        int index = enchantmentsOnItem.indexOf(enchantment);

        if(index != -1)
            enchantmentsOnItem.set(index, enchantment);
        else
            enchantmentsOnItem.add(enchantment);

        putEnchantmentsOnItem(item, enchantmentsOnItem);

        return true;
    }

    public Enchantment getEnchantment(String name) {
        return enchantments.getOrDefault(name.toLowerCase(), null);
    }
}
