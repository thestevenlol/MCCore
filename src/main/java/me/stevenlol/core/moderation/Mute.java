package me.stevenlol.core.moderation;

import me.stevenlol.core.moderation.util.Punishment;
import me.stevenlol.core.moderation.util.PunishmentType;
import me.stevenlol.core.player.CorePlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class Mute implements Punishment {
    private String reason, date;
    private UUID staff;
    private int duration;
    private CorePlayer target;

    public Mute() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
        this.date = sdf.format(System.currentTimeMillis());
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public UUID getStaff() {
        return staff;
    }

    public void setStaff(UUID staff) {
        this.staff = staff;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void apply() {

    }

    @Override
    public void verify() {
        // TODO: Verify if the player already has an active mute.
    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void announce() {
        // TODO: Improve message.
        Bukkit.broadcast(Component.text(target.getPlayer().getName() + " has been muted by " + staff + " for " + duration + " seconds."));
    }

    @Override
    public void decrement() {
        duration--;
    }

    @Override
    public boolean isActive() {
        return duration > 0;
    }

    @Override
    public PunishmentType getType() {
        return PunishmentType.MUTE;
    }

}
