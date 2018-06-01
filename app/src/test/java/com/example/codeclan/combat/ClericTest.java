package com.example.codeclan.combat;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClericTest {
    Cleric cleric;
    Ninja ninja2;

    @Before
    public void before(){
        cleric = new Cleric("Player 1");
        ninja2 = new Ninja("Android");
    }

    @Test
    public void hasName(){
        assertEquals("Player 1", this.cleric.getName());
    }

    @Test
    public void hasMaxHp(){
        assertEquals(200.0, this.cleric.getMaxHp(), 0.01);
    }

    @Test
    public void hasHp(){
        assertEquals(200, this.cleric.getHp(), 0.01);
    }

    @Test
    public void hasAttPower(){
        assertEquals(25, this.cleric.getAttPower(), 0.01);
    }

    @Test
    public void hasMP(){
        assertEquals(100, this.cleric.getMp(), 0.01);
    }

    @Test
    public void hasMaxMP(){
        assertEquals(100, this.cleric.getMaxMp(), 0.01);
    }

    @Test
    public void canChangeName(){
        this.cleric.setName("Player 2");
        assertEquals("Player 2", this.cleric.getName());
    }

    @Test
    public void canChangeMaxHp(){
        this.cleric.setMaxHp(20);
        assertEquals(20, this.cleric.getMaxHp(), 0.01);
    }

    @Test
    public void canChangeHp(){
        this.cleric.setHp(20);
        assertEquals(20, this.cleric.getHp(), 0.01);
    }

    @Test
    public void canChangeAttPower(){
        this.cleric.setAttPower(20);
        assertEquals(20, this.cleric.getAttPower(), 0.01);
    }

    @Test
    public void canChangeMaxMP(){
        this.cleric.setMaxMp(20);
        assertEquals(20, this.cleric.getMaxMp(), 0.01);
    }

    @Test
    public void canChangeMP(){
        this.cleric.setMp(20);
        assertEquals(20, this.cleric.getMp(), 0.01);
    }

    @Test
    public void canCheckIfIsAlive(){
        assertEquals(true, this.cleric.isAlive());
    }

    @Test
    public void canCheckIfIsAlive_DeadCase(){
        cleric.setHp(0);
        assertEquals(false, this.cleric.isAlive());
    }

    @Test
    public void canBasicAttack(){
        cleric.basicAttack(ninja2);
        assertTrue(ninja2.getHp() >= 130);
        assertTrue(ninja2.getHp() <= 175);
        assertEquals(100, cleric.getMp(), 0.01);
    }

    @Test
    public void canSpecialAttack(){
        cleric.specialAttack(cleric);
        assertEquals(200, cleric.getHp(), 0.01);
        assertEquals(50, cleric.getMp(), 0.01);

    }

    @Test
    public void hasAttackLogic(){
        ninja2.setAdrenaline(50);
        ninja2.actionBack(cleric);
        assertTrue(cleric.getHp() >= 100);
        assertTrue(cleric.getHp() <= 150);
        assertEquals(0, ninja2.getAdrenaline(), 0.01);
        cleric.setHp(130);
        cleric.actionBack(ninja2);
        assertEquals(192.5, cleric.getHp(), 0.01);
        assertEquals(50, cleric.getMp(), 0.01);
    }

}
