package me.stevenlol.core.sql;

import lombok.SneakyThrows;
import me.stevenlol.core.Core;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;

public class Database {

    /**
     * Uses SQLite to store data
     */
    private final Core plugin = Core.getPlugin();
    private final String path = plugin.getDataFolder().getAbsolutePath() + "/database.db";
    private Connection connection;


    public Database() {
        createFile();
    }

    @SneakyThrows
    private void createFile() {
        plugin.getLogger().info("[Core] Searching for database file...");
        final File file = new File(path);
        if (!file.exists()) {
            plugin.getLogger().info("[Core] Database file not found, creating...");
            if (file.createNewFile()) {
                plugin.getLogger().info("[Core] Created database file.");
            }
        } else {
            plugin.getLogger().info("[Core] Found database file.");
        }
    }

    @SneakyThrows
    public void connect() {
        plugin.getLogger().info("[Core] Attempting to connect to the database...");
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        if (connection != null) {
            plugin.getLogger().info("[Core] Connected to the database.");
            return;
        }
        plugin.getLogger().info("[Core] Failed to connect to the database.");
    }

    @SneakyThrows
    public void close() {
        connection.close();
    }

    @SneakyThrows
    public PreparedStatement createStatement(String sql) {
        return connection.prepareStatement(sql);
    }


    public void executeUpdate(String sql) {
        synchronized (this) {
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                try (final PreparedStatement statement = createStatement(sql)) {
                    statement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void executeUpdate(PreparedStatement statement) {
        synchronized (this) {
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                try {
                    statement.executeUpdate();
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @SneakyThrows
    @SuppressWarnings("unused")
    public CompletableFuture<ResultSet> executeQuery(String sql) {
        synchronized (this) {
            return CompletableFuture.supplyAsync(() -> {
                try (final PreparedStatement statement = createStatement(sql)) {
                    return statement.executeQuery();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            });
        }
    }

    @SneakyThrows
    @SuppressWarnings("unused")
    public CompletableFuture<ResultSet> executeQuery(PreparedStatement statement) {
        synchronized (this) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    ResultSet set = statement.executeQuery();
                    statement.close();
                    return set;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            });
        }
    }

    @SneakyThrows
    public void createTables() {
        // PlayerNames Table: id - PK, uuid - not null, name - not null, colour, not - null default "&f"
        final String playerNames = "CREATE TABLE IF NOT EXISTS PlayerNames (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "uuid VARCHAR(36) NOT NULL, name VARCHAR(16) NOT NULL, colour VARCHAR(2) NOT NULL DEFAULT \"&f\");";
        executeUpdate(playerNames);
    }

    public boolean isConnected() {
        return connection != null;
    }

}
