public class BuyableSquare extends Square {
    int owner = -1;
    Double price;

    public BuyableSquare() {
        super("Mr. Peanut");
        this.price = 100.0;
    }

    public BuyableSquare(String name, Double initPrice) {
        super(name);
        this.price = initPrice;
    }

    public BuyableSquare(String name, int ownerID, Double initPrice) {
        super(name);
        this.owner = ownerID;
        this.price = initPrice;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public Double getPrice() {
        return price;
    }

    public int getOwner() {
        return owner;
    }

    @Override
    public void doAction(Player player, Board board) {

    }
}