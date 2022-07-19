package com.coe.coebuff.handler;

import com.coe.coebuff.BuffMain;
import com.coe.coebuff.buff.COEBuffPlayer;
import com.coe.coebuff.buff.COEBuffType;
import com.coe.coebuff.event.COEBuffLoadEvent;
import com.coe.coebuff.event.COEBuffUnloadEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PowerBuffHandler implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            COEBuffPlayer p = COEBuffPlayer.getPlayer((Player)e.getDamager());

            int amp = p.getBuffAmp(COEBuffType.POWER);
            if(amp>0){
                e.setDamage(e.getDamage()+100*amp);
            }
        }
    }
}
