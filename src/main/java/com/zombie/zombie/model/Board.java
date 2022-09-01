package com.zombie.zombie.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Board {
    @NotBlank
    public static int gridSize;
}
