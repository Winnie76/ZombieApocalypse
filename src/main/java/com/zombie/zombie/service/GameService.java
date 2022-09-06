package com.zombie.zombie.service;

import com.zombie.zombie.dto.GameConfigDto;
import com.zombie.zombie.dto.GameResultDto;
import com.zombie.zombie.dto.Mapper;
import com.zombie.zombie.model.GameCharacter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class GameService {
    public List<GameCharacter> unwalkedZombies = new ArrayList<>();
    public List<GameCharacter> allZombies = new ArrayList<>();
    public List<GameCharacter> originalCreatures = new ArrayList<>();
    public GameCharacter zombie;
    int gridSize;
    private static final Mapper mapper = new Mapper();

    Logger logger = LoggerFactory.getLogger(Logger.class);

    Map<Character, List<Integer>> directionMapping = new HashMap<>() {{
        put('R', Arrays.asList(1, 0));
        put('L', Arrays.asList(-1, 0));
        put('D', Arrays.asList(0, 1));
        put('U', Arrays.asList(0, -1));
    }};

    public GameResultDto move(GameConfigDto gameConfigDto) {
        gridSize = gameConfigDto.getGridSize();
        String commands = gameConfigDto.getCommands();
        GameCharacter zombie = mapper.toZombie(gameConfigDto.getZombie());
        unwalkedZombies.add(zombie);

        originalCreatures = gameConfigDto.getCreatures().stream().map(mapper::toCreature).collect(Collectors.toList());

        char[] directions = commands.toCharArray();

        int counter = 0;
        while (unwalkedZombies.size() > 0) {
            zombie = unwalkedZombies.remove(0);
            for (char ch : directions) {
                zombie.x = (directionMapping.get(ch).get(0) + gridSize + zombie.x) % gridSize;
                zombie.y = (directionMapping.get(ch).get(1) + gridSize + zombie.y) % gridSize;
                logger.info(String.format("zombie %d moved to (%d, %d).", counter, zombie.x, zombie.y));
                infectOthers(zombie, counter);
            }
            counter += 1;
        }

        return new GameResultDto(allZombies.stream().map(mapper::toZombieDto).toList(),
                originalCreatures.stream().map(mapper::toCreatureDto).toList());
    }


    private void infectOthers(GameCharacter zombie, int id) {
        List<GameCharacter> tempCreatures = originalCreatures.stream().toList();
        for (GameCharacter creature : tempCreatures) {
            if (creature.x == zombie.x && creature.y == zombie.y && !creature.isInfected()) {
                creature.setInfected(true);
                allZombies.add(creature);
                unwalkedZombies.add(creature);
                originalCreatures.remove(creature);
                logger.info(String.format("zombie %d infected creature at (%d, %d).", id, creature.x, creature.y));
            }
        }
    }
}

