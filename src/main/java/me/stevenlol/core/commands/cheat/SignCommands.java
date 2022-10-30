package me.stevenlol.core.commands.cheat;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import me.stevenlol.core.util.formatting.Color;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

public class SignCommands extends BaseCommand {

    @CommandAlias("editsign")
    @CommandPermission("core.sign.edit")
    @SuppressWarnings("unused")
    public void editSign(Player player) {
        Block block = player.getTargetBlock(null, 2);
        if (!(block.getState() instanceof Sign)) {
            player.sendMessage(Color.translate("&cYou must be looking at a sign!"));
            return;
        }
        Sign sign = (Sign) block.getState();
        player.openSign(sign);
    }

}
