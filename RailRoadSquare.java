import java.util.Scanner;

public class RailRoadSquare extends BuyableSquare {
    int typeOfCard;
    Double price;
    Double rent;

    public RailRoadSquare(String name, Double price) {
        super(name, price);
    }

    public RailRoadSquare(int typeOfCard, String nameOfCard, Double price, Double rent) {
        super(nameOfCard, price);
        this.typeOfCard = typeOfCard;
        this.price = price;
        this.rent = rent;
    }

    public void setOwner(int owner) {
        this.setOwner(owner);
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public void doAction(Player player, Board board) {
        int buyHouse = -1;
        Scanner sc = new Scanner(System.in);
        boolean badNumber = false;
        if ((owner < 0) && (player.getMoney().money > this.getPrice())) {
            Util.print(player, player.getName() + " the " + player.piece + ", do you want to buy " + super.getName()
                    + "?\n\n - Enter 1: Yes or 2: No: -\n");
            do {
                try {
                    buyHouse = sc.nextInt();
                    if (buyHouse != 1 && buyHouse != 2) {
                        System.out.println("Your Number is: " + buyHouse
                                + "\n\n - Please enter either 1 for Yes, or 2 for No -\n");
                        badNumber = true;
                    } else {
                        badNumber = false;
                    }

                } catch (Exception e) {
                    System.out.println(" \n\n -Please enter either 1 for Yes, or 2 for No \n");
                    sc.next();
                    badNumber = true;
                }
            } while (badNumber);
            if (buyHouse == 1) {
                Util.print(player, player.getName() + " buys " + super.getName() + " for $" + price);
                owner = player.getID();
                player.getMoney().substractMoney(price);
            } else {
                Util.print(player, player.getName() + " doesn't want to buy " + super.getName());
            }
            // this is my attempt to figure out how many railroads are owned and pay out
            // accordingly
        } else if (owner != player.getID()) {
            double tax = 25.0;
            Double railsOwned = 1.0;
            if (board.squares[5].getOwner() == board.squares[15].getOwner()) {
                railsOwned += 1.0;
            }

            if (board.squares[5].getOwner() == board.squares[25].getOwner()) {
                railsOwned += 1.0;
            }

            if (board.squares[5].getOwner() == board.squares[35].getOwner()) {
                railsOwned += 1.0;
            }

            if (board.squares[15].getOwner() == board.squares[25].getOwner()) {
                railsOwned += 1.0;
            }

            if (board.squares[15].getOwner() == board.squares[35].getOwner()) {
                railsOwned += 1.0;
            }

            if (board.squares[25].getOwner() == board.squares[35].getOwner()) {
                railsOwned += 1.0;
            }
            for (int i = 0; i <= railsOwned; i++) {
                tax = tax * 2.0;
            }
            player.getMoney().substractMoney(tax);
            board.getPlayer(owner).getMoney().addMoney(tax);
            Util.print(player, player.getName() + " paid: $" + tax + " in tax to " + board.getPlayer(owner).getName());
        }
    }
}
