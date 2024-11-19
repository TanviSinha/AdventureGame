import java.util.*;

public class Main {
    static Player player;
    static Map<String, Room> rooms = new HashMap<>();
    static String currentRoom = "Entrance";

    public static void main(String[] args) {
        initializeGame();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Adventure Game!");
        while (true) {
            if (player.getHealth() <= 0) {
                System.out.println("Game Over! You have no health left.");
                break;
            }
            if (currentRoom.equals("Treasure Room") && player.getInventory().contains("Treasure")) {
                System.out.println("You Win! You collected the treasure.");
                break;
            }

            Room room = rooms.get(currentRoom);
            System.out.println("\n" + room.getDescription());
            System.out.println("Available exits: " + room.getExits().keySet());
            System.out.print(">>");
            String command = scanner.nextLine().trim().toLowerCase();

            processCommand(command, room);
        }

        scanner.close();
    }

    static void initializeGame() {
        player = new Player(100);

        Room entrance = new Room("You are at the start of the game entering of a mysterious place.");
        Room forest = new Room("You are in a dense forest.You can talk to people here");
        Room dungeon = new Room("You are in a dark dungeon. You can get enemies here!");
        Room treasureRoom = new Room("You have reached the Treasure Room!");

        entrance.addExit("north", "Forest");
        forest.addExit("south", "Entrance");
        forest.addExit("east", "Dungeon");
        dungeon.addExit("west", "Forest");
        dungeon.addExit("north", "Treasure Room");
        treasureRoom.addExit("south", "Dungeon");

        forest.setItem("Potion");
        treasureRoom.setItem("Treasure");

        NPC wiseMan = new NPC("Wise Old Man", "Take this hint: The treasure is guarded by a fierce enemy.");
        NPC merchant = new NPC("Neville The Key Giver", "This opens the treasure box.");
        forest.setNpcs(Arrays.asList(wiseMan, merchant)); // Two NPCs in the Forest

        Enemy dungeonEnemy = new Enemy("Voldemort", 50);
        dungeon.setEnemy(dungeonEnemy);

        rooms.put("Entrance", entrance);
        rooms.put("Forest", forest);
        rooms.put("Dungeon", dungeon);
        rooms.put("Treasure Room", treasureRoom);
    }

    static void processCommand(String command, Room room) {
        if (command.startsWith("go ")) {
            move(command.substring(3), room);
        } else if (command.equals("check inventory")) {
            player.showInventory();
        } else if (command.equals("talk")) {
            // List NPCs in the room and ask the player to choose
            List<NPC> npcs = room.getNpcs();
            if (npcs != null && !npcs.isEmpty()) {
                System.out.println("Which NPC do you want to talk to?");
                for (int i = 0; i < npcs.size(); i++) {
                    System.out.println(i + ": " + npcs.get(i).getName());
                }
                Scanner scanner = new Scanner(System.in);
                int npcIndex = scanner.nextInt();
                room.interactWithNpc(npcIndex);
            } else {
                System.out.println("There are no NPCs to talk to.");
            }
        } else if (command.equals("attack")) {
            room.combat(player);
        } else if (command.equals("run")) {
            if (room.hasEnemy()) {
                System.out.println("You ran away!");
                currentRoom = "Forest"; // Move to a safe room
            } else {
                System.out.println("There is nothing to run from.");
            }
        } else {
            System.out.println("Invalid command.");
        }
    }

    static void move(String direction, Room room) {
        if (room.getExits().containsKey(direction)) {
            currentRoom = room.getExits().get(direction);
            Room nextRoom = rooms.get(currentRoom);
            nextRoom.pickupItem(player);
        } else {
            System.out.println("You can't go that way.");
        }
    }
}
