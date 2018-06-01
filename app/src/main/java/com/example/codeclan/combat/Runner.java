package com.example.codeclan.combat;

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        Ninja ninja = new Ninja("Daniel");
        Knight knight = new Knight("Luis");
        Arena arena = new Arena();
        arena.addPlayer(ninja);
        arena.addPlayer(knight);
        System.out.println(arena.fightTillDead());
    }
}
