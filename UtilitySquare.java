import java.util.Scanner;

public class UtilitySquare extends BuyableSquare {
    int multiplier;
    Die die1 = new Die();
    Die die2 = new Die();
    int typeOfCard;

    public UtilitySquare(String name, Double price, int multiplier1) {
        super(name, price);
        this.multiplier = multiplier1;

    }

    public UtilitySquare(int typeOfCard, String nameOfCard, Double price, int multiplier1) {
        super(nameOfCard, price);
        this.multiplier = multiplier1;
        this.typeOfCard = typeOfCard;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public Double getPrice() {
        return this.price;
    }

    @Override
    public void doAction(Player player, Board board) {
        int buyHouse = -1;
        Scanner sc = new Scanner(System.in);
        boolean badNumber = false;
        if ((owner < 0) && (player.getMoney().money > this.getPrice())) {
            Util.print(player, player.getName() + " the " + player.piece + ", do you want to buy " + super.getName()
                    + "?\n\n - Enter 1: Yes or 2: No: - \n");
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
                    System.out.println("\n\n - Please enter either 1 for Yes, or 2 for No -\n");
                    sc.next();
                    badNumber = true;
                }
            } while (badNumber);
            if (buyHouse == 1) {
                player.getMoney().substractMoney(this.getPrice());
                Util.print(player, player.getName() + " buys " + super.getName() + " for $" + price);
                owner = player.getID();

            } else {
                Util.print(player, player.getName() + " doesn't want to buy " + super.getName());
            }
        } else {
            if (owner != player.getID() && !board.getPlayer(owner).isBroke()) {
                int rolled = board.getCurrentPlayer().tossDice(die1, die2);
                Double lost = (double) rolled * (double) multiplier;
                player.getMoney().substractMoney(lost);
                board.getPlayer(owner).getMoney().addMoney(lost);
                Util.print(player, player.getName() + " rolled a: " + rolled + " and paid: " + lost + " in bills to "
                        + board.getPlayer(owner).getName());

            }
        }
    }
}