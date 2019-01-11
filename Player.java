public class Player {
    int turn = 0;
    int position = 0;
    int id;
    String name;
    String piece;
    boolean broke = false;
    boolean jail = false;
    Money money = new Money(1500.0);

    public Player(int id, String name, String piece) {
        this.id = id;
        this.name = name;
        this.piece = piece;
    }

    public int getTurn() {
        return this.turn;
    }

    public int tossDice(Die die1, Die die2) {
        int face1 = die1.rollDie();
        int face2 = die2.rollDie();
        int total = face1 + face2;
        Util.print(this, getName() + " tosses the dice... First Die is " + face1 + "... Second Die is " + face2);
        Util.print(this, getName() + " moves: " + total + " spaces ");

        return total;
    }

    public int getCurrentPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void nextTurn() {
        this.turn++;
    }

    public String getName() {
        return this.name;
    }

    public Money getMoney() {
        return this.money;
    }

    public int getID() {
        return this.id;
    }

    public void setIsBroke(boolean broke) {
        this.broke = broke;
    }

    public boolean isBroke() {
        return this.broke;
    }

    public void setInJail(boolean jail) {
        this.jail = jail;
    }

    public boolean isInJail() {
        return this.jail;
    }

    public String getPiece() {
        return this.piece;
    }
}