package eu.m0dex.additionalenchantments.commands;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import org.bukkit.command.CommandSender;

public abstract class CommandModule {

    public String cmdName;
    public String perm;
    public int minArgs;
    public int maxArgs;
    public boolean allowConsole;

    AdditionalEnchantments instance;

    public CommandModule(AdditionalEnchantments _instance, String _cmdName, String _perm, int _minArgs, int _maxArgs, boolean _allowConsole) {
        this.instance = _instance;

        this.cmdName = _cmdName;
        this.perm = _perm;
        this.minArgs = _minArgs;
        this.allowConsole = _allowConsole;

        if(_maxArgs == -1)
            this.maxArgs = 99;
        else
            this.maxArgs = _maxArgs;

        instance.addCommand(cmdName, this);
    }

    public abstract void run(CommandSender sender, CommandContext args);
    public abstract void help(CommandSender sender);
}
