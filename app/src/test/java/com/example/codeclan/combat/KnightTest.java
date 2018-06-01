package com.example.codeclan.combat;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KnightTest {
    Knight knight;
    Ninja ninja2;

    @Before
    public void before(){
        knight = new Knight("Player 1");
        ninja2 = new Ninja("Android");
    }

    @Test
    public void hasName(){
        assertEquals("Player 1", this.knight.getName());
    }

    @Test
    public void hasMaxHp(){
        assertEquals(200.0, this.knight.getMaxHp(), 0.01);
    }

    @Test
    public void hasHp(){
        assertEquals(200, this.knight.getHp(), 0.01);
    }

    @Test
    public void hasAttPower(){
        assertEquals(25, this.knight.getAttPower(), 0.01);
    }

    @Test
    public void hasAdrenaline(){
        assertEquals(0, this.knight.getAdrenaline(), 0.01);
    }

    @Test
    public void hasMaxAdrenaline(){
        assertEquals(100, this.knight.getMaxAdrenaline(), 0.01);
    }

    @Test
    public void canChangeName(){
        this.knight.setName("Player 2");
        assertEquals("Player 2", this.knight.getName());
    }

    @Test
    public void canChangeMaxHp(){
        this.knight.setMaxHp(20);
        assertEquals(20, this.knight.getMaxHp(), 0.01);
    }

    @Test
    public void canChangeHp(){
        this.knight.setHp(20);
        assertEquals(20, this.knight.getHp(), 0.01);
    }

    @Test
    public void canChangeAttPower(){
        this.knight.setAttPower(20);
        assertEquals(20, this.knight.getAttPower(), 0.01);
    }

    @Test
    public void canChangeMaxAdrenaline(){
        this.knight.setMaxAdrenaline(20);
        assertEquals(20, this.knight.getMaxAdrenaline(), 0.01);
    }

    @Test
    public void canChangeAdrenaline(){
        this.knight.setAdrenaline(20);
        assertEquals(20, this.knight.getAdrenaline(), 0.01);
    }

    @Test
    public void canCheckIfIsAlive(){
        assertEquals(true, this.knight.isAlive());
    }

    @Test
    public void canCheckIfIsAlive_DeadCase(){
        knight.setHp(0);
        assertEquals(false, this.knight.isAlive());
    }

    @Test
    public void canBasicAttack(){
        knight.basicAttack(ninja2);
        assertTrue(ninja2.getHp() >= 130);
        assertTrue(ninja2.getHp() <= 175);
        assertTrue(knight.getAdrenaline() > 25);
        assertTrue(knight.getAdrenaline() < 70);
    }

    @Test
    public void canSpecialAttack(){
        knight.specialAttack(ninja2);
        assertEquals(200, ninja2.getHp(), 0.01);
        knight.setAdrenaline(50);
        knight.specialAttack(ninja2);
        assertTrue(ninja2.getHp() >= 100);
        assertTrue(ninja2.getHp() <= 150);
        assertEquals(0, knight.getAdrenaline(), 0.01);
    }

    @Test
    public void hasAttackLogic(){
        ninja2.actionBack(knight);
        assertTrue(knight.getHp() >= 130);
        assertTrue(knight.getHp() <= 175);
        assertTrue(ninja2.getAdrenaline() > 25);
        assertTrue(ninja2.getAdrenaline() < 70);
        knight.setAdrenaline(50);
        knight.actionBack(ninja2);
        assertTrue(ninja2.getHp() >= 100);
        assertTrue(ninja2.getHp() <= 150);
        assertEquals(0, knight.getAdrenaline(), 0.01);
    }

}
