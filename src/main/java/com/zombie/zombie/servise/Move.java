package com.zombie.zombie.servise;

import com.zombie.zombie.model.Cell;
import com.zombie.zombie.model.GameObject;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Integer, Integer> overWallPos = new HashMap<>(){{
            put(gridSize, 0);
            put(-1, gridSize - 1);
        }};
        zombie.x = overWallPos.get(zombie.x);
        zombie.y = overWallPos.get(zombie.y);

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

