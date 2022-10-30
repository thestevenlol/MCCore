package me.stevenlol.core.util;

import me.stevenlol.core.Core;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Cooldowns {

    private final List<HashMap<UUID, Integer>> coolDowns = new ArrayList<>();

    public Cooldowns() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(Core.getPlugin(), () -> {
            for (HashMap<UUID, Integer> cooldown : coolDowns) {
                for (UUID uuid : cooldown.keySet()) {
                    int time = cooldown.get(uuid);
                    if (time > 0) {
                        cooldown.put(uuid, time - 1);
                    }
                }
            }
        }, 0, 20);
    }

    public static boolean isDone(HashMap<UUID, Integer> map, UUID uuid) {
        if (map.containsKey(uuid)) {
            return map.get(uuid) == 0;
        }
        return false;
    }

    public static void setCooldown(HashMap<UUID, Integer> map, UUID uuid, int time) {
        map.put(uuid, time);
    }

    public static void clearCooldown(HashMap<UUID, Integer> map, UUID uuid) {
        map.remove(uuid);
    }


}
