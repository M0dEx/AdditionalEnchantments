package eu.m0dex.additionalenchantments.utils;

import eu.m0dex.additionalenchantments.AdditionalEnchantments;
import org.bukkit.configuration.file.FileConfiguration;

public class Settings {

    /*
        MISC
     */
    public boolean debug;

    public Settings(AdditionalEnchantments instance, Configuration configuration) {

        FileConfiguration config = configuration.getConfig();

        debug = config.getBoolean("misc.debug", false);
    }
}
