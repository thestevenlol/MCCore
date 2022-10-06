package me.stevenlol.core.homes;

import me.stevenlol.core.player.CorePlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Home {

    private float x, y, z;
    private double yaw, pitch;
    private String world;
    private String name;
    private OfflinePlayer owner;

    public Home() {

    }

    public Home(Player player) {
        this.x = player.getLocation().getBlockX();
        this.y = player.getLocation().getBlockY();
        this.z = player.getLocation().getBlockZ();
        this.yaw = player.getLocation().getYaw();
        this.pitch = player.getLocation().getPitch();
        this.world = player.getWorld().getName();
    }

    public Home(CorePlayer corePlayer) {
        this.x = corePlayer.getPlayer().getLocation().getBlockX();
        this.y = corePlayer.getPlayer().getLocation().getBlockY();
        this.z = corePlayer.getPlayer().getLocation().getBlockZ();
        this.yaw = corePlayer.getPlayer().getLocation().getYaw();
        this.pitch = corePlayer.getPlayer().getLocation().getPitch();
        this.world = corePlayer.getPlayer().getWorld().getName();
    }

    public Home(float x, float y, float z, double yaw, double pitch, String world, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
        this.name = name;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public double getYaw() {
        return yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public String getWorld() {
        return world;
    }

    public String getName() {
        return name;
    }

    public UUID getOwner() {
        return owner.getUniqueId();
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(OfflinePlayer owner) {
        this.owner = owner;
    }
}
