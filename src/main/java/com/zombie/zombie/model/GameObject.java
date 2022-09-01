package com.zombie.zombie.model;

import lombok.Data;

@Data
public class GameObject {
    private boolean infected;
    private Cell curCell;

    public GameObject(Cell curCell, Boolean infected) {
        this.curCell = curCell;
        this.infected = infected;
    }

    public void move(String direction, int boardSize){
    }
    public String getName() {
        return this.infected ? "Zombie" : "Creature";
    }
}
