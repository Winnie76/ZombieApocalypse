package com.zombie.zombie.model;

import lombok.Data;

import java.util.List;

@Data
public class Cell {
    private List<Integer> pos;
    // game objects on cell
    private List<GameObject> GOOnCell;

}
