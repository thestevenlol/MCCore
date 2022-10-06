package me.stevenlol.core.moderation.util;

public interface Punishment {

    void apply();
    void verify();
    void update();
    void remove();
    void announce();
    void decrement();
    boolean isActive();
    PunishmentType getType();

}
