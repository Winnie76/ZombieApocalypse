package com.zombie.zombie.model;

import lombok.Data;

@Data
public class GameObject {
    private boolean infected;
	private Board board;
    private Cell curCell;

    public GameObject(Cell curCell, Boolean infected, Integer boardSize) {
        this.curCell = curCell;
        this.infected = infected;
        this.boardSize = boardSize;
    }

    public void move(String direction, int boardSize){
		// move
		List<Integer> pos = this.curCell.getPos();
		List<Integer> newPos = pos.clone();
		// Integer x = pos.get(0);
		// Integer y = pos.get(1);

		// Integer new_x = x % n;
		// Integer new_y = y % n;
		Method moveMethod
		= GameObject.class.getDeclaredMethod(String.format("moveOne%s", direction), Integer.class);

		if (direction == "R" or direction == "L") {
			newPos[0] = moveMethod(pos[0]);
		} else {
			newPos[1] = moveMethod(newPos[1]);
		}

		this.setCurCell(newPos);

		if (this.infected) {
			this.infectOthers();
		}
    }
    public String getName() {
        return this.infected ? "Zombie" : "Creature";
    }

	private void infectOthers() {
		List<GameObject> GOList = this.curCell.getGOOnCell();
	}

	public void setCurCell(List<Integer> newPos) {
		// board.getCell(newPos);
	}

	private static Integer moveOneR(Integer i){
		return (i + this.boardSize + 1) % n;
	}
	private static Integer moveOneL(Integer i){
		return (i + this.boardSize - 1) % n;
	}
	private static Integer moveOneU(Integer i){
		return (i + this.boardSize - 1) % n;
	}
	private static Integer moveOneD(Integer i){
		return (i + this.boardSize + 1) % n;
	}
}
