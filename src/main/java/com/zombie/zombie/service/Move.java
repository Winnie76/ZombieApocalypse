package com.zombie.zombie.service;

import com.zombie.zombie.model.GameCharacter;
import lombok.RequiredArgsConstructor;

import java.util.*;

import static com.zombie.zombie.model.Board.gridSize;

@RequiredArgsConstructor
public class Move {
    public static List<GameCharacter> newZombies = new ArrayList<>();
    public static List<GameCharacter> infectedCreatures = new ArrayList<>();
    public static List<GameCharacter> creatures = new ArrayList<>();
    public static GameCharacter zombie;

    public static void move(GameCharacter zombie) {
        String directions = GameCharacter.action.toUpperCase();
        char[] direction = directions.toCharArray();
        Map<Integer, Integer> overWallPos = new HashMap<>() {{
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
            } else if (zombie.y >= gridSize || zombie.y < 0) {
                zombie.y = overWallPos.get(zombie.y);
            }

            infectOthers(zombie);
        }
        newZombies.add(zombie);
    }


    private static void infectOthers(GameCharacter zombie) {
        for (GameCharacter creature : creatures) {
            if (creature.x == zombie.x && creature.y == zombie.y && !creature.isInfected()) {
                creature.setInfected(true);
                infectedCreatures.add(creature);
                move(new GameCharacter(zombie.x, zombie.y, true));
            }
        }
    }
}

