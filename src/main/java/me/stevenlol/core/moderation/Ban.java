package me.stevenlol.core.moderation;

import java.util.UUID;

public class Ban extends Punishment {

    public Ban(UUID sender, UUID player, int duration, String reason, boolean isPermanent) {
        super(sender, player, duration, reason, isPermanent);
    }

    public Ban(UUID sender, UUID player, int duration, String reason) {
        super(sender, player, duration, reason);
    }

    @Override
    public boolean punish() {
        return false;
    }

    @Override
    public boolean removePunishment() {
        return false;
    }

}
