package me.stevenlol.core.commands.cheat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import me.stevenlol.core.util.formatting.Color;
import org.bukkit.entity.Player;

public class FeedCommand extends BaseCommand {

    @CommandAlias("feed")
    @CommandPermission("core.cheat.feed")
    @SuppressWarnings("unused")
    public void feed(Player player, String[] args) {
        if (args.length == 0) {
            player.setFoodLevel(20);
            player.sendMessage(Color.translate("&aYou have been fed."));
            return;
        }

        Player target = player.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(Color.translate("&cThat player is not online!"));
            return;
        }
        target.setFoodLevel(20);
        target.sendMessage(Color.translate("&aYou have been fed by " + player.getName() + "."));
        player.sendMessage(Color.translate("&aYou have fed " + target.getName() + "."));
    }

}
