package com.zombie.zombie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResultDto {
    List<ZombieDto> zombies;
    List<CreatureDto> creatures;
}
