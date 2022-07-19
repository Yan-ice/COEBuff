package com.coe.coebuff.timer;

import com.coe.coebuff.BuffMain;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class MainLoop {
    static Set<Animation> ani_list = new HashSet<>();
    static Set<Animation> unreg_list = new HashSet<>();
    public static void register(Animation e){
        ani_list.add(e);
    }
    public static void unregister(Animation e){
        unreg_list.add(e);
    }

    static BukkitRunnable timer = new BukkitRunnable() {
        @Override
        public void run() {
            for(Animation a : ani_list){
                a.onUpdate();
            }
            for(Animation r : unreg_list){
                ani_list.remove(r);
            }
            unreg_list.clear();
        }
    };

    static{
        timer.runTaskTimer(BuffMain.main,20,20);
    }

}
