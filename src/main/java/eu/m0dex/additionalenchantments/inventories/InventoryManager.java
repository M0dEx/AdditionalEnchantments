package eu.m0dex.additionalenchantments.inventories;

import eu.m0dex.additionalenchantments.utils.Common;
import eu.m0dex.additionalenchantments.utils.Messages;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryManager {

    public static final SmartInventory MainMenu = SmartInventory.builder()
            .id("adminMenu")
            .provider(new AdminMenuInventory())
            .size(3, 9)
            .title(Common.applyColours(Messages.ADMIN_MENU_TITLE.getMessage()))
            .build();

    public static ItemStack createItem(ItemStack item, String title) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Common.applyColours(title));
        item.setItemMeta(meta);

        return item;
    }
}
