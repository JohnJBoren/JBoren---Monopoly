import java.util.Scanner;

public class PropertySquare extends BuyableSquare {
    int houses;
    Boolean availHouse;
    int typeOfCard;
    String color;
    Double price;
    Double rent;
    Double rentWitOne;
    Double rentWitTwo;
    Double rentWitThree;
    Double rentWitFour;

    public PropertySquare() {
        super("George", 100.0);
        this.houses = 0;
        this.availHouse = true;
        this.typeOfCard = 1;
        this.color = "red";
        this.price = 1.0;
        this.rent = 1.0;
        this.rentWitOne = 2.0;
        this.rentWitTwo = 3.0;
        this.rentWitThree = 4.0;
        this.rentWitFour = 5.0;
    }

    public PropertySquare(String name, Double price) {
        super(name, price);
        this.houses = 0;
        this.availHouse = true;
        this.typeOfCard = 1;
        this.color = "red";
        this.price = 1.0;
        this.rent = 1.0;
        this.rentWitOne = 2.0;
        this.rentWitTwo = 3.0;
        this.rentWitThree = 4.0;
        this.rentWitFour = 5.0;
    }

    public PropertySquare(int typeOfCard, String nameOfCard, String color, Double price, Double rent, Double rentWitOne,
            Double rentWitTwo, Double rentWitThree, Double rentWitFour) {
        super(nameOfCard, price);
        this.houses = 0;
        this.availHouse = true;
        this.typeOfCard = typeOfCard;
        this.color = color;
        this.price = price;
        this.rent = rent;
        this.rentWitOne = rentWitOne;
        this.rentWitTwo = rentWitTwo;
        this.rentWitThree = rentWitThree;
        this.rentWitFour = rentWitFour;
    }

    public void setOwner(int owner) {
        super.setOwner(owner);
    }

    public int getOwner() {
        return super.owner;
    }

    public Double getPrice() {
        return this.price;
    }

    public Boolean houseAvail() {
        if (this.houses < 4) {
            return this.availHouse;
        } else {
            return this.availHouse;
        }
    }

    public void buyHouse() {
        if (this.houses < 4) {
            return;
        } else {

        }
    }

    @Override
    public void doAction(Player player, Board board) {
        int buyHouse = -1;
        int buyAnotherHouse = -1;
        Scanner sc = new Scanner(System.in);
        boolean badNumber = false;
        boolean badNumber2 = false;

        // if the property is not owned you can buy it
        if ((super.owner < 0) && (player.getMoney().money > this.getPrice())) {

            Util.print(player, player.getName() + " the " + player.piece + ", do you want to buy " + super.getName()
                    + "? \n\n - Enter 1: Yes or 2: No: - \n");
            do {
                try {
                    buyHouse = sc.nextInt();
                    if (buyHouse != 1 && buyHouse != 2) {
                        System.out.println(
                                "Your Number is: " + buyHouse + "\n - Please enter either 1 for Yes, or 2 for No -\n");
                        badNumber = true;
                    } else {
                        badNumber = false;
                    }

                } catch (Exception e) {
                    System.out.println("\n  - Please enter either 1 for Yes, or 2 for No -\n");
                    sc.next();
                    badNumber = true;
                }
            } while (badNumber);

            if (buyHouse == 1) {
                Util.print(player,
                        player.getName() + " the " + player.piece + " buys " + super.getName() + " for $" + price);
                super.owner = player.getID();
                player.getMoney().substractMoney(price);
            } else {
                Util.print(player,
                        player.getName() + " the " + player.piece + " doesn't want to buy " + super.getName());
            }
            // if you are the owner you can buy houses
        } else if (super.owner != player.getID() && super.owner != -1) {
            Double lost = rent;
            if (this.houses == 0) {
                lost = this.rent;
            } else if (this.houses == 1) {
                lost = this.rentWitOne;
            } else if (this.houses == 2) {
                lost = this.rentWitTwo;
            } else if (this.houses == 3) {
                lost = this.rentWitThree;
            } else if (this.houses == 4) {
                lost = this.rentWitFour;
            }

            player.getMoney().substractMoney(lost);
            board.getPlayer(owner).getMoney().addMoney(lost);
            Util.print(player, player.getName() + " the " + player.piece + " paid: $" + lost + " in rent to "
                    + board.getPlayer(owner).getName());

        } else {
            if (super.owner == player.getID() && this.houseAvail()) {
                Util.print(player, player.getName() + " the " + player.piece + " you have " + houses
                        + " houses with a max of 4 available.");
                Util.print(player, player.getName() + " the " + player.piece + ", do you want to buy a house on: "
                        + super.getName() + "?");
                Util.print(player, player.getName() + "\n\n - Enter 1: Yes or 2: No: \n");

                do {
                    try {
                        buyAnotherHouse = sc.nextInt();

                        if (buyAnotherHouse != 1 && buyAnotherHouse != 2) {
                            System.out.println("Your Number is: " + buyAnotherHouse
                                    + "\n\n Please enter either 1 for Yes, or 2 for No \n");
                            badNumber2 = true;
                        } else {
                            badNumber2 = false;
                        }

                    } catch (Exception e) {
                        System.out.println("\n\n - Please enter either 1 for Yes, or 2 for No -\n");
                        sc.next();
                        badNumber2 = true;
                    }
                } while (badNumber2);
            }
            if (buyAnotherHouse == 1 && super.owner == player.getID()) {
                this.houses += 1;
                player.getMoney().substractMoney(50.0);
                Util.print(player,
                        player.getName() + " the " + player.piece + " buys a house.  For a total of : " + this.houses);
            } else {
                Util.print(player,
                        player.getName() + " the " + player.piece + " doesn't want to buy another house right meow");
            }

        }
    }
}