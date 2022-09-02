package com.zombie.zombie.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class GameObject {
    @NotBlank
    public static String action;
}

