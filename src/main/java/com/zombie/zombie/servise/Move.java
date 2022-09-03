package com.zombie.zombie.servise;

import com.zombie.zombie.model.Cell;
import com.zombie.zombie.model.GameObject;
import lombok.RequiredArgsConstructor;

import java.util.*;

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
        Map<Integer, Integer> overWallPos = new HashMap<>(){{
            put(gridSize, 0);
            put(-1, gridSize - 1);
        }};
        Map<Character, List<Integer>> action = new HashMap<>() {{
            put('R', Arrays.asList(1, 0));
            put('L', Arrays.asList(-1, 0));
            put('D', Arrays.asList(0, 1));
            put('U', Arrays.asList(0, -1));
        }};

        for (char ch : direction) {
            zombie.x = action.get(ch).get(0) + zombie.x;
            zombie.y = action.get(ch).get(1) + zombie.y;

            if (zombie.x >= gridSize || zombie.x < 0) {
                zombie.x = overWallPos.get(zombie.x);
            }else if (zombie.y >= gridSize || zombie.y < 0) {
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
                moving(new Cell.Zombie(zombie.x, zombie.y));
            }
        }
    }


}

