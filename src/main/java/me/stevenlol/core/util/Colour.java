package me.stevenlol.core.util;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;

public class Colour {

    public static Component translate(String message) {
        return Component.text(ChatColor.translateAlternateColorCodes('&', message));
    }

}
