package com.zombie.zombie.controller;

import com.zombie.zombie.service.GenerateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class ZombieController {

    @PostMapping("/getValue")
    @ResponseStatus(HttpStatus.CREATED)
    @ExceptionHandler(Exception.class)
    public HashMap<Object, Object> getValue(@RequestBody String value) {
        return GenerateResponse.generateResponse(value);
    }

}