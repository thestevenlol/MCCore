package me.stevenlol.core.player;

import me.stevenlol.core.homes.Home;
import me.stevenlol.core.moderation.util.Punishment;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CorePlayer {

    // Refers to the Player class the CorePlayer class is based on.
    // It is better to do it this way rather than extending the Player class.
    private final Player player;
    private List<Punishment> punishments;
    private List<Home> homes;

    public CorePlayer(Player player) {
        this.player = player;
        loadPunishments();
    }

    private void loadPunishments() {
        punishments = new ArrayList<>();
        // TODO: Load punishments from database.
    }

    private void loadHomes() {
        homes = new ArrayList<>();
        // TODO: Load homes from database.
    }

    public Player getPlayer() {
        return player;
    }

    public List<Punishment> getPunishments() {
        return punishments;
    }

    // TODO: Create a method to return a specific punishment.

    public List<Home> getHomes() {
        return homes;
    }

    public Home getHome(String name) {
        return this.homes.stream()
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
