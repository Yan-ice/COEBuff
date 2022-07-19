package com.coe.coebuff.buff;

public class COEBuff {
    COEBuffType type;
    int amp;
    int duration;
    public COEBuff(COEBuffType type,int amp,int duration){
        this.type = type;
        this.amp = amp;
        this.duration = duration;
    }
    public COEBuffType getType(){
        return type;
    }
    public int getAmp(){
        return amp;
    }
    public boolean replace(COEBuff another){
        if(another.type==this.type){
            if(another.amp==amp){
                duration = duration+another.duration;
                return true;
            }
        }
        return false;
    }
    public boolean equals(Object b){
        if(!(b instanceof COEBuff)){
            return false;
        }
        return this.type==((COEBuff)b).type;
    }
}
