package com.zombie.zombie.model;

import java.util.ArrayList;
import java.util.List;

import static com.zombie.zombie.model.Board.gridSize;

public class GameObject {
    public static List<Cell.Zombie> newZombies = new ArrayList<>();
    public static List<Cell.Creature> infectiousCreatures = new ArrayList<>();
    public static List<Cell.Creature> creatures;
    public static Cell.Zombie zombie;
    private static String direction;

    public static void moving(Cell.Zombie zombie) {
        String directions = direction.toUpperCase();
        char[] direction = directions.toCharArray();
        for (char ch : direction) {
            if (ch == 'R') {
                zombie.setX((zombie.getX() - 1));
                overWall(zombie);
                isHasCreatures(zombie);
            }
            if (ch == 'L') {
                zombie.setX((zombie.getX() + 1));
                overWall(zombie);
                isHasCreatures(zombie);
            }
            if (ch == 'D') {
                zombie.setY((zombie.getY() - 1));
                overWall(zombie);
                isHasCreatures(zombie);
            }
            if (ch == 'U') {
                zombie.setY((zombie.getY() + 1));
                overWall(zombie);
                isHasCreatures(zombie);
            }
        }
        newZombies.add(zombie);

    }

    private static void overWall(Cell.Zombie zombie) {
        if (zombie.getX() >= gridSize) {
            zombie.setX(0);
        }
        if (zombie.getX() < 0) {
            zombie.setX((zombie.getX() + gridSize));
        }
        if (zombie.getY() >= gridSize) {
            zombie.setY(0);
        }
        if (zombie.getY() < 0) {
            zombie.setY((zombie.getY() + gridSize));
        }


    }

    private static void isHasCreatures(Cell.Zombie zombie) {
        for (Cell.Creature creature : creatures) {
            if (creature.getX() == zombie.getX() && creature.getY() == zombie.getY() && !creature.isInfected()) {
                creature.setInfected(true);
                infectiousCreatures.add(creature);
                moving(new Cell.Zombie(zombie.getX(), zombie.getY()));
            }
        }
    }


}


