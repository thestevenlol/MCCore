package me.stevenlol.core.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.stevenlol.core.Core;
import me.stevenlol.core.util.Colour;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatEventListener implements Listener {

    @EventHandler
    public void onChatAsync(AsyncChatEvent event) {

        final String format = Core.getPlugin().getConfig().getString("chat.format");
        final String[] data = Core.getNameCache().get(event.getPlayer().getUniqueId());

        if (format == null) return;

        event.renderer((source, sourceDisplayName, message, viewer) -> {
            String displayName = data[0];
            String colour = data[1];

            if (displayName == null) {
                displayName = event.getPlayer().getName();
            }

            if (colour == null) {
                colour = "&f";
            }

            final String formattedString = format
                    .replace("%displayname%", colour + displayName)
                    .replace("%message%", PlainTextComponentSerializer.plainText().serialize(message));

            return Colour.translate(formattedString);
        });

    }

}
