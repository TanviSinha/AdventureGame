import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Room {
    private String description;
    private Map<String, String> exits = new HashMap<>();
    private String item;
    private List<NPC> npcs;
    private Enemy enemy;

    public Room(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getExits() {
        return exits;
    }

    public void addExit(String direction, String room) {
        exits.put(direction, room);
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setNpcs(List<NPC> npcs) {
        this.npcs = npcs;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public void interactWithNpc(int npcIndex) {
        if (npcs != null && npcIndex >= 0 && npcIndex < npcs.size()) {
            npcs.get(npcIndex).talk();
        } else {
            System.out.println("There is no NPC to interact with here.");
        }
    }

    public void pickupItem(Player player) {
        if (item != null) {
            System.out.println("You picked up a " + item);
            player.addItem(item);
            item = null;
        }
    }

    public boolean hasEnemy() {
        return enemy != null;
    }

    public void combat(Player player) {
        if (enemy != null) {
            System.out.println("You encounter a " + enemy.getName());
            System.out.println("Fight! (Type 'attack' or 'run')");
        } else {
            System.out.println("There is no enemy here.");
        }
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public List<NPC> getNpcs() {
        return npcs;
    }
}
