package com.coe.coebuff.handler;

import com.coe.coebuff.BuffMain;
import com.coe.coebuff.buff.COEBuffType;
import com.coe.coebuff.event.COEBuffLoadEvent;
import com.coe.coebuff.event.COEBuffUnloadEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LifeBuffHandler implements Listener {

    @EventHandler
    public void onLoad(COEBuffLoadEvent e){
        if(e.getBuff().getType() == COEBuffType.LIFE){
            e.getPlayer().setMaxHealth(
                    e.getPlayer().getMaxHealth() + e.getBuff().getAmp()*2
            );
        }
    }

    @EventHandler
    public void onUnload(COEBuffUnloadEvent e){
        if(e.getBuff().getType() == COEBuffType.LIFE){
            e.getPlayer().setMaxHealth(
                    e.getPlayer().getMaxHealth() - e.getBuff().getAmp()*2
            );
        }
    }
}
