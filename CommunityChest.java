import java.util.Random;
import java.util.Scanner;

public class CommunityChest extends Square {
    int typeOfCard;

    public CommunityChest(int typeOfCard, String name) {
        super(name);
        this.typeOfCard = typeOfCard;
    }

    @Override
    public void doAction(Player player, Board board) {
        Util.print(player, player.getName() + " the " + player.piece
                + " has landed on Community Chest! \n\n - Press Enter to draw a card - \n");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        int index = new Random().nextInt(board.commCardsArray.length);
        Util.print(player,
                player.getName() + " the " + player.piece + " has drawn : " + board.commCardsArray[index].getName());

        // Logic for Community Chest Cards
        if (index == 0) {
            player.setPosition(0);
            board.squares[0].doAction(player, board); // Polymorphism in action
        } else if (index == 1) {
            player.getMoney().substractMoney(100.0);
            Util.print(player, player.getName() + " the " + player.piece + " pays the Doctor $100");
        } else if (index == 2) {
            Util.print(player, player.getName() + " the " + player.piece
                    + " has been arrested and must go straight to Jail, do not pass Go, do not collect $200 and lose a turn");
            player.setPosition(10);
            player.setInJail(true);
            board.squares[10].doAction(player, board);
        } else if (index == 3) {
            player.getMoney().substractMoney(200.0);
            Util.print(player, player.getName() + " the " + player.piece + " pays School Fees $200");
        } else if (index == 4) {
            player.getMoney().addMoney(350.0);
            Util.print(player, player.getName() + " the " + player.piece + " PFD pays you $350");
        }
    }
}
