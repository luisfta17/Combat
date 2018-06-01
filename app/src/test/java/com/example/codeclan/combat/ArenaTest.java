package com.example.codeclan.combat;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArenaTest {
    Ninja ninja;
    Knight knight;
    Arena arena;

    @Before
    public void before(){
        ninja = new Ninja("Daniel");
        knight = new Knight("Luis");
        arena = new Arena();
        arena.addPlayer(ninja);
        arena.addPlayer(knight);
    }

    @Test
    public void hasPlayers(){
        assertEquals(2, arena.getFighters().size());
    }

    @Test
    public void canFightTillDead() throws InterruptedException{
        arena.fightTillDead();
        assertTrue((!arena.getFighters().get(0).isAlive() && arena.getFighters().get(1).isAlive())
                || (arena.getFighters().get(0).isAlive() && !arena.getFighters().get(1).isAlive()));
    }

}
