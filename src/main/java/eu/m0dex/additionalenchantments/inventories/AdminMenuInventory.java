package eu.m0dex.additionalenchantments.inventories;

import eu.m0dex.additionalenchantments.utils.Messages;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AdminMenuInventory implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {

        //Borders
        contents.fillBorders(ClickableItem.empty(InventoryManager.createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15), " ")));

        //Exit button
        contents.set(2, 8, ClickableItem.of(
                InventoryManager.createItem(new ItemStack(Material.BARRIER, 1), Messages.ADMIN_MENU_EXIT.getMessage()),
                e -> player.closeInventory()));

        contents.set(1, 7, ClickableItem.of(
                InventoryManager.createItem(new ItemStack(Material.BLAZE_ROD, 1), Messages.ADMIN_MENU_RELOAD.getMessage()),
                e -> {
                    player.performCommand("additionalenchantments reload");
                    player.closeInventory();
                }
        ));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}