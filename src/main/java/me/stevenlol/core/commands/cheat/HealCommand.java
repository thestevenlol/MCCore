package me.stevenlol.core.commands.cheat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import org.bukkit.entity.Player;

public class HealCommand extends BaseCommand {

    @CommandAlias("heal")
    @CommandPermission("core.cheat.heal")
    @SuppressWarnings("unused")
    public void heal(Player player, String[] args) {
        if (args.length == 0) {
            player.setHealth(20);
            player.sendMessage("You have been healed.");
            return;
        }

        Player target = player.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("That player is not online!");
            return;
        }
        target.setHealth(20);
        target.sendMessage("You have been healed by " + player.getName() + ".");
        player.sendMessage("You have healed " + target.getName() + ".");
    }

}
