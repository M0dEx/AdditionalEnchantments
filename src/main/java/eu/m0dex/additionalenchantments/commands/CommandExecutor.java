package eu.m0dex.additionalenchantments.commands;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import eu.m0dex.additionalenchantments.utils.Common;
import eu.m0dex.additionalenchantments.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {

    AdditionalEnchantments instance;

    public CommandExecutor(AdditionalEnchantments _instance) {
        super();
        this.instance = _instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CommandModule module = instance.getCommandModule(cmd.getName());
        if(module != null) {

            if(module.perm.equals("") || Common.hasPermission(sender, module.perm)) {

                if(module.allowConsole) {

                    if(module.minArgs <= args.length && args.length <= module.maxArgs)
                        module.run(sender, new CommandContext(args));
                    else
                        module.help(sender);

                } else {

                    if(sender instanceof Player) {

                        if (module.minArgs <= args.length && args.length <= module.maxArgs)
                            module.run(sender, new CommandContext(args));
                        else
                            module.help(sender);

                    } else
                        Common.tell(sender, Messages.PLAYER_ONLY_COMMAND);
                }

            }
        }

        return true;
    }
}
