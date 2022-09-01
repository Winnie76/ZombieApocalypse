package com.zombie.zombie.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    private GameObject creature;
    private GameObject zombie;
    private Cell cell;
    private List<Integer> pos = new ArrayList<>(2);
    List<GameObject> GOList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        pos.add(0);
        pos.add(0);
        cell = new Cell(pos, GOList);
        creature = new GameObject(cell, false);
        zombie = new GameObject(cell, true);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void moveBorderSize1() {
        // always return to the origin (0, 0).
        this.creature.move("R", 1);
        assertEquals(new Cell(this.pos), this.creature.getCurCell());
        this.creature.move("D", 1);
        assertEquals(new Cell(this.pos), this.creature.getCurCell());
        this.creature.move("U", 1);
        assertEquals(new Cell(this.pos), this.creature.getCurCell());
        this.creature.move("L", 1);
        assertEquals(new Cell(this.pos), this.creature.getCurCell());
    }

    @Test
    void moveBorderSize2() {
        this.creature.move("R", 2);
        List<Integer> pos = new ArrayList<>(2);
        pos.add(1);
        pos.add(0);
        assertEquals(new Cell(pos), this.creature.getCurCell());
    }

    @Test
    void moveBorderSize10() {
        this.creature.move("R", 10);
        this.creature.move("R", 10);
        this.creature.move("R", 10);
        List<Integer> pos = new ArrayList<>(2);
        pos.add(3);
        pos.add(0);
        assertEquals(new Cell(pos), this.creature.getCurCell());
    }

    @Test
    void getName() {
        assertEquals("Creature", this.creature.getName());
        assertEquals("Zombie", this.zombie.getName());
    }

    @Test
    void isInfected() {
        assertFalse(this.creature.isInfected());
        assertTrue(this.zombie.isInfected());
    }

    @Test
    void getCurCell() {
        assertEquals(new Cell(this.pos), this.creature.getCurCell());
    }

    @Test
    void setInfected() {
        this.creature.setInfected(true);
        assertTrue(this.creature.isInfected());
    }

    @Test
    void setCurCell() {
    }
}