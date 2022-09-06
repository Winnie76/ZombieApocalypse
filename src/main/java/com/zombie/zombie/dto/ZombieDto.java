package com.zombie.zombie.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ZombieDto {
    @NotBlank
    int x;
    @NotBlank
    int y;
}
