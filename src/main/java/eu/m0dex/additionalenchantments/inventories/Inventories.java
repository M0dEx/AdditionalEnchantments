package eu.m0dex.additionalenchantments.inventories;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import eu.m0dex.additionalenchantments.utils.Common;
import eu.m0dex.additionalenchantments.utils.Messages;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Inventories {

    public static final SmartInventory MainMenu = SmartInventory.builder()
            .id("adminMenu")
            .provider(new AdminMenuInventory())
            .size(3, 9)
            .title(Common.applyColours(Messages.ADMIN_MENU_TITLE.getMessage()))
            .manager(AdditionalEnchantments.getInstance().getInventoryManager())
            .build();

    public static final SmartInventory Enchanter = SmartInventory.builder()
            .id("enchanterMenu")
            .provider(new EnchanterInventory())
            .size(5, 9)
            .title(Common.applyColours(Messages.ENCHANTER_TITLE.getMessage()))
            .manager(AdditionalEnchantments.getInstance().getInventoryManager())
            .build();

    public static ItemStack createItem(ItemStack item, String title) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Common.applyColours(title));
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack createItem(ItemStack item, String title, List<String> lore) {

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Common.applyColours(title));
        meta.setLore(Arrays.asList(Common.applyColours((String[]) lore.toArray())));
        item.setItemMeta(meta);

        return item;
    }
}
