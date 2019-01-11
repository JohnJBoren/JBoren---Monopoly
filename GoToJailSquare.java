public class GoToJailSquare extends Square {
    int typeOfCard;

    public GoToJailSquare(int typeOfCard, String name) {
        super(name);
        this.typeOfCard = typeOfCard;
    }

    @Override
    public void doAction(Player player, Board board) {
        player.setInJail(true);
        Util.print(player, player.getName() + " the " + player.piece
                + " has been arrested and must go straight to Jail, do not pass Go, do not collect $200 and lose a turn");
        board.movePlayer(player, -board.getTotalSquare() / 2, false);

    }
}