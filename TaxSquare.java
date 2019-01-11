public class TaxSquare extends Square {
    String name;
    Double tax;
    int typeOfCard;

    public TaxSquare() {
        name = "Call This Something";
    }

    public TaxSquare(int typeOfCard, String name, Double tax) {
        super(name);
        this.typeOfCard = typeOfCard;
        this.tax = tax;
    }

    public String getName() {
        return name;
    }

    public Double getTaxPrice() {
        return tax;
    }

    public void doAction(Player player, Board board) {
        player.getMoney().substractMoney(this.getTaxPrice());
        Util.print(player,
                player.getName() + " has landed on " + this.getName() + " and must pay: " + this.getTaxPrice());

    }
}