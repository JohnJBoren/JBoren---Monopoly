public class FreeParking extends Square {
    int typeOfCard;

    public FreeParking(int typeOfCard, String name) {
        super(name);
        this.typeOfCard = typeOfCard;
    }

    @Override
    public void doAction(Player player, Board board) {
        player.getMoney().addMoney(20.0);
        Util.print(player, player.getName() + " has landed on Free Parking and gets $20");
    }
}