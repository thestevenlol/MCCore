package me.stevenlol.core.events;

import lombok.SneakyThrows;
import me.stevenlol.core.Core;
import me.stevenlol.core.sql.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class PlayerJoin implements Listener {

    @EventHandler
    @SneakyThrows
    public void onJoin(PlayerJoinEvent e) {
        // Default values for new player
        final Player player = e.getPlayer();
        final String username = player.getName();
        final String uuid = player.getUniqueId().toString();
        final String colour = "&f";

        final Database database = Core.getDatabase();
        final HashMap<UUID, String[]> cache = Core.getNameCache();
        final String[] defaultData = {username, colour};

        // 1. Check if player has joined
        // 2. If not, create a new row for them with the above default information.
        // 3. Add default information to the cache.
        // 4. If they have a row, retrieve their information from the database and store it in the nameCache.

        // 1. Check if the player has joined.
        // Create the statement and fill in uuid.
        final PreparedStatement statement = database.createStatement("SELECT * FROM PlayerNames WHERE uuid = ?");
        statement.setString(1, uuid);

        // Execute the statement and check if the result has any rows.

        Bukkit.getScheduler().runTaskAsynchronously(Core.getPlugin(), () -> {
            try (final ResultSet result = statement.executeQuery()) {
                if (result.next()) {

                    // 4. If they have a row, retrieve their information from the database and store it in the nameCache.
                    final String[] data = {
                            result.getString("displayname"),
                            result.getString("colour")
                    };

                    cache.put(player.getUniqueId(), data);

                } else {

                    // 2. If not, create a new row for them with the above default information.
                    final PreparedStatement insertStatement = database.createStatement("INSERT INTO PlayerNames (uuid, displayname, colour) VALUES (?, ?, ?)");
                    insertStatement.setString(1, uuid);
                    insertStatement.setString(2, username);
                    insertStatement.setString(3, colour);
                    database.executeUpdate(insertStatement);

                    // 3. Add default information to the cache.
                    cache.put(player.getUniqueId(), defaultData);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

    }

}
