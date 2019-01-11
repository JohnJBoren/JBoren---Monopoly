public class JailSquare extends Square {
    int typeOfSquare;

    public JailSquare(int typeOfSquare, String name) {
        super(name);
        this.typeOfSquare = typeOfSquare;
    }

    @Override
    public void doAction(Player player, Board board) {
        Util.print(player, player.getName() + ", you are either Just Visiting or are spending the night in Jail.");
    }
}