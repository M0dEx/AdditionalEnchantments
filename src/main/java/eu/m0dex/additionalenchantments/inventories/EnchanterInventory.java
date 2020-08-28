package eu.m0dex.additionalenchantments.inventories;

import eu.m0dex.additionalenchantments.utils.Common;
import eu.m0dex.additionalenchantments.utils.Messages;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class EnchanterInventory implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {

        //Borders
        contents.fillBorders(ClickableItem.empty(Inventories.createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15), " ")));
        contents.fillColumn(4, ClickableItem.empty(Inventories.createItem(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15), " ")));

        contents.set(0, 4, ClickableItem.empty(Inventories.createItem(
                new ItemStack(Material.ENCHANTMENT_TABLE, 1),
                Messages.ENCHANTER_TITLE.getMessage(),
                Arrays.asList(Messages.ENCHANTER_HINT_LORE.getMessage().split("\n")))));
        //Exit button
        /*
        contents.set(2, 8, ClickableItem.of(
                InventoryManager.createItem(new ItemStack(Material.BARRIER, 1), Messages.ADMIN_MENU_EXIT.getMessage()),
                e -> player.closeInventory()));
         */
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
