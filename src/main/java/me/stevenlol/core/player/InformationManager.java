package me.stevenlol.core.player;

import lombok.SneakyThrows;
import me.stevenlol.core.Core;
import me.stevenlol.core.storage.Fetch;
import me.stevenlol.core.storage.SQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class InformationManager {

    private final SQL sql = Core.getSql();

    /**
     * Updates the last join of a player.
     * Should only be called when a player joins for the first time.
     * @param player The player information to create.
     */
    @SneakyThrows
    public void createPlayer(Player player) {
        exists(player, r -> {
            if (!r) {
                String time = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
                PreparedStatement statement = sql.createStatement("INSERT INTO player_information (uuid, first_join, last_join, last_quit, play_time) VALUES (?, ?, ?, ?, ?)");
                statement.setString(1, player.getUniqueId().toString());
                statement.setString(2, time);
                statement.setString(3, time);
                statement.setString(4, "null");
                statement.setInt(5, 0);
                sql.update(statement);
            }
        });
    }

    public void exists(Player player, Fetch<Boolean> fetch) {
        Bukkit.getScheduler().runTaskAsynchronously(Core.getPlugin(), () -> {
            try (PreparedStatement statement = sql.createStatement("SELECT * FROM player_information WHERE uuid = ?")) {
                statement.setString(1, player.getUniqueId().toString());
                fetch.give(statement.executeQuery().next());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void getPlayTime(Player player, Fetch<Integer> fetch) {
        Bukkit.getScheduler().runTaskAsynchronously(Core.getPlugin(), () -> {
            try (PreparedStatement statement = sql.createStatement("SELECT play_time FROM player_information WHERE uuid = ?")) {
                statement.setString(1, player.getUniqueId().toString());
                fetch.give(statement.executeQuery().getInt("play_time"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @SneakyThrows
    public void updateLastJoin(Player player) {
        String time = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        PreparedStatement statement = sql.createStatement("UPDATE player_information SET last_join = ? WHERE uuid = ?");
        statement.setString(1, time);
        statement.setString(2, player.getUniqueId().toString());
        sql.update(statement);
    }

    @SneakyThrows
    public void updateLastQuit(Player player) {
        String time = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        PreparedStatement statement = sql.createStatement("UPDATE player_information SET last_quit = ? WHERE uuid = ?");
        statement.setString(1, time);
        statement.setString(2, player.getUniqueId().toString());
        sql.update(statement);
    }

    @SneakyThrows
    public void updatePlayTime(Player player) {
        getPlayTime(player, time -> {
            PreparedStatement statement = sql.createStatement("UPDATE player_information SET play_time = ? WHERE uuid = ?");
            statement.setInt(1, time + 60);
            statement.setString(2, player.getUniqueId().toString());
            sql.update(statement);
        });
    }

}
