package me.stevenlol.core.commands;

import co.aikar.commands.PaperCommandManager;
import me.stevenlol.core.Core;

import java.util.Arrays;

public class CommandCompleters {

    public CommandCompleters() {
        PaperCommandManager manager = Core.getCommandManager();
        manager.getCommandCompletions().registerCompletion("EnchantNames", c -> {
            String[] enchantmentNames = new String[] {
                    "sharpness",
                    "protection",
                    "fire_protection",
                    "feather_falling",
                    "blast_protection",
                    "projectile_protection",
                    "respiration",
                    "aqua_affinity",
                    "thorns",
                    "depth_strider",
                    "frost_walker",
                    "binding_curse",
                    "sweeping_edge",
                    "efficiency",
                    "silk_touch",
                    "unbreaking",
                    "fortune",
                    "power",
                    "punch",
                    "flame",
                    "infinity",
                    "luck_of_the_sea",
                    "lure",
                    "mending",
                    "vanishing_curse",
                    "loyalty",
                    "impaling",
                    "riptide",
                    "channeling",
                    "multishot",
                    "quick_charge",
                    "piercing"
            };

            return Arrays.asList(enchantmentNames);
        });
    }

}
