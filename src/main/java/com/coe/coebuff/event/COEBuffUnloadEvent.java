package com.coe.coebuff.event;

import com.coe.coebuff.buff.COEBuff;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class COEBuffUnloadEvent extends PlayerEvent {
    COEBuff buff;
    public COEBuffUnloadEvent(Player who, COEBuff buff) {
        super(who);
        this.buff = buff;
    }

    public COEBuff getBuff(){
        return buff;
    }

    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList(){
        return handlers;
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
