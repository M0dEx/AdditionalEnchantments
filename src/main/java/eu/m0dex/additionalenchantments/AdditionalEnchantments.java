package eu.m0dex.additionalenchantments;

import eu.m0dex.additionalenchantments.commands.CommandModule;
import eu.m0dex.additionalenchantments.enchantments.EnchantmentManager;
import eu.m0dex.additionalenchantments.utils.Configuration;
import eu.m0dex.additionalenchantments.utils.Messages;
import eu.m0dex.additionalenchantments.utils.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public class AdditionalEnchantments extends JavaPlugin {

    private EnchantmentManager enchantmentManager;

    private Map<String, CommandModule> commands;

    private Configuration messagesCfg;

    private Metrics metrics;

    private static AdditionalEnchantments instance;

    @Override
    public void onEnable() {

        instance = this;

        loadConfigs();
        registerCommands();
        registerListeners();

        enchantmentManager = new EnchantmentManager(this);

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

        //TODO: Commands
    }

    /**
     * Registers event listeners
     */
    private void registerListeners() {

        PluginManager pm = this.getServer().getPluginManager();

        //TODO: Event listeners
    }

    /**
     * Initializes metrics and custom graphs
     */
    private void initializeMetrics() {

        metrics = new Metrics(this, 6741);
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
