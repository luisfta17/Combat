package com.example.codeclan.combat;

import java.util.ArrayList;
import java.util.Collections;

public class Arena {
    private ArrayList<Character> fighters;

    public Arena(){
        this.fighters = new ArrayList<>();
    }

    public ArrayList<Character> getFighters() {
        return this.fighters;
    }

    public String fightTillDead(){
        Collections.shuffle(fighters);
        while (fighters.get(0).isAlive() && fighters.get(1).isAlive()){
            fighters.get(0).actionBack(fighters.get(1));
            if (fighters.get(1).isAlive()){
                fighters.get(1).actionBack(fighters.get(0));
            } else {
                return fighters.get(0).getName() + "Has won";
            }
        }
        return fighters.get(1).getName() + "Has won";

    }
}
