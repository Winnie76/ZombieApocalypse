package com.zombie.zombie.service;

import com.zombie.zombie.dto.GameConfigDto;
import com.zombie.zombie.dto.GameResultDto;
import com.zombie.zombie.dto.Mapper;
import com.zombie.zombie.model.GameCharacter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class Move {
    public static List<GameCharacter> newZombies = new ArrayList<>();
    public static List<GameCharacter> infectedCreatures = new ArrayList<>();
    public static List<GameCharacter> creatures = new ArrayList<>();
    public static GameCharacter zombie;
    int gridSize;
    private static final Mapper mapper = new Mapper();

    Logger logger = LoggerFactory.getLogger(Logger.class);

    public GameResultDto move(GameConfigDto gameConfigDto) {

        logger.warn("LOGGING:" + gameConfigDto.toString());
        gridSize = gameConfigDto.getGridSize();
        String commands = gameConfigDto.getCommands();// GameCharacter.action.toUpperCase();
        GameCharacter zombie = mapper.toZombie(gameConfigDto.getZombie());
        char[] directions = commands.toCharArray();
        Map<Character, List<Integer>> action = new HashMap<>() {{
            put('R', Arrays.asList(1, 0));
            put('L', Arrays.asList(-1, 0));
            put('D', Arrays.asList(0, 1));
            put('U', Arrays.asList(0, -1));
        }};

        for (char ch : directions) {
            zombie.x = (action.get(ch).get(0) + gridSize + zombie.x) % gridSize;
            zombie.y = (action.get(ch).get(0) + gridSize + zombie.y) % gridSize;
            infectOthers(zombie);
        }
        newZombies.add(zombie);
        return new GameResultDto(newZombies.stream()
                .map(mapper::toZombieDto).toList(),
                creatures.stream().map(mapper::toCreatureDto).toList());
    }


    private void infectOthers(GameCharacter zombie) {
        for (GameCharacter creature : creatures) {
            if (creature.x == zombie.x && creature.y == zombie.y && !creature.isInfected()) {
                creature.setInfected(true);
                infectedCreatures.add(creature);
            }
        }
    }
}

