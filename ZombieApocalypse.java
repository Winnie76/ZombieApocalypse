import java.util.*;

public class ZombieApocalypse {
    public static final boolean ISINFECTED = true;
    public static final boolean NOTINFECTED = false;
    public static final boolean MOVECOMPLETED = true;
    public static final boolean MOVENOTCOMPLETED = false;
    public static final int CREATURENUM = -1;
    private int zombieNumber;
    private String moveSequence;

    private Map<List<Integer>, List<GameObject>> world;
    private List<GameObject> zombiesWaiting;

    public ZombieApocalypse(int n, String sequence, Map<String, Integer> zombies, List<Map<String, Integer>> creatures) {

        zombiesWaiting = new ArrayList<GameObject>();
        zombieNumber = 0;
        moveSequence = sequence;
        world = generateWorld(n, zombies, creatures);
    }

    public Map<List<Integer>, List<GameObject>> generateWorld(int n, Map<String, Integer> zombies, List<Map<String, Integer>> creatures){

        Map<List<Integer>, List<GameObject>> worldGenerated = new HashMap<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                List<Integer> position = new ArrayList<Integer>(Arrays.asList(i, j));
                List<GameObject> emptyGameObjectList = new ArrayList<GameObject>();
                worldGenerated.put(position, emptyGameObjectList);
            }
        }
        List<Integer> zombiePos = Arrays.asList(zombies.get("x"), zombies.get("y"));
        List<GameObject> objectsSameCell = worldGenerated.get(zombiePos);
        GameObject zombieOne = new GameObject(ISINFECTED, MOVENOTCOMPLETED, this.zombieNumber, zombiePos);
        objectsSameCell.add(zombieOne);
        worldGenerated.put(zombiePos, objectsSameCell);

        this.zombiesWaiting.add(zombieOne);
        System.out.println("initial zombie waiting list");
        System.out.println(this.zombiesWaiting);

        for (Map<String, Integer> creature: creatures){
            List<Integer> creaturePos = Arrays.asList(creature.get("x"), creature.get("y"));
            List<GameObject> onSameCell = worldGenerated.get(creaturePos);
            GameObject creatureOne = new GameObject(NOTINFECTED, MOVENOTCOMPLETED, CREATURENUM, creaturePos);
            onSameCell.add(creatureOne);
            worldGenerated.put(creaturePos, onSameCell);
        }
        System.out.println("creatures");
        System.out.println(creatures);
        System.out.println("zombies");
        System.out.println(zombies);
        System.out.println("initial world generated");
        System.out.println(worldGenerated);


        return worldGenerated;
    }

    public static void main(String[] args) {
        Map<String, Integer> zombieList = new HashMap<String, Integer>();
        zombieList.put("x", 3);
        zombieList.put("y", 1);
        List<Map<String, Integer>> creatureList = new ArrayList<Map<String, Integer>>();
        Map<String, Integer> creatureOne = new HashMap<String, Integer>();
        creatureOne.put("x", 0);
        creatureOne.put("y", 1);
        Map<String, Integer> creatureTwo = new HashMap<String, Integer>();
        creatureTwo.put("x", 1);
        creatureTwo.put("y", 2);
        Map<String, Integer> creatureThree = new HashMap<String, Integer>();
        creatureThree.put("x", 1);
        creatureThree.put("y", 1);
        creatureList.add(creatureOne);
        creatureList.add(creatureTwo);
        creatureList.add(creatureThree);

        int boardSize = 4;
        ZombieApocalypse zombieApocalypse =
                new ZombieApocalypse(boardSize, "RDRU", zombieList, creatureList);
        System.out.print(zombieApocalypse.world);

        while (zombieApocalypse.zombiesWaiting.size() != 0){
            GameObject currentZombie = zombieApocalypse.zombiesWaiting.get(0);
            System.out.println("yes first zombie");
            for (int n = 0; n < zombieApocalypse.moveSequence.length(); n++){
                char nextMove = zombieApocalypse.moveSequence.charAt(n);
                zombieApocalypse.zombieMove(nextMove, currentZombie, boardSize);
                System.out.println("check move infection");
                System.out.println(zombieApocalypse.zombiesWaiting);

            }
            currentZombie.setMoveComplete(true);
            zombieApocalypse.zombiesWaiting.remove(0);
        }
        System.out.println("everything is done");
        System.out.println(zombieApocalypse.world);

        System.out.println("final zombie waiting list");
        System.out.println(zombieApocalypse.zombiesWaiting);




    }

    public void zombieMove(char nextMove, GameObject currZombie, int boardSize){
        List<Integer> newPos = null;
        if (nextMove == 'U'){
            System.out.println("U executed");
            if (currZombie.getObjectPosition().get(1) == 0){
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0), boardSize-1);

            }
            else{
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0), currZombie.getObjectPosition().get(1)-1);
            }
            System.out.println(newPos);
        }else if (nextMove == 'D'){
            System.out.println("D executed");

            if (currZombie.getObjectPosition().get(1) == boardSize-1){
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0), 0);
            }
            else{
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0), currZombie.getObjectPosition().get(1)+1);
            }
            System.out.println(newPos);

        }else if (nextMove == 'L'){
            System.out.println("L executed");

            if (currZombie.getObjectPosition().get(0) == 0){
                newPos = Arrays.asList(boardSize-1, currZombie.getObjectPosition().get(1));
            }
            else{
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0)-1, currZombie.getObjectPosition().get(1));
            }
            System.out.println(newPos);
        }else if (nextMove == 'R'){
            System.out.println("R executed");

            if (currZombie.getObjectPosition().get(0) == boardSize-1){
                newPos = Arrays.asList(0, currZombie.getObjectPosition().get(1));
            }
            else{
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0)+1, currZombie.getObjectPosition().get(1));
            }
            System.out.println(newPos);
        }else{
            System.out.println("not a valid character");
        }
        List<GameObject> listAfterRemoval = this.world.get(currZombie.getObjectPosition());
        listAfterRemoval.remove(currZombie);
        this.world.put(currZombie.getObjectPosition(), listAfterRemoval);
        List<GameObject> listAddNewObj = this.world.get(newPos);
        listAddNewObj.add(currZombie);
        this.world.put(newPos, listAddNewObj);
        currZombie.setObjectPosition(newPos);

        for (int k = 0; k < this.world.get(newPos).size(); k++){
            if (this.world.get(newPos).get(k).getInfection() == false){
                System.out.println("yes adding infected creature!!!!!!!!!!!!");

                this.world.get(newPos).get(k).setInfection(true);
                this.world.get(newPos).get(k).setZombieNum(this.zombieNumber+1);
                this.zombiesWaiting.add(this.world.get(newPos).get(k));
            }
        }
    }
}
