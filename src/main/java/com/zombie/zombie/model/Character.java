package com.zombie.zombie.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class Character {
    @NotBlank
    public static String action;
    @NotBlank
    public int x;
    @NotBlank
    public int y;
    @NotBlank
    private boolean isInfected = false;

}

