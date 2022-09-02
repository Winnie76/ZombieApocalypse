package com.zombie.zombie.servise;

import com.zombie.zombie.model.Cell;
import com.zombie.zombie.model.GameObject;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.zombie.zombie.model.Board.gridSize;

@RequiredArgsConstructor
public class Move {
    public static List<Cell.Zombie> newZombies = new ArrayList<>();
    public static List<Cell.Creature> infectiousCreatures = new ArrayList<>();
    public static List<Cell.Creature> creatures;
    public static Cell.Zombie zombie;

    public static void moving(Cell.Zombie zombie) {
        String directions = GameObject.action.toUpperCase();
        char[] direction = directions.toCharArray();
        for (char ch : direction) {
            if (ch == 'R') {
                zombie.x = (zombie.x - 1);
                overWall(zombie);
                isHasCreatures(zombie);
            }
            if (ch == 'L') {
                zombie.x = (zombie.x + 1);
                overWall(zombie);
                isHasCreatures(zombie);
            }
            if (ch == 'D') {
                zombie.y = (zombie.y - 1);
                overWall(zombie);
                isHasCreatures(zombie);
            }
            if (ch == 'U') {
                zombie.y = (zombie.y + 1);
                overWall(zombie);
                isHasCreatures(zombie);
            }
        }
        newZombies.add(zombie);

    }

    private static void overWall(Cell.Zombie zombie) {
        if (zombie.x >= gridSize) {
            zombie.x = 0;
        }
        if (zombie.x < 0) {
            zombie.x = (zombie.x + gridSize);
        }
        if (zombie.y >= gridSize) {
            zombie.y = 0;
        }
        if (zombie.y < 0) {
            zombie.y = (zombie.y + gridSize);
        }


    }

    private static void isHasCreatures(Cell.Zombie zombie) {
        for (Cell.Creature creature : creatures) {
            if (creature.x == zombie.x && creature.y == zombie.y && !creature.isInfected()) {
                creature.setInfected(true);
                infectiousCreatures.add(creature);
                moving(new Cell.Zombie(zombie.x, zombie.y));
            }
        }
    }


}

