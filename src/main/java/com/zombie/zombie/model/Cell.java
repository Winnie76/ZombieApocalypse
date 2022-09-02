package com.zombie.zombie.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Cell {

    public static class Zombie {
        @NotBlank
        public int x;
        @NotBlank
        public int y;

        public Zombie(int x, int y) {

        }
    }

    public static class Creature {
        @NotBlank
        public int x;
        @NotBlank
        public int y;
        private boolean isInfected = false;

        public boolean isInfected() {

            return isInfected;
        }

        public void setInfected(boolean infected) {

            isInfected = infected;
        }
    }
}
