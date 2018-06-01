package com.example.codeclan.combat;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NinjaTest {
    Ninja ninja;

    @Before
    public void before(){
        ninja = new Ninja();
    }

    @Test
    public void hasName(){
        assertEquals("Player 1", this.ninja.getName());
    }

    @Test
    public void hasMaxHp(){
        assertEquals(200.0, this.ninja.getMaxHp(), 0.01);
    }

    @Test
    public void hasHp(){
        assertEquals(200, this.ninja.getHp(), 0.01);
    }

    @Test
    public void hasAttPower(){
        assertEquals(25, this.ninja.getAttPower(), 0.01);
    }

    @Test
    public void hasAdrenaline(){
        assertEquals(0, this.ninja.getAdrenaline(), 0.01);
    }

    @Test
    public void hasMaxAdrenaline(){
        assertEquals(100, this.ninja.getMaxAdrenaline(), 0.01);
    }



}
