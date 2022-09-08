package com.zombie.zombie.serviceTest;


import com.zombie.zombie.dto.CreatureDto;
import com.zombie.zombie.dto.GameConfigDto;
import com.zombie.zombie.dto.GameResultDto;
import com.zombie.zombie.dto.ZombieDto;
import com.zombie.zombie.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameServiceTest {

    GameConfigDto gameConfigDto=new GameConfigDto();

    ZombieDto zombieDto = new ZombieDto();
    GameService gameService=new GameService();
    GameResultDto gameResult=new GameResultDto();

    @BeforeEach
    public  void setUp() {
        ZombieDto zombieDto = new ZombieDto(0, 0);
        gameConfigDto.setZombie(zombieDto);

    }

    @Test
    void noMove() {
        gameConfigDto.setGridSize(1);
        gameConfigDto.setCommands("");
        ArrayList<CreatureDto> creatures = new ArrayList<>();
        creatures.add(new CreatureDto(0, 0));
        gameConfigDto.setCreatures(creatures);
        gameResult=gameService.move(gameConfigDto);
        assertEquals("[ZombieDto(x=0, y=0)]", gameResult.getZombies().toString());

    }

    @Test
    void moveGridOne() {
        gameConfigDto.setGridSize(1);
        gameConfigDto.setCommands("U");
        ArrayList<CreatureDto> creatures = new ArrayList<>();
        creatures.add(new CreatureDto(0, 0));
        gameConfigDto.setCreatures(creatures);
        gameResult=gameService.move(gameConfigDto);
        assertEquals("[ZombieDto(x=0, y=0), ZombieDto(x=0, y=0)]", gameResult.getZombies().toString());
    }

    @Test
    void moveGridMultiple() {
        gameConfigDto.setGridSize(4);
        gameConfigDto.setCommands("D");
        ArrayList<CreatureDto> creatures = new ArrayList<>();
        creatures.add(new CreatureDto(0, 0));
        gameConfigDto.setCreatures(creatures);
        gameResult=gameService.move(gameConfigDto);
        assertEquals("[ZombieDto(x=0, y=1)]", gameResult.getZombies().toString());
    }

    @Test
    void moveGridMultipleMoveMultiple() {
        gameConfigDto.setGridSize(4);
        gameConfigDto.setCommands("DDR");
        ArrayList<CreatureDto> creatures = new ArrayList<>();
        creatures.add(new CreatureDto(0, 0));
        gameConfigDto.setCreatures(creatures);
        gameResult=gameService.move(gameConfigDto);
        assertEquals("[ZombieDto(x=1, y=2)]", gameResult.getZombies().toString());
    }
    //
    @Test
    void moveGridMultipleMoveMultipleOverBorderRight() {
        gameConfigDto.setGridSize(3);
        gameConfigDto.setCommands("RRRR");
        ArrayList<CreatureDto> creatures = new ArrayList<>();
        creatures.add(new CreatureDto(0, 0));
        gameConfigDto.setCreatures(creatures);
        gameResult=gameService.move(gameConfigDto);
        assertEquals("[ZombieDto(x=1, y=0), ZombieDto(x=1, y=0)]", gameResult.getZombies().toString());
    }
    //
    @Test
    void moveGridMultipleMoveMultipleOverBorderDown() {
        gameConfigDto.setGridSize(3);
        gameConfigDto.setCommands("DDDD");
        ArrayList<CreatureDto> creatures = new ArrayList<>();
        creatures.add(new CreatureDto(0, 0));
        gameConfigDto.setCreatures(creatures);
        gameResult=gameService.move(gameConfigDto);
        assertEquals("[ZombieDto(x=0, y=1), ZombieDto(x=0, y=1)]", gameResult.getZombies().toString());
    }

    @Test
    void moveGridMultipleMoveMultipleOverBorderLeft() {
        gameConfigDto.setGridSize(3);
        gameConfigDto.setCommands("LLLL");
        ArrayList<CreatureDto> creatures = new ArrayList<>();
        creatures.add(new CreatureDto(0, 0));
        gameConfigDto.setCreatures(creatures);
        gameResult=gameService.move(gameConfigDto);
        assertEquals("[ZombieDto(x=2, y=0), ZombieDto(x=2, y=0)]", gameResult.getZombies().toString());
    }

    @Test
    void moveGridMultipleMoveMultipleOverBorderUp() {
        gameConfigDto.setGridSize(3);
        gameConfigDto.setCommands("DDDD");
        ArrayList<CreatureDto> creatures = new ArrayList<>();
        creatures.add(new CreatureDto(0, 0));
        gameConfigDto.setCreatures(creatures);
        gameResult=gameService.move(gameConfigDto);
        assertEquals("[ZombieDto(x=0, y=1), ZombieDto(x=0, y=1)]", gameResult.getZombies().toString());
    }

    @Test
    void multipleCreaturesInCellOne() {
        gameConfigDto.setGridSize(3);
        gameConfigDto.setCommands("DD");
        ArrayList<CreatureDto> creatures = new ArrayList<>();
        creatures.add(new CreatureDto(0, 1));
        creatures.add(new CreatureDto(1, 1));
        gameConfigDto.setCreatures(creatures);
        gameResult=gameService.move(gameConfigDto);
        assertEquals("[ZombieDto(x=0, y=0), ZombieDto(x=0, y=2)]", gameResult.getZombies().toString());
        assertEquals("[CreatureDto(x=1, y=1)]", gameResult.getCreatures().toString());

    }

}

