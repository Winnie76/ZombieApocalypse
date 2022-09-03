package com.zombie.zombie.service;

import com.zombie.zombie.model.Board;
import com.zombie.zombie.model.GameCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void moveGridMultipleMoveMultipleOverBorderRight() {
        Board.gridSize = 3;
        GameCharacter.action = "RRRR";
        Move.move(zombie);
        assertEquals(1, zombie.x);
        assertEquals(0, zombie.y);
    }

    @Test
    void moveGridMultipleMoveMultipleOverBorderDown() {
        Board.gridSize = 3;
        GameCharacter.action = "DDDD";
        Move.move(zombie);
        assertEquals(0, zombie.x);
        assertEquals(1, zombie.y);
    }

    @Test
    void moveGridMultipleMoveMultipleOverBorderLeft() {
        Board.gridSize = 3;
        GameCharacter.action = "LLLL";
        Move.move(zombie);
        assertEquals(2, zombie.x);
        assertEquals(0, zombie.y);
    }

    @Test
    void moveGridMultipleMoveMultipleOverBorderUp() {
        Board.gridSize = 3;
        GameCharacter.action = "DDDD";
        Move.move(zombie);
        assertEquals(0, zombie.x);
        assertEquals(2, zombie.y);
    }

    @Test
    void multipleCreaturesInCellOne() {
        Board.gridSize = 3;
        GameCharacter.action = "DD";
        GameCharacter creature1 = new GameCharacter(1, 1, false);
        GameCharacter creature2 = new GameCharacter(1, 1, false);
        Move.move(zombie);
        assertTrue(creature1.isInfected());
    }

    @Test
    void multipleCreaturesInCellTwo() {
        // FIXME: Still failing.
        // Cell needs to know about its GameCharacters.
        Board.gridSize = 3;
        GameCharacter.action = "DD";
        GameCharacter creature1 = new GameCharacter(1, 1, false);
        GameCharacter creature2 = new GameCharacter(1, 1, false);
        Move.move(zombie);
        assertTrue(creature2.isInfected());
    }
}