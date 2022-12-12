package me.stevenlol.core;

import co.aikar.commands.PaperCommandManager;
import me.stevenlol.core.commands.NameCommand;
import me.stevenlol.core.commands.SQLDebug;
import me.stevenlol.core.events.ChatEventListener;
import me.stevenlol.core.events.PlayerJoin;
import me.stevenlol.core.sql.Database;
import me.stevenlol.core.util.ConfigFiles;
import me.stevenlol.core.util.SafetyChecks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public final class Core extends JavaPlugin {

    private final Logger logger = Bukkit.getLogger();
    public static Core plugin;
    private static PaperCommandManager commandManager;

    private static ConfigFiles files;
    private static Database database;
    private static final HashMap<UUID, String[]> nameCache = new HashMap<>();



    @Override
    public void onEnable() {
        plugin = this;
        files = new ConfigFiles(this);

        database = new Database();
        database.connect();
        database.createTables();

        new SafetyChecks(this);

        // Files and Folders

        // Commands
        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new NameCommand());
        commandManager.registerCommand(new SQLDebug());

        // TabCompletion
        commandManager.getCommandCompletions().registerAsyncCompletion("colours", c ->
                List.of("&a", "&b", "&c", "&d", "&e", "&f", "&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9"));

        // Events
        Bukkit.getPluginManager().registerEvents(new ChatEventListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);

    }

    @Override
    public void onDisable() {
        database.close();
    }

    public static Core getPlugin() {
        return plugin;
    }

    @NotNull
    @Override
    public Logger getLogger() {
        return logger;
    }

    @SuppressWarnings("unused")
    public static PaperCommandManager getCommandManager() {
        return commandManager;
    }

    @SuppressWarnings("unused")
    public static ConfigFiles getFiles() {
        return files;
    }

    public static Database getDatabase() {
        return database;
    }

    public static HashMap<UUID, String[]> getNameCache() {
        return nameCache;
    }
}
