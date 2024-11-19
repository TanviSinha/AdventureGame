import java.util.ArrayList;
import java.util.List;

public class Player {
    private int health;
    private List<String> inventory = new ArrayList<>();

    public Player(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void showInventory() {
        System.out.println("Inventory: " + inventory);
    }

    public void addItem(String item) {
        inventory.add(item);
    }
}
