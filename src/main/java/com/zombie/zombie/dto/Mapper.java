package com.zombie.zombie.dto;

import com.zombie.zombie.model.GameCharacter;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public CreatureDto toCreatureDto(GameCharacter gameCharacter) {
        int x = gameCharacter.x;
        int y = gameCharacter.y;
        return new CreatureDto(x, y);
    }

    public ZombieDto toZombieDto(GameCharacter zombie) {
        int x = zombie.getX();
        int y = zombie.getY();
        return new ZombieDto(x, y);
    }

    public GameCharacter toCreature(CreatureDto creatureDto) {
        return new GameCharacter(creatureDto.getX(), creatureDto.getY(), false);
    }

    public GameCharacter toZombie(ZombieDto zombieDto) {
        return new GameCharacter(zombieDto.getX(), zombieDto.getY(), true);
    }
}
