package com.example.codeclan.combat.character;

public abstract class Character {
    private String name;
    private double maxHp, hp, attPower;

    public Character(String name, double maxHp, double hp, double attPower ){
        this.name = name;
        this.maxHp = maxHp;
        this.hp = hp;
        this.attPower = attPower;
    }

    public String getName() {
        return this.name;
    }

    public double getMaxHp() {
        return this.maxHp;
    }

    public double getHp() {
        return this.hp;
    }

    public double getAttPower() {
        return this.attPower;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHp(double maxHp) {
        this.maxHp = maxHp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setAttPower(double attPower) {
        this.attPower = attPower;
    }

    public boolean isAlive(){
        return this.getHp() > 0;
    }

    public void reciveDamage(double damage){
        double total = damage;
        this.hp -= total;
        if (!this.isAlive()){
            this.hp = 0;
        }
    }


}
