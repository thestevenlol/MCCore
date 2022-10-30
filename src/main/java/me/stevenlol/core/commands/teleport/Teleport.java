package me.stevenlol.core.commands.teleport;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import me.stevenlol.core.util.formatting.Color;
import org.bukkit.entity.Player;

public class Teleport {

    @CommandAlias("tpo")
    @CommandPermission("core.tp.override")
    @Description("Force teleport to a player.")
    @CommandCompletion("@players")
    @SuppressWarnings("unused")
    public void teleportOverride(Player player, String[] args) {
        if (args.length == 1) {
            Player target = player.getServer().getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Color.translate("&cThat player is not online!"));
                return;
            }
            player.teleportAsync(target.getLocation());
            player.sendMessage(Color.translate("&aYou have teleported to " + target.getName() + "."));
        } else {
            player.sendMessage(Color.translate("Usage: /tpo <player>"));
        }
    }

    @CommandAlias("tp")
    @CommandPermission("core.tp")
    @Description("Teleport to a player.")
    @CommandCompletion("@players")
    @SuppressWarnings("unused")
    public void teleport(Player player, String[] args) {

    }

}
