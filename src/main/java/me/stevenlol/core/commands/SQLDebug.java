package me.stevenlol.core.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import lombok.SneakyThrows;
import me.stevenlol.core.Core;
import me.stevenlol.core.sql.Database;
import me.stevenlol.core.util.Colour;
import org.bukkit.entity.Player;

@CommandAlias("sql")
public class SQLDebug extends BaseCommand {

    @SneakyThrows
    @Subcommand("debug")
    @CommandPermission("core.admin")
    @SuppressWarnings("unused")
    public void debug(Player player) {
        final Database database = Core.getDatabase();

        player.sendMessage(Colour.translate("&a&lSQL Debug"));
        player.sendMessage(Colour.translate("&7Database Connected: " + (database.isConnected() ? "&aYes" : "&cNo")));
    }

}
