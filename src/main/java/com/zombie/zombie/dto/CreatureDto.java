package com.zombie.zombie.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreatureDto {
    @NotBlank
    int x;
    @NotBlank
    int y;
}
