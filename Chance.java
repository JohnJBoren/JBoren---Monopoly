import java.util.Random;
import java.util.Scanner;

public class Chance extends Square {
    int typeOfCard;

    public Chance(int typeOfCard, String name) {
        super(name);
        this.typeOfCard = typeOfCard;
    }

    @Override
    public void doAction(Player player, Board board) {
        Util.print(player, player.getName() + " the " + player.piece
                + " has landed on Chance! \n\n - Press Enter to draw a card - \n");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        int index = new Random().nextInt(board.chanceCardsArray.length);
        Util.print(player, player.getName() + " has drawn : " + board.chanceCardsArray[index].getName());

        // Logic for Drawing a Chance Card
        if (index == 0) {
            Util.print(player, player.getName() + " moves to BoardWalk");
            player.setPosition(39);
            board.squares[39].doAction(player, board);
        } else if (index == 1) {
            Util.print(player, player.getName() + " moves to Illinois Ave.");
            if (player.position > 23) {
                player.getMoney().addMoney(200.0);
                Util.print(player, player.getName() + " passes Go and collects $200");
            }
            player.setPosition(23);
            board.squares[23].doAction(player, board);
        } else if (index == 2) {
            if (player.position < 20) {
                Util.print(player, player.getName() + " moves to Electric Company");
                player.setPosition(12);
                board.squares[12].doAction(player, board);
            } else {
                Util.print(player, player.getName() + " moves to Water Works");
                player.setPosition(28);
                board.squares[28].doAction(player, board);
            }
        } else if (index == 3) {
            if (player.position < 10) {
                Util.print(player, player.getName() + " moves to Reading Railroad");
                player.setPosition(5);
                board.squares[5].doAction(player, board);
            } else if (player.position < 20) {
                Util.print(player, player.getName() + " moves to Pennsylvannia Railroad");
                player.setPosition(15);
                board.squares[15].doAction(player, board);
            } else if (player.position < 30) {
                Util.print(player, player.getName() + " moves to B. & O. Railroad");
                player.setPosition(25);
                board.squares[25].doAction(player, board);
            } else if (player.position < 40) {
                Util.print(player, player.getName() + " moves to Short Line Railroad");
                player.setPosition(35);
                board.squares[35].doAction(player, board);
            }
        } else if (index == 4) {
            player.getMoney().addMoney(1000.0);
            Util.print(player, player.getName() + " won The Lottery +$1000");
        }
    }
}