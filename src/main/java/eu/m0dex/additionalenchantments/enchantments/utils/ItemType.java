package eu.m0dex.additionalenchantments.enchantments.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ItemType {
    BOOTS(new Material[] { Material.LEATHER_BOOTS, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.GOLD_BOOTS, Material.DIAMOND_BOOTS }),
    LEGGINGS(new Material[] { Material.LEATHER_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.GOLD_LEGGINGS, Material.DIAMOND_LEGGINGS }),
    CHESTPLATES(new Material[] { Material.LEATHER_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.DIAMOND_CHESTPLATE }),
    HELMETS(new Material[] { Material.LEATHER_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.GOLD_HELMET, Material.DIAMOND_HELMET }),
    ARMOR(Stream.of(BOOTS.getItems(), LEGGINGS.getItems(), CHESTPLATES.getItems(), HELMETS.getItems()).flatMap(List::stream).collect(Collectors.toList())),
    SWORDS(new Material[] { Material.WOOD_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLD_SWORD, Material.DIAMOND_SWORD }),
    PICKAXES(new Material[] { Material.WOOD_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE }),
    AXES(new Material[] { Material.WOOD_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLD_AXE, Material.DIAMOND_AXE }),
    BOW(new Material[] { Material.BOW }),
    WEAPONS(Stream.of(SWORDS.getItems(), AXES.getItems(), BOW.getItems()).flatMap(List::stream).collect(Collectors.toList()));

    List<Material> items;

    ItemType(Material[] _items) { items = Arrays.asList(_items); }
    ItemType(List<Material> _items) { items = _items; }

    public List<Material> getItems() {
        return items;
    }

    public boolean isApplicable(ItemStack item) {
        return items.contains(item.getType());
    }
}
