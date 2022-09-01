package com.zombie.zombie.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return pos.equals(cell.pos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos);
    }
}
