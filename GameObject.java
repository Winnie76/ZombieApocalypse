public class GameObject {
    private boolean infected;
    private boolean moveCompleted;
    private int zombieNum;

    public GameObject(boolean isInfected, boolean moveCompleted, int zombieNum){
        infected = isInfected;
        moveCompleted = moveCompleted;
        zombieNum = zombieNum;
    }

    public void changeInfection(boolean isInfected){
        this.infected = isInfected;
//        System.out.println("zombie " + zombieNum + " infected creature at ");
    }
}
