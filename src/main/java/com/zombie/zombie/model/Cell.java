package com.zombie.zombie.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cell {
    // [x, y]
    private List<Integer> pos;
    // game objects on cell
    private List<GameObject> GOOnCell;

    public Cell(List<Integer> pos) {
        this.pos = pos;
        this.GOOnCell = new ArrayList<>();
    }

    public Cell(List<Integer> pos, List<GameObject> GOOnCell) {
        this.pos = pos;
        this.GOOnCell = GOOnCell;
    }

}
