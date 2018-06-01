package com.example.codeclan.combat;

public abstract class Healer extends Character {
    private double maxMp, mp;
    public Healer(String name, double maxHp, double hp, double attPower, double maxMp, double mp) {
        super(name, maxHp, hp, attPower);
        this.maxMp = maxMp;
        this.mp = mp;
    }

    public double getMaxMp() {
        return this.maxMp;
    }

    public double getMp() {
        return this.mp;
    }

    public void setMaxMp(double maxMp) {
        this.maxMp = maxMp;
    }

    public void setMp(double mp) {
        this.mp = mp;
    }

    public void basicAttack(Character character){
        double extra = getRandomNumber(20);
        double total = extra + this.getAttPower();
        character.reciveDamage(total);
        this.mp += total / 2;
        if (this.mp > getMaxMp()){
            this.mp = maxMp;
        }
    }

    // HEALER SPECIAL ATTACK HEALS HIMSELF
    public void specialAttack(Character character){
        if (this.mp >= 50){
            character.setHp(( character.getHp() + this.getAttPower() * 2.5));
            if (character.getHp() > character.getMaxHp()){
                character.setHp(character.getMaxHp());
            }
            System.out.println(String.format("%s has healed itself  %.2f points of HP -  %s has %.2f hp!",this.getName(), (this.getAttPower() * 2), this.getName(), this.getHp()));
            this.mp -= 50;
        }
    }

    public void actionBack(Character character){
        if (this.mp >= 50 && this.getHp() <= 135){
            this.specialAttack(this);
        } else {
            this.basicAttack(character);
        }
    }


}
