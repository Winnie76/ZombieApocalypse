package com.zombie.zombie.model;

import lombok.Data;

@Data
public class Cell {

    public static class Zombie {
        private int x;
        private int y;

        public Zombie() {
        }

        public Zombie(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public class Creature {
        private int x;
        private int y;
        private boolean isInfected = false;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public boolean isInfected() {
            return isInfected;
        }

        public void setInfected(boolean infected) {
            isInfected = infected;
        }

    }


}
