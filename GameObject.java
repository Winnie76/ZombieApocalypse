import java.util.List;

public class GameObject {
    private boolean infected;
    private boolean moveCompleted;
    private int zombieNum;
    private List<Integer> objectPosition;

    public GameObject(boolean isInfected, boolean moveCompleted, int zombieNum, List<Integer> position){
        infected = isInfected;
        moveCompleted = moveCompleted;
        zombieNum = zombieNum;
        objectPosition = position;
    }

    public boolean getInfection(){
        return this.infected;
    }

    public void setInfection(boolean isInfected){
        this.infected = isInfected;
    }

    public List<Integer> getObjectPosition(){
        return this.objectPosition;
    }

    public void setObjectPosition(List<Integer> newPosition){
        this.objectPosition = newPosition;
    }

    public boolean getMoveComplete(){
        return this.moveCompleted;
    }
    public void setMoveComplete(boolean completed){
        this.moveCompleted = completed;
    }

    public int getZombieNum(){
        return this.zombieNum;
    }

    public void setZombieNum(int number){
        this.zombieNum = number;
    }
}
