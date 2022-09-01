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
        world = generateWorld(n, zombies, creatures);
        zombiesWaiting = new ArrayList<GameObject>();
        zombieNumber = 0;
        moveSequence = sequence;
    }

    public Map<List<Integer>, List<GameObject>> generateWorld(int n, Map<String, Integer> zombies, List<Map<String, Integer>> creatures){

        Map<List<Integer>, List<GameObject>> worldGenerated = new HashMap<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                ArrayList<Integer> position = new ArrayList<Integer>(Arrays.asList(i, j));
                ArrayList<GameObject> emptyGameObjectList = new ArrayList<GameObject>();
                worldGenerated.put(position, emptyGameObjectList);
            }
        }
        List<GameObject> objectsSameCell = worldGenerated.get(Arrays.asList(zombies.get("x"), zombies.get("y")));
        GameObject zombieOne = new GameObject(ISINFECTED, MOVENOTCOMPLETED, this.zombieNumber);
        objectsSameCell.add(zombieOne);
        worldGenerated.put(Arrays.asList(zombies.get("x"), zombies.get("y")), objectsSameCell);

        for (Map<String, Integer> creature: creatures){
            List<GameObject> onSameCell = worldGenerated.get(Arrays.asList(creature.get("x"), creature.get("y")));
            GameObject creatureOne = new GameObject(NOTINFECTED, MOVENOTCOMPLETED, CREATURENUM);
            onSameCell.add(creatureOne);
            worldGenerated.put(Arrays.asList(creature.get("x"), creature.get("y")), onSameCell);
        }
        System.out.println("creatures");
        System.out.println(creatures);
        System.out.println("zombies");
        System.out.println(zombies);



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


        ZombieApocalypse zombieApocalypse =
                new ZombieApocalypse(4, "UDLR", zombieList, creatureList);
        System.out.print(zombieApocalypse.world);

        zombieApocalypse.zombiesWaiting.add(zombieList);
        for (int n = 0; n < zombieApocalypse.moveSequence.length(); n++){
            char nextMove = zombieApocalypse.moveSequence.charAt(n);
            zombieApocalypse.zombieMove(nextMove);
        }
    }

    public void zombieMove(char nextMove){

    }
}
