package com.zombie.zombie.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zombie.zombie.model.GameCharacter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import static com.zombie.zombie.model.Board.gridSize;
import static com.zombie.zombie.service.Move.*;

public class GenerateResponse {
    @Data
    @AllArgsConstructor
    private static class Pos {
        int x;
        int y;
    }

    public static HashMap<Object, Object> generateResponse(String value) {
        initValue(value);
        Move.move(zombie);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("zombies", newZombies.stream().map(z -> new Pos(z.x, z.y)).toList());
        creatures.removeAll(Move.infectedCreatures);
        map.put("creatures", creatures.stream().map(c -> new Pos(c.x, c.y)).toList());
        return map;
    }

    private static void initValue(String value) {
        JSONObject jsonObject = JSON.parseObject(value);
        gridSize = Integer.parseInt(jsonObject.get("gridSize").toString());
        zombie = JSON.parseObject(jsonObject.get("zombie").toString(), GameCharacter.class);
        zombie.setInfected(true);
        GameCharacter[] creatureArr = JSON.parseObject(jsonObject.get("creatures").toString(), GameCharacter[].class);
        creatures = Arrays.stream(creatureArr).collect(Collectors.toList());
        GameCharacter.action = jsonObject.get("commands").toString();
        newZombies = new ArrayList<>();
        infectedCreatures = new ArrayList<>();
    }
}
