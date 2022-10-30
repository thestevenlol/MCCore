package me.stevenlol.core.tests;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import me.stevenlol.core.Core;
import me.stevenlol.core.player.InformationManager;
import me.stevenlol.core.util.formatting.Color;
import me.stevenlol.core.util.formatting.IntegerToTimeString;
import org.bukkit.entity.Player;

@CommandAlias("test")
public class SQLiteTest extends BaseCommand {

    @Subcommand("sql")
    @CommandPermission("core.test.sql")
    @SuppressWarnings("unused")
    public void testSql(Player player) {
        boolean result = Core.getSql().getFile().exists();
        player.sendMessage(Color.translate("&e[ FILE SYSTEM ]"));
        player.sendMessage(Color.translate("&7SQLite file exists: " + (result ? "&aYes" : "&cNo")));
        player.sendMessage(Color.translate("&7SQLite file path: &e" + Core.getSql().getFile().getPath()));
        player.sendMessage("");
        player.sendMessage(Color.translate("&e[ CONNECTIONS ]"));
        player.sendMessage(Color.translate("&7SQLite connection exists: " + (Core.getSql().getConnection() != null ? "&aYes" : "&cNo")));
    }

    @Subcommand("pinfo")
    @CommandPermission("core.test.pinfo")
    @SuppressWarnings("unused")
    public void testPInfo(Player player) {
        InformationManager manager = new InformationManager();
        manager.createPlayer(player);
    }

    @Subcommand("playtime|pt")
    @CommandPermission("core.test.playtime")
    @SuppressWarnings("unused")
    public void testPlayTime(Player player) {
        InformationManager manager = new InformationManager();
        manager.getPlayTime(player, time -> {
            player.sendMessage(Color.translate("\n&e[ PLAY TIME ]"));
            player.sendMessage(Color.translate("&7Play time: &e" + IntegerToTimeString.convert(time) + "\n"));
        });
    }

}
