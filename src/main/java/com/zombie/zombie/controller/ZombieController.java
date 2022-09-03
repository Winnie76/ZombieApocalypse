package com.zombie.zombie.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zombie.zombie.model.GameCharacter;
import com.zombie.zombie.service.Move;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import static com.zombie.zombie.model.Board.gridSize;
import static com.zombie.zombie.service.Move.*;

@RestController
@RequiredArgsConstructor
public class ZombieController {

    @PostMapping("/getValue")
    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler(Exception.class)
    public HashMap<Object, Object> getValue(@RequestBody String value) {
        initValue(value);
        Move.move(zombie);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("grid-size", gridSize);
        map.put("zombies", newZombies);
        creatures.removeAll(Move.infectedCreatures);
        map.put("creatures", creatures);
        return map;
    }

    private void initValue(String value) {
        JSONObject jsonObject = JSON.parseObject(value);
        gridSize = Integer.parseInt(jsonObject.get("gridSize").toString());
        zombie = JSON.parseObject(jsonObject.get("zombie").toString(), GameCharacter.class);
        GameCharacter[] creatureArr = JSON.parseObject(jsonObject.get("creatures").toString(), GameCharacter[].class);
        creatures = Arrays.stream(creatureArr).collect(Collectors.toList());
        GameCharacter.action = jsonObject.get("commands").toString();
        newZombies = new ArrayList<>();
        infectedCreatures = new ArrayList<>();
    }

}