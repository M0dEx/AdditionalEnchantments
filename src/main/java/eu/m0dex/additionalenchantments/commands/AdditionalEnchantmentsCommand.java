package eu.m0dex.additionalenchantments.commands;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import eu.m0dex.additionalenchantments.enchantments.Enchantment;
import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentManager;
import eu.m0dex.additionalenchantments.utils.Common;
import eu.m0dex.additionalenchantments.utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdditionalEnchantmentsCommand extends CommandModule {

    public AdditionalEnchantmentsCommand(AdditionalEnchantments instance) {
        super(instance, "additionalenchantments", "", 0, 3, false);
    }

    @Override
    public void run(CommandSender sender, CommandContext args) {

        switch(args.getString(0).toLowerCase()) {

            case "enchant":
                enchant(sender, args);
                break;
            default:
                help(sender);
                break;
        }
    }

    private void enchant(CommandSender sender, CommandContext args) {

        Enchantment enchantment = instance.getEnchantmentManager().getEnchantment(args.getString(1));

        if(enchantment == null || !(sender instanceof Player))
            return;

        Player player = (Player) sender;

        EnchantmentManager.enchantItem(player.getItemInHand(), enchantment, args.getInt(2));
    }

    @Override
    public void help(CommandSender sender) {
        Common.tell(sender, Messages.NO_PERMISSION);
    }
}
