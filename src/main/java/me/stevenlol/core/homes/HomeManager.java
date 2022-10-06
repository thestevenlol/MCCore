package me.stevenlol.core.homes;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeManager {

    /**
     * This is a list of ALL homes in the database.
     */
    private List<Home> serverHomes;


    public HomeManager() {
        serverHomes = new ArrayList<>();
        loadHomes();
    }

    /**
     * This method loads all homes from the database.
     */
    private void loadHomes() {
        // TODO: Load homes from database.
    }

    /**
     * @param player The player to get the homes of.
     * @return A list of all homes the player owns.
     */
    public List<Home> getHomes(Player player) {
        return serverHomes.stream()
                .filter(h -> h.getOwner().equals(player.getUniqueId()))
                .collect(Collectors.toList());
    }

    /**
     * @param name The name of the home.
     * @return The home with the specified name.
     */
    public List<Home> getHomes(String name) {
        return serverHomes.stream()
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    /**
     * @param player The player to get the home of.
     * @param name The name of the home.
     * @return The home from the player with the specified home name, otherwise null.
     */
    public Home getHome(Player player, String name) {
        return serverHomes.stream()
                .filter(h -> h.getOwner().equals(player.getUniqueId()))
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}
