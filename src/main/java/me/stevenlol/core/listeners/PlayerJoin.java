package me.stevenlol.core.listeners;

import me.stevenlol.core.player.InformationManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private final InformationManager manager = new InformationManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        manager.createPlayer(player);
        manager.updateLastJoin(player);
    }

}
