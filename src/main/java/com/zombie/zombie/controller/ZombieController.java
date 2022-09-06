package com.zombie.zombie.controller;
import com.zombie.zombie.dto.GameConfigDto;
import com.zombie.zombie.dto.GameResultDto;
import com.zombie.zombie.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
public class ZombieController {

    @PostMapping("/getValue")
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public GameResultDto getValue(@Valid @RequestBody GameConfigDto gameConfigDto) {
        GameService gameService = new GameService();
        return gameService.move(gameConfigDto);
    }

}
