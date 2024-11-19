public class NPC {
    private String name;
    private String hint;

    public NPC(String name, String hint) {
        this.name = name;
        this.hint = hint;
    }

    public String getName() {
        return name;
    }

    public void talk() {
        System.out.println("You talk to " + name + ".");
        System.out.println("\"" + hint + "\"");
    }
}
