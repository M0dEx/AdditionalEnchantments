package eu.m0dex.additionalenchantments.commands;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import eu.m0dex.additionalenchantments.inventories.Inventories;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnchanterCommand extends CommandModule {

    public EnchanterCommand(AdditionalEnchantments instance) {
        super(instance, "enchanter", "additionalenchantments.enchanter", 0, 0, false);
    }

    @Override
    public void run(CommandSender sender, CommandContext args) {

        Inventories.Enchanter.open((Player) sender);
    }

    @Override
    public void help(CommandSender sender) {

    }
}
