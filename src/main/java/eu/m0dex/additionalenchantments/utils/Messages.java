package eu.m0dex.additionalenchantments.utils;

import org.bukkit.configuration.file.FileConfiguration;

public enum Messages {

    // Basic messages
    PLAYER_ONLY_COMMAND(""),
    NO_PERMISSION("&3&lFunQuiz &e&l» &c&lYou do not have the permission to do this!");


    private String path;
    private String value;

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
