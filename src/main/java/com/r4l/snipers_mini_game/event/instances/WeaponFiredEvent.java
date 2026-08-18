package com.r4l.snipers_mini_game.event.instances;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.Event;

public class WeaponFiredEvent extends Event {
    
    private final Player player;
    private final Level level;
    
    public WeaponFiredEvent(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level;
    }
}