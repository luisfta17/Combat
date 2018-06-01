package com.example.codeclan.combat;

import java.util.Random;

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

    public int getRandomNumber(int maxNumber){
        Random random = new Random();
        int  number = random.nextInt(maxNumber) + 1;
        return number;
    }

    public boolean criticalHit(){
        if (getRandomNumber(10) > 7){
            return true;
        } else {
            return false;
        }
    }

    public void reciveDamage(double damage) {
        if (criticalHit()) {
            double total = damage * 2.0;
            this.hp -= total;
            if (!this.isAlive()) {
                this.hp = 0;
            }
        } else {
            double total = damage;
            this.hp -= total;
            if (!this.isAlive()) {
                this.hp = 0;
            }
        }

    }

    public abstract void basicAttack(Character character);
}
