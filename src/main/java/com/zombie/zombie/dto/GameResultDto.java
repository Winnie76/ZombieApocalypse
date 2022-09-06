package com.zombie.zombie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GameResultDto {
    List<ZombieDto> zombies;
    List<CreatureDto> creatures;
}
