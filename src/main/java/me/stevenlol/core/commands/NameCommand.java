package me.stevenlol.core.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import lombok.SneakyThrows;
import me.stevenlol.core.Core;
import me.stevenlol.core.sql.Database;
import me.stevenlol.core.util.Colour;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Pattern;

@CommandAlias("name")
@Description("Change things about your name.")
public class NameCommand extends BaseCommand {

    final OfflinePlayer[] players = Bukkit.getOfflinePlayers();
    final Database database = Core.getDatabase();
    final HashMap<UUID, String[]> cache = Core.getNameCache();

    @Subcommand("set")
    @CommandCompletion("@nothing")
    @SneakyThrows
    @SuppressWarnings("unused")
    public void set(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(Colour.translate("&cYou must specify a name."));
            player.sendMessage(Colour.translate("&cExample: /name set Rob"));
            return;
        }

        if (args.length == 1) {
            final String name = args[0];
            final Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");

            // Check for size limit
            if (name.length() > 16) {
                player.sendMessage(Colour.translate("&cYour name must be 16 characters or less."));
                return;
            }

            // Check for invalid characters
            if (!pattern.matcher(name).matches()) {
                player.sendMessage(Colour.translate("&cYour name must only contain letters and numbers."));
                return;
            }

            // Prevent players using names of other players
            for (OfflinePlayer offlinePlayer : players) {
                if (offlinePlayer.getName() == null) continue; // Skip if null
                if (offlinePlayer.getName().equalsIgnoreCase(name)) continue; // Skip if same player
                if (offlinePlayer.getName().equalsIgnoreCase(name)) {
                    player.sendMessage(Colour.translate("&cYou cannot use the names of other players."));
                    return;
                }
            }

            // 1. Update the cache
            // 2. Update the database

            // 1. Update the cache
            String[] cache = this.cache.get(player.getUniqueId());
            cache[0] = name;
            this.cache.put(player.getUniqueId(), cache);

            // 2. Update the database
            final PreparedStatement statement = database.createStatement("UPDATE PlayerNames SET displayname = ? WHERE uuid = ?");
            statement.setString(1, name);
            statement.setString(2, player.getUniqueId().toString());

            // Execute the statement
            database.executeUpdate(statement);

            // Send the player a message
            player.sendMessage(Colour.translate("&aYour name has been changed to " + this.cache.get(player.getUniqueId())[1] + name));
            return;
        }

        player.sendMessage(Colour.translate("&cYou cannot have spaces in your name."));
    }

    @Subcommand("reset")
    @CommandCompletion("@nothing")
    @SneakyThrows
    @SuppressWarnings("unused")
    public void reset(Player player) {
        // 1. Update the cache
        // 2. Update the database

        // 1. Update the cache
        String[] cache = this.cache.get(player.getUniqueId());
        cache[0] = player.getName();
        cache[1] = "&f";
        this.cache.put(player.getUniqueId(), cache);

        // 2. Update the database
        final PreparedStatement statement = database.createStatement("UPDATE PlayerNames SET displayname = ?, colour = ? WHERE uuid = ?");
        statement.setString(1, player.getName());
        statement.setString(2, "&f");
        statement.setString(3, player.getUniqueId().toString());

        // Execute the statement
        database.executeUpdate(statement);

        // Send the player a message
        player.sendMessage(Colour.translate("&aYour name has been reset to default."));

    }

    @Subcommand("colour")
    @CommandCompletion("@colours")
    @SneakyThrows
    @SuppressWarnings("unused")
    public void color(Player player, String[] args) {
        // Create regex for valid color codes
        final String[] colours = {
                "&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9",
                "&a", "&b", "&c", "&d", "&e", "&f"
        };

        if (args.length == 0) {
            player.sendMessage(Colour.translate("&cYou must specify a color."));
            player.sendMessage(Colour.translate("&cExample: /name color &c"));
            return;
        }

        if (args.length == 1) {
            final String color = args[0];
            if (color == null) return;
            if (color.length() > 2) return;
            if (!color.startsWith("&")) return;

            final Pattern pattern = Pattern.compile(String.join("|", colours));

            if (!pattern.matcher(color).matches()) {
                player.sendMessage(Colour.translate("&cYou must specify a valid color."));
                return;
            }

            // Check if color is valid
            for (String colour : colours) {
                if (color.equalsIgnoreCase(colour)) {

                    // 1. Update the cache
                    // 2. Update the database

                    // 1. Update the cache
                    String[] cache = this.cache.get(player.getUniqueId());
                    cache[1] = color;
                    this.cache.put(player.getUniqueId(), cache);

                    // 2. Update the database
                    final PreparedStatement statement = database.createStatement("UPDATE PlayerNames SET colour = ? WHERE uuid = ?");
                    statement.setString(1, color);
                    statement.setString(2, player.getUniqueId().toString());

                    // Execute the statement
                    database.executeUpdate(statement);

                    // Send the player a message
                    player.sendMessage(Colour.translate("&aYour colour has been changed to " + color + this.cache.get(player.getUniqueId())[0]));

                    return;
                }
            }

            player.sendMessage(Colour.translate("&cYou must specify a valid color."));
            player.sendMessage(Colour.translate("&cExample: /name color &c"));
        }
    }


}
