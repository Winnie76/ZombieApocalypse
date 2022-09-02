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

        // initiate world to {[0, 0]: [ ], [0, 1]: [ ], [0, 2]: [ ],...}
        Map<List<Integer>, List<GameObject>> worldGenerated = new HashMap<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                List<Integer> position = new ArrayList<Integer>(Arrays.asList(i, j));
                List<GameObject> emptyGameObjectList = new ArrayList<GameObject>();
                worldGenerated.put(position, emptyGameObjectList);
            }
        }

        // add zombie to the right cell
        List<Integer> zombiePos = Arrays.asList(zombies.get("x"), zombies.get("y"));
        List<GameObject> objectsSameCell = worldGenerated.get(zombiePos);
        GameObject zombieOne = new GameObject(ISINFECTED, MOVENOTCOMPLETED, this.zombieNumber, zombiePos);
        this.zombieNumber = this.zombieNumber + 1;
        objectsSameCell.add(zombieOne);
        worldGenerated.put(zombiePos, objectsSameCell);

        // add zombie to the zombie movement waiting list
        this.zombiesWaiting.add(zombieOne);

        // add creatures to the right cell
        for (Map<String, Integer> creature: creatures){
            List<Integer> creaturePos = Arrays.asList(creature.get("x"), creature.get("y"));
            List<GameObject> onSameCell = worldGenerated.get(creaturePos);
            GameObject creatureOne = new GameObject(NOTINFECTED, MOVENOTCOMPLETED, CREATURENUM, creaturePos);
            onSameCell.add(creatureOne);
            worldGenerated.put(creaturePos, onSameCell);
        }

        System.out.println("initial world generated");
        System.out.println(worldGenerated);

        return worldGenerated;
    }

    public static void main(String[] args) {

        // create test data
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
        String sequence = "RDRU";

        // create object zombieApocalypse
        ZombieApocalypse zombieApocalypse =
                new ZombieApocalypse(boardSize, sequence, zombieList, creatureList);

        // loop through all zombies waiting for movement, execute movement one by one
        while (zombieApocalypse.zombiesWaiting.size() != 0){
            GameObject currentZombie = zombieApocalypse.zombiesWaiting.get(0);
            for (int n = 0; n < zombieApocalypse.moveSequence.length(); n++){
                char nextMove = zombieApocalypse.moveSequence.charAt(n);
                zombieApocalypse.zombieMove(nextMove, currentZombie, boardSize);

            }
            currentZombie.setMoveComplete(MOVECOMPLETED);
            zombieApocalypse.zombiesWaiting.remove(0);
        }
        System.out.println("final positions of all zombies and creatures:");
        for (List<GameObject> gameObjectsEachCell : zombieApocalypse.world.values()) {
            if (gameObjectsEachCell.size() > 0){
                for (GameObject oneGameObj: gameObjectsEachCell){
                    System.out.println("print each game object in the final world");
                    System.out.println(oneGameObj.getObjectPosition());
                    System.out.println(oneGameObj.getZombieNum());
                    System.out.println("is this game object infected? " + oneGameObj.getInfection());


                }
            }
        }
        System.out.println("final world generated");
        System.out.println(zombieApocalypse.world);





    }

    public void zombieMove(char nextMove, GameObject currZombie, int boardSize){
        // get new position value after movement
        List<Integer> newPos = null;
        if (nextMove == 'U'){
            if (currZombie.getObjectPosition().get(1) == 0){
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0), boardSize-1);
            }
            else{
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0), currZombie.getObjectPosition().get(1)-1);
            }
            System.out.println("zombie " + currZombie.getZombieNum() + " moved to (" + newPos.get(0) + ", " + newPos.get(1) + ").");
        }else if (nextMove == 'D'){
            if (currZombie.getObjectPosition().get(1) == boardSize-1){
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0), 0);
            }
            else{
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0), currZombie.getObjectPosition().get(1)+1);
            }
            System.out.println("zombie " + currZombie.getZombieNum() + " moved to (" + newPos.get(0) + ", " + newPos.get(1) + ").");
        }else if (nextMove == 'L'){
            if (currZombie.getObjectPosition().get(0) == 0){
                newPos = Arrays.asList(boardSize-1, currZombie.getObjectPosition().get(1));
            }
            else{
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0)-1, currZombie.getObjectPosition().get(1));
            }
            System.out.println("zombie " + currZombie.getZombieNum() + " moved to (" + newPos.get(0) + ", " + newPos.get(1) + ").");
        }else if (nextMove == 'R'){
            if (currZombie.getObjectPosition().get(0) == boardSize-1){
                newPos = Arrays.asList(0, currZombie.getObjectPosition().get(1));
            }
            else{
                newPos = Arrays.asList(currZombie.getObjectPosition().get(0)+1, currZombie.getObjectPosition().get(1));
            }
            System.out.println("zombie " + currZombie.getZombieNum() + " moved to (" + newPos.get(0) + ", " + newPos.get(1) + ").");
        }else{
            System.out.println("not a valid character");
        }

        // remove GameObject zombie from previous cell
        List<GameObject> listAfterRemoval = this.world.get(currZombie.getObjectPosition());
        listAfterRemoval.remove(currZombie);
        this.world.put(currZombie.getObjectPosition(), listAfterRemoval);

        // add GameObject zombie to new cell after movement
        List<GameObject> listAddNewObj = this.world.get(newPos);
        listAddNewObj.add(currZombie);
        this.world.put(newPos, listAddNewObj);

        //update GameObject zombie 's position
        currZombie.setObjectPosition(newPos);

        // set creatures on the same cell to be infected and add to zombie movement waiting list
        for (int k = 0; k < this.world.get(newPos).size(); k++){
            if (this.world.get(newPos).get(k).getInfection() == NOTINFECTED){
                System.out.println("zombie " + currZombie.getZombieNum() + " infected creature at (" + newPos.get(0) + ", " + newPos.get(1) + ")");
                this.world.get(newPos).get(k).setInfection(ISINFECTED);
                this.world.get(newPos).get(k).setZombieNum(this.zombieNumber);
                this.zombieNumber = this.zombieNumber + 1;
                this.zombiesWaiting.add(this.world.get(newPos).get(k));
            }
        }
    }
}
