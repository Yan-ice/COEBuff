package com.coe.coebuff.buff;

public enum COEBuffType {
    NONE(0,"未知效果"),
    POWER(1,"力量"),
    LIFE(2,"生命提升");


    int id;
    String name;
    COEBuffType(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static COEBuffType loadType(int id){
        for(COEBuffType t : COEBuffType.values()){
            if(t.id==id){
                return t;
            }
        }
        return NONE;
    }
}
