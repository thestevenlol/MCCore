package me.stevenlol.core.util;

import me.stevenlol.core.Core;

public class ConfigFiles {

    public ConfigFiles(Core plugin) {
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
    }

}
