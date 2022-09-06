package com.zombie.zombie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GameResultDto {
    List<ZombieDto> zombies;
    List<CreatureDto> creatures;
}
