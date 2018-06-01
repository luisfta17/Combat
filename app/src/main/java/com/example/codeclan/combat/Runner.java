package com.example.codeclan.combat;

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        Ninja ninja = new Ninja("Ninja");
        Knight knight = new Knight("Knight");
        Cleric cleric = new Cleric("Cleric");
        Arena arena = new Arena();
        arena.addPlayer(ninja);
        arena.addPlayer(cleric);
        System.out.println(arena.fightTillDead());
    }
}
