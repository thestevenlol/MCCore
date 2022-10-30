package me.stevenlol.core;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import me.stevenlol.core.commands.cheat.*;
import me.stevenlol.core.commands.gamemode.GameModeCommand;
import me.stevenlol.core.commands.util.WeatherCommand;
import me.stevenlol.core.listeners.PlayerJoin;
import me.stevenlol.core.listeners.PlayerQuit;
import me.stevenlol.core.storage.SQL;
import me.stevenlol.core.tests.SQLiteTest;
import me.stevenlol.core.commands.CommandCompleters;
import me.stevenlol.core.util.Cooldowns;
import me.stevenlol.core.tasks.PlayTimeTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public final class Core extends JavaPlugin {

    private final Logger logger = Bukkit.getLogger();
    public static Core plugin;
    private static PaperCommandManager commandManager;

    private static SQL sql;

    @Override
    public void onEnable() {
        plugin = this;

        // Files and Folders
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        sql = new SQL();
        if (sql.start()) getLogger().info("Connected to database!");
        else getLogger().severe("Failed to connect to database!");
        sql.createTables();

        commandManager = new PaperCommandManager(this);
        BaseCommand[] commands = new BaseCommand[] {
                new SQLiteTest(),
                new SignCommands(),
                new ToolCommands(),
                new WeatherCommand(),
                new FeedCommand(),
                new HealCommand(),
                new GameModeCommand(),
                new BinCommand(),
        };

        for (BaseCommand command : commands) {
            commandManager.registerCommand(command);
        }

        // CommandCompleter
        new CommandCompleters();

        // Events
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);

        // Cool-downs
        new Cooldowns();

        // Tasks
        new PlayTimeTask();
    }

    @Override
    public void onDisable() {
        sql.stop();
    }

    public static Core getPlugin() {
        return plugin;
    }

    @NotNull
    @Override
    public Logger getLogger() {
        return logger;
    }

    public static PaperCommandManager getCommandManager() {
        return commandManager;
    }

    public static SQL getSql() {
        return sql;
    }
}
