package me.stevenlol.core.commands.gamemode;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import me.stevenlol.core.util.formatting.Color;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameModeCommand extends BaseCommand {

    @CommandAlias("gmc")
    @CommandPermission("core.gamemode.creative")
    @SuppressWarnings("unused")
    public void gmc(Player player) {
        if (!player.getGameMode().equals(GameMode.CREATIVE)) {
            player.setGameMode(GameMode.CREATIVE);
            return;
        }
        player.sendMessage(Color.translate("&cYou are already in creative mode!"));
    }

    @CommandAlias("gms")
    @CommandPermission("core.gamemode.survival")
    @SuppressWarnings("unused")
    public void gms(Player player) {
        if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
            player.setGameMode(GameMode.SURVIVAL);
            return;
        }
        player.sendMessage(Color.translate("&cYou are already in survival mode!"));
    }

    @CommandAlias("gma")
    @CommandPermission("core.gamemode.adventure")
    @SuppressWarnings("unused")
    public void gma(Player player) {
        if (!player.getGameMode().equals(GameMode.ADVENTURE)) {
            player.setGameMode(GameMode.ADVENTURE);
            return;
        }
        player.sendMessage(Color.translate("&cYou are already in adventure mode!"));
    }

    @CommandAlias("gmsp")
    @CommandPermission("core.gamemode.spectator")
    @SuppressWarnings("unused")
    public void gmsp(Player player) {
        if (!player.getGameMode().equals(GameMode.SPECTATOR)) {
            player.setGameMode(GameMode.SPECTATOR);
            return;
        }
        player.sendMessage(Color.translate("&cYou are already in spectator mode!"));
    }

}
