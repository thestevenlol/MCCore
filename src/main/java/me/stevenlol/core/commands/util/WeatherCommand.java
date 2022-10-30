package me.stevenlol.core.commands.util;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import me.stevenlol.core.util.formatting.Color;
import org.bukkit.entity.Player;

public class WeatherCommand extends BaseCommand {

    @CommandAlias("weather")
    @CommandPermission("core.util.weather")
    @SuppressWarnings("unused")
    public void weather(Player player, String[] args) {
        String[] weather = {"sun", "rain", "thunder", "clear"};
        if (args.length == 0) {
            player.sendMessage("Usage: /weather <sun/clear/rain/thunder>");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "sun", "clear" -> {
                player.getWorld().setStorm(false);
                player.getWorld().setThundering(false);
                player.sendMessage(Color.translate("&aWeather has been set to sun."));
            }
            case "rain" -> {
                player.getWorld().setStorm(true);
                player.getWorld().setThundering(false);
                player.sendMessage(Color.translate("&aWeather has been set to rain."));
            }
            case "thunder" -> {
                player.getWorld().setStorm(true);
                player.getWorld().setThundering(true);
                player.sendMessage(Color.translate("&aWeather has been set to thunder."));
            }
            default -> player.sendMessage("Usage: /weather <sun/rain/thunder>");
        }
    }

}
