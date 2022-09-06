package com.zombie.zombie.controller;

import com.zombie.zombie.dto.GameConfigDto;
import com.zombie.zombie.dto.GameResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ZombieController {

    @PostMapping("/getValue")
    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler(Exception.class)
    public GameResultDto getValue(@RequestBody GameConfigDto configDto) {
        return new GameResultDto();
    }

}