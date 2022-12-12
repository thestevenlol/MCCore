package me.stevenlol.core.util;

import me.stevenlol.core.Core;

public class SafetyChecks {

    // Getting main class via constructor because I feel likes it safer than a getter.
    private final Core core;

    public SafetyChecks(Core core) {
        this.core = core;
        hasValidChatFormat();
    }

    private void hasValidChatFormat() {
        if (core.getConfig().getString("chat.format") == null) {
            core.getLogger().severe("=================================================================");
            core.getLogger().severe("The chat format is not valid. Please check your config.yml file.");
            core.getLogger().severe("=================================================================");
        }
    }

}
