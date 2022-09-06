package com.zombie.zombie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZombieDto {
    @NotBlank
    int x;
    @NotBlank
    int y;
}
