package com.zombie.zombie.controller;

import com.zombie.zombie.dto.GameConfigDto;
import com.zombie.zombie.dto.GameResultDto;
import com.zombie.zombie.service.Move;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ZombieController {

    private Move moveService;

    @PostMapping("/getValue")
    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler(Exception.class)
    public GameResultDto getValue(@RequestBody GameConfigDto configDto) {
        return moveService.move(configDto);
    }

}