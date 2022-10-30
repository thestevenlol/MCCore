package me.stevenlol.core.moderation;

import java.util.UUID;

public abstract class Punishment {

    private UUID sender;
    private UUID player;
    private int duration;
    private String reason;
    private boolean isPermanent;

    // Error Strings
    private final String negativeDurationError = "Duration cannot be negative!\nIf you want to make a permanent punishment, use the other constructor.";

    public Punishment(UUID sender, UUID player, int duration, String reason, boolean isPermanent) {
        this.sender = sender;
        this.player = player;
        this.reason = reason;
        this.isPermanent = isPermanent;

        if (isPermanent) {
            this.duration = -1;
        }

        if (!isPermanent) {
            if (duration < 0) {
                throw new IllegalArgumentException(negativeDurationError);
            } else {
                this.duration = duration;
            }
        }
    }

    public Punishment(UUID sender, UUID player, int duration, String reason) {
        this.sender = sender;
        this.player = player;
        this.reason = reason;

        if (duration < 0) {
            throw new IllegalArgumentException(negativeDurationError);
        } else {
            this.duration = duration;
        }
    }

    public UUID getSender() {
        return sender;
    }

    public void setSender(UUID sender) {
        this.sender = sender;
    }

    public UUID getPlayer() {
        return player;
    }

    public void setPlayer(UUID player) {
        this.player = player;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration < 0) {
            throw new IllegalArgumentException(negativeDurationError);
        } else {
            this.duration = duration;
        }
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isPermanent() {
        return isPermanent;
    }

    public void setPermanent(boolean permanent) {
        isPermanent = permanent;
    }

    public boolean canPunish() {
        return (this.isPermanent() || this.getDuration() > 0);
    }

    public abstract boolean punish();
    public abstract boolean removePunishment();

    public String toString() {
        return "Punishment{" +
                "sender=" + sender +
                ", player=" + player +
                ", duration=" + duration +
                ", reason='" + reason + '\'' +
                ", isPermanent=" + isPermanent +
                '}';
    }

}
