package eu.m0dex.additionalenchantments.commands;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import eu.m0dex.additionalenchantments.enchantments.Enchantment;
import eu.m0dex.additionalenchantments.enchantments.utils.CustomEnchantment;
import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentManager;
import eu.m0dex.additionalenchantments.utils.Common;
import eu.m0dex.additionalenchantments.utils.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdditionalEnchantmentsCommand extends CommandModule {

    public AdditionalEnchantmentsCommand(AdditionalEnchantments instance) {
        super(instance, "additionalenchantments", "", 0, 3, true);
    }

    @Override
    public void run(CommandSender sender, CommandContext args) {

        switch(args.getString(0)) {

            case "enchant":
                enchant(sender, args);
                break;
            case "reload":
                reload(sender);
                break;
            default:
                help(sender);
                break;
        }
    }

    private void enchant(CommandSender sender, CommandContext args) {

        if(!(sender instanceof Player))
            return;

        Player player = (Player) sender;

        CustomEnchantment enchantment = new CustomEnchantment(instance.getEnchantmentManager().getEnchantment(args.getString(1)), args.getInt(2));

        if(enchantment.getEnchantment() == null)
            return;

        if (EnchantmentManager.enchantItem(player.getItemInHand(), enchantment))
            Common.tell(sender, Messages.ENCHANTMENT_SUCCESSFUL.getMessage("%enchantment%-" + enchantment.toString()));
        else
            Common.tell(sender, Messages.ENCHANTMENT_UNSUCCESSFUL.getMessage("%enchantment%-" + enchantment.toString()));
    }

    private void reload(CommandSender sender) {

        if(!Common.hasPermission(sender, "additionalenchantment.admin.reload"))
            return;

        instance.reload();
        Common.tell(sender, Messages.PLUGIN_RELOADED);
    }

    @Override
    public void help(CommandSender sender) {

        if(Common.hasPermission(sender, "additionalenchantments.help"))
            Common.tell(sender, Messages.ADDITIONAL_ENCHANTMENTS_HELP);
    }
}
