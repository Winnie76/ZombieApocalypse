package com.zombie.zombie.service;

import com.zombie.zombie.model.Cell;
import com.zombie.zombie.model.Character;
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

    public static void move(Cell.Zombie zombie) {
        String directions = Character.action.toUpperCase();
        char[] direction = directions.toCharArray();
        Map<Integer, Integer> overWallPos = new HashMap<>() {{
            put(gridSize, 0);
            put(-1, gridSize - 1);
        }};
        for (char ch : direction) {
            if (ch == 'R') {
                zombie.x = (zombie.x - 1);
            }
            if (ch == 'L') {
                zombie.x = (zombie.x + 1);
            }
            if (ch == 'D') {
                zombie.y = (zombie.y - 1);
            }
            if (ch == 'U') {
                zombie.y = (zombie.y + 1);
            }

            if (zombie.x >= gridSize || zombie.x < 0) {
                zombie.x = overWallPos.get(zombie.x);
            } else if (zombie.y >= gridSize || zombie.y < 0) {
                zombie.y = overWallPos.get(zombie.y);
            }

            isHasCreatures(zombie);
        }
        newZombies.add(zombie);

    }


    private static void isHasCreatures(Cell.Zombie zombie) {
        for (Cell.Creature creature : creatures) {
            if (creature.x == zombie.x && creature.y == zombie.y && !creature.isInfected()) {
                creature.setInfected(true);
                infectiousCreatures.add(creature);
                move(new Cell.Zombie(zombie.x, zombie.y));
            }
        }
    }


}

