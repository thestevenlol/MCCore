package me.stevenlol.core.storage;

import lombok.SneakyThrows;
import me.stevenlol.core.Core;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.*;

public class SQL {

    private Connection connection;
    private File file;

    public SQL() {

    }

    // Tables
    public void createTables() {
        Core.getPlugin().getLogger().info("Creating tables.");

        /*
        id - primary key - auto increment - integer - not null
        uuid - VARCHAR(36)
        first_join - VARCHAR(10)
        last_join - VARCHAR(10)
        last_quit - VARCHAR(10)
        play_time - integer
         */
        update(createStatement("CREATE TABLE IF NOT EXISTS player_information (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "uuid VARCHAR(36)," +
                "first_join VARCHAR(10)," +
                "last_join VARCHAR(10)," +
                "last_quit VARCHAR(10)," +
                "play_time INTEGER" +
                ")"));

        Core.getPlugin().getLogger().info("Tables created.");
    }

    // Operations
    @SneakyThrows
    public PreparedStatement createStatement(String sql) {
        return this.connection.prepareStatement(sql);
    }

    public void update(final PreparedStatement statement) {
        Bukkit.getScheduler().runTaskAsynchronously(Core.getPlugin(), () -> {
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @SneakyThrows
    public ResultSet query(final PreparedStatement statement) {
        return statement.executeQuery();
    }

    // Control
    @SneakyThrows
    public boolean start() {
        file = new File(Core.getPlugin().getDataFolder(), "storage.db");
        if (!file.exists()) {
            Core.getPlugin().getLogger().info("Creating database...");
            return file.createNewFile();
        }

        connection = DriverManager.getConnection("jdbc:sqlite:" + file.getPath());

        return connection != null;
    }

    @SneakyThrows
    public void stop() {
        if (connection != null) {
            Core.getPlugin().getLogger().info("Closing database connection...");
            connection.close();
        }
    }

    // Getters
    public Connection getConnection() {
        return connection;
    }

    public File getFile() {
        return file;
    }

}
