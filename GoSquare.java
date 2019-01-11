public class GoSquare extends Square {
    int typeOfCard;

    public GoSquare(int typeOfCard, String name) {
        super(name);
        this.typeOfCard = typeOfCard;
    }

    @Override
    public void doAction(Player player, Board board) {
        if (player.getTurn() > 0) {
            player.getMoney().addMoney(200.0);
        }

        Util.print(player, player.getName() + " is on Go... + $200");
    }
}