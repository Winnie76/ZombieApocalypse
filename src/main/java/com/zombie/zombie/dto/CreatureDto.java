package com.zombie.zombie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatureDto {
    @NotBlank
    int x;
    @NotBlank
    int y;
}