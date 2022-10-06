package me.stevenlol.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class Core extends JavaPlugin {

    private final Logger logger = Bukkit.getLogger();
    public static Core plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    @Override
    public void onDisable() {

    }

    public static Core getPlugin() {
        return plugin;
    }

    @NotNull
    @Override
    public Logger getLogger() {
        return logger;
    }
}
