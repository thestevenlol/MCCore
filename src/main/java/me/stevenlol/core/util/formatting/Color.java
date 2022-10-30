package me.stevenlol.core.util.formatting;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;

public class Color {

    public static Component translate(String message) {

        return Component.text(ChatColor.translateAlternateColorCodes('&', message));

    }

}
