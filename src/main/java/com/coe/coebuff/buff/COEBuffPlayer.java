package com.coe.coebuff.buff;

import com.coe.coebuff.BuffMain;
import com.coe.coebuff.buff.COEBuff;
import com.coe.coebuff.event.COEBuffLoadEvent;
import com.coe.coebuff.event.COEBuffUnloadEvent;
import com.coe.coebuff.timer.Animation;
import com.coe.coebuff.timer.MainLoop;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class COEBuffPlayer implements Animation {
    static Set<COEBuffPlayer> plist = new HashSet<>();

    public static COEBuffPlayer getPlayer(Player p){
        if(p==null){
            return null;
        }
        for(COEBuffPlayer coep : plist){
            if(coep.pid==p.getUniqueId()){
                return coep;
            }
        }
        return new COEBuffPlayer(p);
    }

    private COEBuffPlayer(Player p){
        pid = p.getUniqueId();
        plist.add(this);
        MainLoop.register(this);
    }


    public void removeBuff(COEBuffType bt){
        for(COEBuff b : bflist){
            if(b.getType()==bt){
                Player p = Bukkit.getPlayer(pid);
                if(p!=null){
                    p.sendMessage("buff "+b.getType().toString()+" 已失效。");
                    COEBuffUnloadEvent e = new COEBuffUnloadEvent(p,b);
                    Bukkit.getServer().getPluginManager().callEvent(e);
                }

                bflist.remove(b);
                break;
            }
        }
    }

    UUID pid;
    Set<COEBuff> bflist = new HashSet<>();

    public void addBuff(COEBuff bf){
        removeBuff(bf.type);

        bflist.add(bf);
        Player p = Bukkit.getPlayer(pid);
        COEBuffLoadEvent e = new COEBuffLoadEvent(p,bf);
        BuffMain.main.getServer().getPluginManager().callEvent(e);

    }

    public int getBuffAmp(COEBuffType t){
        for(COEBuff bf : bflist){
            if(bf.type==t){
                return bf.amp;
            }
        }
        return 0;
    }

    @Override
    public void onUpdate() {
        Set<COEBuff> to_remove = new HashSet<>();
        for(COEBuff bf : bflist){
            bf.duration--;
            if(bf.duration<=0){
                to_remove.add(bf);
            }
        }

        Player p = Bukkit.getPlayer(pid);
        for(COEBuff rm : to_remove){
            removeBuff(rm.getType());
        }
    }

    public void printState() {
        Player p = Bukkit.getPlayer(pid);
        if(p!=null){
            p.sendMessage("状态列表：");
            for(COEBuff buff : bflist){
                p.sendMessage(
                        String.format("[%s] 等级 %d: 剩余%d秒",buff.type.toString(),buff.amp,buff.duration)
                );
            }
        }
    }
}
