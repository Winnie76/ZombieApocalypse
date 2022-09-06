package com.zombie.zombie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class GameConfigDto {
    @Min(1)
    int gridSize;

    @NotNull
    ZombieDto zombie;
    @NotNull
    List<CreatureDto> creatureDtoList;

    @NotBlank
    String commands;
}
