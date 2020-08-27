package eu.m0dex.additionalenchantments;

import eu.m0dex.additionalenchantments.commands.AdditionalEnchantmentsCommand;
import eu.m0dex.additionalenchantments.commands.CommandExecutor;
import eu.m0dex.additionalenchantments.commands.CommandModule;
import eu.m0dex.additionalenchantments.enchantments.utils.EnchantmentManager;
import eu.m0dex.additionalenchantments.listeners.EnchantmentsListener;
import eu.m0dex.additionalenchantments.listeners.PlayerListener;
import eu.m0dex.additionalenchantments.playerdata.PlayerCache;
import eu.m0dex.additionalenchantments.utils.Configuration;
import eu.m0dex.additionalenchantments.utils.Messages;
import eu.m0dex.additionalenchantments.utils.Metrics;
import eu.m0dex.additionalenchantments.utils.Settings;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AdditionalEnchantments extends JavaPlugin {

    private PlayerCache playerCache;

    private EnchantmentManager enchantmentManager;

    private Map<String, CommandModule> commands;
    private CommandExecutor cmdExec;

    private Configuration mainCfg;
    private Configuration messagesCfg;
    private Configuration enchantmentCfg;

    private Settings settings;

    private Metrics metrics;

    private static AdditionalEnchantments instance;

    @Override
    public void onEnable() {

        instance = this;

        playerCache = new PlayerCache();

        enchantmentManager = new EnchantmentManager(this);

        registerCommands();
        registerListeners();

        initializeMetrics();
    }

    @Override
    public void onDisable() {

        HandlerList.unregisterAll(this);
    }

    public void reload() {

        if(!loadConfigs()) {
            getLogger().severe("Something went wrong while loading the config files!");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        enchantmentManager = new EnchantmentManager(this);

        registerListeners();
    }

    /**
     * Loads configs
     */
    private boolean loadConfigs() {

        mainCfg =           new Configuration(this, "", "config.yml");
        messagesCfg =       new Configuration(this, "", "messages.yml");
        enchantmentCfg =    new Configuration(this, "", "enchantments.yml");

        boolean cfgLoaded = mainCfg.loadConfig() && enchantmentCfg.loadConfig() && messagesCfg.loadConfig();

        if(!cfgLoaded)
            return false;

        settings = new Settings(this, mainCfg);

        loadMessages();

        return true;
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

        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new EnchantmentsListener(this), this);
    }

    /**
     * Initializes metrics and custom graphs
     */
    private void initializeMetrics() {

        metrics = new Metrics(this, 6741);
    }

    public void addCommand(String cmdName, CommandModule module) {
        if(commands != null) {
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

    public Settings getSettings() { return settings; }

    public FileConfiguration getEnchantmentConfig() { return enchantmentCfg.getConfig(); }

    public EnchantmentManager getEnchantmentManager() { return enchantmentManager; }

    public PlayerCache getPlayerCache() { return playerCache; }

    public CommandModule getCommandModule(String cmdName) { return commands.get(cmdName); }
}
