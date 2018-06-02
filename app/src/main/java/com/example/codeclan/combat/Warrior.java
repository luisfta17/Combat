package com.example.codeclan.combat;

public abstract class Warrior extends Character {

    private double maxAdrenaline, adrenaline;

    public Warrior(String name, double maxHp, double hp, double attPower, double maxAdrenaline, double adrenaline ){
        super(name, maxHp, hp, attPower);
        this.maxAdrenaline = maxAdrenaline;
        this.adrenaline = adrenaline;
    }

    public double getMaxAdrenaline() {
        return this.maxAdrenaline;
    }

    public void setMaxAdrenaline(double maxAdrenaline) {
        this.maxAdrenaline = maxAdrenaline;
    }

    public double getAdrenaline() {
        return this.adrenaline;
    }

    public void setAdrenaline(double adrenaline) {
        this.adrenaline = adrenaline;
    }


    public void basicAttack(Character character){
        double extra = getRandomNumber(10);
        double total = extra + this.getAttPower();
        character.reciveDamage(total);
        this.adrenaline += total;
        if (this.adrenaline > getMaxAdrenaline()){
            this.adrenaline = maxAdrenaline;
        }
    }

    public void specialAttack(Character character){
        if (this.adrenaline >= 50){
            character.reciveDamage(this.getAttPower() * 2);
            System.out.println(String.format("%s used a Special attack!",this.getName()));
            this.adrenaline -= 50;
        }
    }

    public void actionBack(Character character){
        if (this.adrenaline >= 50){
            this.specialAttack(character);
        } else {
            this.basicAttack(character);
        }
    }
}
