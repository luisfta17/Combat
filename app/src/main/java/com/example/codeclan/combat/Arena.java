package com.example.codeclan.combat;

import java.util.ArrayList;
import java.util.Collections;

public class Arena {
    private ArrayList<Character> fighters;
    private static final int TIME_DELAY = 2000;

    public Arena(){
        this.fighters = new ArrayList<>();
    }

    public ArrayList<Character> getFighters() {
        return this.fighters;
    }

    public void addPlayer(Character character){
        this.fighters.add(character);
    }

    public String fightTillDead() throws InterruptedException{
        Collections.shuffle(fighters);
        System.out.println(String.format("%s is fighting %s: ", fighters.get(0).getName(), fighters.get(1).getName()));
        while (fighters.get(0).isAlive() && fighters.get(1).isAlive()){
            Thread.sleep(TIME_DELAY);
            fighters.get(0).actionBack(fighters.get(1));
            System.out.println(String.format("%s has hit %s !! ", fighters.get(0).getName(), fighters.get(1).getName()));
            if (fighters.get(1).isAlive()){
                Thread.sleep(TIME_DELAY);
                fighters.get(1).actionBack(fighters.get(0));
                System.out.println(String.format("%s Has hit back %s!! ", fighters.get(1).getName(), fighters.get(0).getName()));
            } else {
                return fighters.get(0).getName() + " Has won";
            }
        }
        return fighters.get(1).getName() + " Has won";

    }
}
