public abstract class Square {
    String name;
    int owner = -1;

    public Square() {
        name = "Call This Something";
    }

    public Square(String name) {
        this.name = name;
    }

    public Square(String name, int owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return this.name;
    }

    public int getOwner() {
        return this.owner;
    }

    public abstract void doAction(Player player, Board board);
}