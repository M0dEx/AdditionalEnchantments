package eu.m0dex.additionalenchantments;

import eu.m0dex.additionalenchantments.commands.AdditionalEnchantmentsCommand;
import eu.m0dex.additionalenchantments.commands.CommandExecutor;
import eu.m0dex.additionalenchantments.commands.CommandModule;
import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentManager;
import eu.m0dex.additionalenchantments.listeners.EnchantmentsListener;
import eu.m0dex.additionalenchantments.utils.Configuration;
import eu.m0dex.additionalenchantments.utils.Messages;
import eu.m0dex.additionalenchantments.utils.Metrics;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class AdditionalEnchantments extends JavaPlugin {

    private EnchantmentManager enchantmentManager;

    private Map<String, CommandModule> commands;
    private CommandExecutor cmdExec;

    private Configuration messagesCfg;

    private Metrics metrics;

    private static AdditionalEnchantments instance;

    @Override
    public void onEnable() {

        instance = this;

        loadConfigs();

        enchantmentManager = new EnchantmentManager(this);

        registerCommands();
        registerListeners();

        initializeMetrics();
    }

    @Override
    public void onDisable() {

    }

    /**
     * Loads configs
     */
    private void loadConfigs() {

        messagesCfg =   new Configuration(this, "", "messages.yml");
        messagesCfg.reloadConfig();

        instance.saveDefaultConfig();
        instance.getConfig().options().copyDefaults(true);
        instance.saveConfig();

        loadMessages();
    }

    /**
     * Loads the configuration with messages
     */
    private void loadMessages() {

        Messages.setFile(this.messagesCfg.getConfig());
        Messages[] arrayOfMessages;
        int j = (arrayOfMessages = Messages.values()).length;
        for (int i = 0; i < j; i++) {
            Messages value = arrayOfMessages[i];

            this.messagesCfg.getConfig().addDefault(value.getPath(), value.getDefault());
        }
        this.messagesCfg.getConfig().options().copyDefaults(true);
        this.messagesCfg.saveConfig();
    }

    /**
     * Registers commands
     */
    private void registerCommands() {

        commands = new HashMap<>();
        cmdExec = new CommandExecutor(this);

        new AdditionalEnchantmentsCommand(this);
    }

    /**
     * Registers event listeners
     */
    private void registerListeners() {

        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new EnchantmentsListener(this), this);
    }

    /**
     * Initializes metrics and custom graphs
     */
    private void initializeMetrics() {

        metrics = new Metrics(this, 6741);
    }

    public void addCommand(String cmdName, CommandModule module) {
        if(commands != null && !commands.containsKey(cmdName)) {
            commands.put(cmdName, module);
            this.getCommand(cmdName).setExecutor(cmdExec);
        }
    }

    /**
     * Gets the current instance of the plugin
     * @return AdditionalEnchantments class containing the current plugin instance
     */
    public static AdditionalEnchantments getInstance() {
        return instance;
    }

    public EnchantmentManager getEnchantmentManager() { return enchantmentManager; }

    public CommandModule getCommandModule(String cmdName) { return commands.get(cmdName); }
}
