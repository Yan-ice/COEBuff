package com.coe.coebuff;

import com.coe.coebuff.buff.COEBuff;
import com.coe.coebuff.buff.COEBuffPlayer;
import com.coe.coebuff.buff.COEBuffType;
import com.coe.coebuff.handler.LifeBuffHandler;
import com.coe.coebuff.handler.PowerBuffHandler;
import com.coe.coebuff.timer.MainLoop;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class BuffMain extends JavaPlugin {
    public static BuffMain main;
    static Set<Listener> lset = new HashSet<>();
    private void enable(Class<? extends Listener> c){
        try {
            Listener l = c.newInstance();
            lset.add(l);
            getServer().getPluginManager().registerEvents(l,this);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void onEnable(){
        main = this;
        enable(LifeBuffHandler.class);
        enable(PowerBuffHandler.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = null;

        for(Player pl : Bukkit.getOnlinePlayers()){
            if(pl.getName().equals(args[1])){
                p = pl;
            }
        }
        COEBuffPlayer bp = COEBuffPlayer.getPlayer(p);

        switch(args[0]){
            case "add":
                COEBuffType buffType = COEBuffType.loadType(Integer.valueOf(args[2]));
                bp.addBuff(new COEBuff(buffType,Integer.valueOf(args[4]),Integer.valueOf(args[3])));
                p.sendMessage("已添加buff "+buffType.toString()+" 。");
                break;
            case "remove":
                COEBuffType buffType2 = COEBuffType.loadType(Integer.valueOf(args[2]));
                bp.removeBuff(buffType2);
                break;
            case "list":
                bp.printState();
        }
        return true;
    }
}
