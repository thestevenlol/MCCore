package me.stevenlol.core.tasks;

import me.stevenlol.core.Core;
import me.stevenlol.core.player.InformationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayTimeTask {

    public PlayTimeTask() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(Core.getPlugin(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                InformationManager manager = new InformationManager();
                manager.updatePlayTime(player);
            }
        }, 0, 20 * 60);
    }

}
