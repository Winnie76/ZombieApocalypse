package com.zombie.zombie.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zombie.zombie.model.Board;
import com.zombie.zombie.model.Cell;
import com.zombie.zombie.model.GameObject;
import com.zombie.zombie.servise.Move;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.zombie.zombie.servise.Move.*;

@RestController
@RequiredArgsConstructor
public class ZombieController {

    @PostMapping("/getValue")
    public Map getValue(String value) {
        System.out.println(value);
        initValue(value);
        Move.moving(zombie);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("zombies", newZombies);
        creatures.removeAll(Move.infectiousCreatures);
        map.put("creatures", creatures);
        return map;
    }

    private void initValue(String value) {
        JSONObject jsonObject = JSON.parseObject(value);
        Board.gridSize = Integer.parseInt(jsonObject.get("gridSize").toString());
        zombie = JSON.parseObject(jsonObject.get("zombie").toString(), Cell.Zombie.class);
        Cell.Creature[] creatureArr = JSON.parseObject(jsonObject.get("creatures").toString(), Cell.Creature[].class);
        creatures = Arrays.stream(creatureArr).collect(Collectors.toList());
        GameObject.action = jsonObject.get("commands").toString();
        newZombies = new ArrayList<>();
        infectiousCreatures = new ArrayList<>();
    }

}