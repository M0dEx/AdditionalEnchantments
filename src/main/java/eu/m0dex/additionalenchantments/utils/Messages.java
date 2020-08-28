package eu.m0dex.additionalenchantments.utils;

import org.bukkit.configuration.file.FileConfiguration;

public enum Messages {

    //region MISC MESSAGES
    PLAYER_ONLY_COMMAND(""),
    ADDITIONAL_ENCHANTMENTS_HELP(""),
    ENCHANTMENT_SUCCESSFUL("&3&lAdditionalEnchantments &e&l» &a&lSuccessfully enchanted the item with %enchantment%&a&l!"),
    ENCHANTMENT_UNSUCCESSFUL("&3&lAdditionalEnchantments &e&l» &c&lCouldn't enchant the item with %enchantment%&c&l!"),
    NO_PERMISSION("&3&lAdditionalEnchantments &e&l» &c&lYou do not have the permission to do this!"),
    //endregion

    //region ADMIN MENU
    ADMIN_MENU_TITLE("&3&lAdmin Menu"),
    ADMIN_MENU_EXIT("&c&lExit"),
    ADMIN_MENU_RELOAD("&2&lReload AE"),
    //endregion

    //region ENCHANTER MENU
    ENCHANTER_TITLE("&3&lEnchanter"),
    ENCHANTER_HINT_LORE(""),
    //endregion

    //region ADMIN COMMANDS
    PLUGIN_RELOADED("&3&lAdditionalEnchantments &e&l» &a&lSuccessfully reloaded the plugin!");
    //endregion

    private final String path;
    private final String value;

    private static FileConfiguration conf;

    Messages(String _value) {
        path = this.name().toLowerCase().replace("_","-");
        value = _value;
    }

    /**
     * Sets the configuration file for messages
     * @param config    <code>FileConfiguration</code> config file
     */
    public static void setFile(FileConfiguration config) {
        conf = config;
    }

    public String getDefault() {
        return this.value;
    }

    public String getPath() {
        return this.path;
    }

    /**
     *
     * @param args  Message variables to replace in this format "%repl1%"-"Replacement";"%repl2%"-"Replacement2"
     * @return      <code>String</code> message specified in the config with variables replaced by specified replacements
     */
    public String getMessage(String args) {

        String output = conf.getString(this.path, this.value);

        for(String arg : args.split(";"))
            output = output.replace(arg.split("-")[0], arg.split("-")[1]);

        return output;
    }

    /**
     * Gets message from config
     * @return      <code>String</code> message specified in the config
     */
    public String getMessage() {
        return conf.getString(this.path, this.value);
    }
}
