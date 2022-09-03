package com.zombie.zombie.service;

import com.zombie.zombie.model.Board;
import com.zombie.zombie.model.GameCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveTest {

    GameCharacter zombie;
    List<GameCharacter> creatures;

    @BeforeEach
    void setUp() {
        zombie = new GameCharacter(0, 0, true);
    }

    @Test
    void noMove() {
        Board.gridSize = 1;
        GameCharacter.action = "";
        Move.move(zombie);
        assertEquals(0, zombie.x);
        assertEquals(0, zombie.y);
    }

    @Test
    void moveGridOne() {
        Board.gridSize = 1;
        GameCharacter.action = "U";
        Move.move(zombie);
        assertEquals(0, zombie.x);
        assertEquals(0, zombie.y);
    }

    @Test
    void moveGridMultiple() {
        Board.gridSize = 4;
        GameCharacter.action = "D";
        Move.move(zombie);
        assertEquals(0, zombie.x);
        assertEquals(1, zombie.y);
    }

    @Test
    void moveGridMultipleMoveMultiple() {
        Board.gridSize = 4;
        GameCharacter.action = "DDR";
        Move.move(zombie);
        assertEquals(1, zombie.x);
        assertEquals(2, zombie.y);
    }
}