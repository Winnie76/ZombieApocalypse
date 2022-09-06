package com.zombie.zombie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CreatureDto {
    @NotBlank
    int x;
    @NotBlank
    int y;
}
