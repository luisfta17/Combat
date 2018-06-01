package com.example.codeclan.combat;

public class Warrior extends Character {
    private double maxAdrenaline, adrenaline;

    public Warrior(String name, double maxHp, double hp, double attPower, double maxAdrenaline, double adrenaline ){
        super(name, maxHp, hp, attPower);
        this.maxAdrenaline = maxAdrenaline;
        this.adrenaline = adrenaline;
    }

    public void basicAttack(Character character){
        double extra = getRandomNumber(10);
        character.reciveDamage(this.getAttPower() + extra);
        this.adrenaline += this.getAttPower() + extra;
    }

}
