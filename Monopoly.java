import java.util.Scanner;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monopoly {
    Die die1 = new Die();
    Die die2 = new Die();
    Board board;
    int x = 875;
    int y = 875;

    public Monopoly(int totalPlayer) {
        this.board = new Board(totalPlayer);

    }

    public static void main(String[] args) {

        System.out.println("\tMonopoly\n");
        Scanner scanner = new Scanner(System.in);
        int totalPlayer = 0;
        while (totalPlayer < 2 || totalPlayer > 5) {
            try {
                System.out.println("How many people are playing?");
                System.out.print("Players (2 - 5): ");
                totalPlayer = scanner.nextInt();
            } catch (Exception e) {
                System.err.println("Error: Number too large.");
                continue;
            }
            if (totalPlayer > 5) {
                System.err.println("Error: Invalid player count.");
            }
        }

        Monopoly game = new Monopoly(totalPlayer);
        game.createPlayers(totalPlayer, game);
        game.createGUI(game);

    }

    public void createPlayers(int totalPlayer, Monopoly game) {
        // Create the players
        Scanner in = new Scanner(System.in);

        List<Integer> randomInt = new ArrayList<Integer>();
        for (int r = 0; r < 5; r++) {
            randomInt.add(r);
        }
        Collections.shuffle(randomInt);

        for (int i = 0; i < board.players.length; i++) {
            System.out.println("\nPlease enter your name: \n");
            String name = in.next();
            System.out.println(name + " is the : " + game.board.pieces[randomInt.get(i)]);
            game.board.players[i] = new Player(i, name, game.board.pieces[randomInt.get(i)]);
        }
    }

    // Create the GUI
    public void createGUI(Monopoly game) {
        ImagePanel panel = new ImagePanel(new ImageIcon("MonoBoard.jpg").getImage());
        ImagePanel thimble = new ImagePanel(new ImageIcon("Thimble2.gif").getImage());
        ImagePanel racecar = new ImagePanel(new ImageIcon("racecar.gif").getImage());
        ImagePanel tophat = new ImagePanel(new ImageIcon("tophat.png").getImage());
        ImagePanel boot = new ImagePanel(new ImageIcon("boot.png").getImage());
        ImagePanel battleship = new ImagePanel(new ImageIcon("battleship.png").getImage());
        panel.add(thimble).setLocation(x, y);
        panel.add(racecar).setLocation(x, y + 25);
        panel.add(tophat).setLocation(x, y + 50);
        panel.add(boot).setLocation(x, y + 75);
        panel.add(battleship).setLocation(x, y + 100);
        JFrame frame = new JFrame();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.startGame(panel, thimble, racecar, tophat, boot, battleship, frame);
    }

    // Game logic and graphics
    public void startGame(JPanel panel, JPanel thimble, JPanel racecar, JPanel tophat, JPanel boot, JPanel battleship,
            JFrame frame) {
        System.out.println("Game start!");
        System.out.println("========");

        while (!isGameEnd() && !board.hasWinner()) {

            if (!board.getCurrentPlayer().isBroke() && !board.getCurrentPlayer().isInJail()) {

                Util.print(board.getCurrentPlayer(),
                        board.getCurrentPlayer().getName() + "\n\n - Press ENTER to Roll the Dice: -\n");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
                int face = board.getCurrentPlayer().tossDice(die1, die2);

                // Move Player thimble on the image board
                if ((board.getCurrentPlayer().getCurrentPosition() + face) <= 10
                        && board.getCurrentPlayer().getPiece().equals("Thimble")) {
                    int pos = board.getCurrentPlayer().getCurrentPosition();
                    panel.remove(thimble);
                    panel.add(thimble).setLocation(875 - ((pos + face) * 80), 875);
                    panel.revalidate();
                    frame.revalidate();
                } else if ((board.getCurrentPlayer().getCurrentPosition() + face) <= 20
                        && board.getCurrentPlayer().getPiece().equals("Thimble")) {
                    int pos = board.getCurrentPlayer().getCurrentPosition() - 10;
                    panel.remove(thimble);
                    panel.add(thimble).setLocation(10, 900 - ((pos + face) * 80));
                    panel.revalidate();
                    frame.revalidate();
                } else if ((board.getCurrentPlayer().getCurrentPosition() + face) <= 30
                        && board.getCurrentPlayer().getPiece().equals("Thimble")) {
                    int pos = board.getCurrentPlayer().getCurrentPosition() - 20;
                    panel.remove(thimble);
                    panel.add(thimble).setLocation(90 + ((pos + face) * 80), 10);
                    panel.revalidate();
                    frame.revalidate();
                } else if ((board.getCurrentPlayer().getCurrentPosition() + face) <= 39
                        && board.getCurrentPlayer().getPiece().equals("Thimble")) {
                    int pos = board.getCurrentPlayer().getCurrentPosition() - 30;
                    panel.remove(thimble);
                    panel.add(thimble).setLocation(900, 90 + ((pos + face) * 80));
                    panel.revalidate();
                    frame.revalidate();
                } else if ((board.getCurrentPlayer().getCurrentPosition() + face) == 40
                        && board.getCurrentPlayer().getPiece().equals("Thimble")) {
                    panel.remove(thimble);
                    panel.add(thimble).setLocation(960, y + 90);
                    panel.revalidate();
                    frame.revalidate();
                }

                // Move racecar
                if ((board.getCurrentPlayer().position + face) <= 10
                        && board.getCurrentPlayer().getPiece().equals("Racecar")) {
                    int pos = board.getCurrentPlayer().position;
                    panel.add(racecar).setLocation(875 - ((pos + face) * 80), 900);
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) <= 20
                        && board.getCurrentPlayer().getPiece().equals("Racecar")) {
                    int pos = board.getCurrentPlayer().position - 10;
                    panel.add(racecar).setLocation(25, 900 - ((pos + face) * 80));
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) <= 30
                        && board.getCurrentPlayer().getPiece().equals("Racecar")) {
                    int pos = board.getCurrentPlayer().position - 20;
                    panel.add(racecar).setLocation(90 + ((pos + face) * 80), 35);
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) <= 39
                        && board.getCurrentPlayer().getPiece().equals("Racecar")) {
                    int pos = board.getCurrentPlayer().position - 30;
                    panel.add(racecar).setLocation(925, 90 + ((pos + face) * 80));
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) == 40
                        && board.getCurrentPlayer().getPiece().equals("Racecar")) {
                    panel.add(racecar).setLocation(925, y + 90);
                    panel.revalidate();
                }

                // Move tophat
                if ((board.getCurrentPlayer().position + face) <= 10
                        && board.getCurrentPlayer().getPiece().equals("Top Hat")) {
                    int pos = board.getCurrentPlayer().position;
                    panel.add(tophat).setLocation(875 - ((pos + face) * 80), 925);
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) <= 20
                        && board.getCurrentPlayer().getPiece().equals("Top Hat")) {
                    int pos = board.getCurrentPlayer().position - 10;
                    panel.add(tophat).setLocation(50, 900 - ((pos + face) * 80));
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) <= 30
                        && board.getCurrentPlayer().getPiece().equals("Top Hat")) {
                    int pos = board.getCurrentPlayer().position - 20;
                    panel.add(tophat).setLocation(90 + ((pos + face) * 80), 60);
                    panel.validate();
                } else if ((board.getCurrentPlayer().position + face) <= 39
                        && board.getCurrentPlayer().getPiece().equals("Top Hat")) {
                    int pos = board.getCurrentPlayer().position - 30;
                    panel.add(tophat).setLocation(950, 90 + ((pos + face) * 80));
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) == 40
                        && board.getCurrentPlayer().getPiece().equals("Top Hat")) {
                    panel.add(tophat).setLocation(950, y + 90);
                    panel.revalidate();
                }

                // Move boot
                if ((board.getCurrentPlayer().position + face) <= 10
                        && board.getCurrentPlayer().getPiece().equals("Boot")) {
                    int pos = board.getCurrentPlayer().position;
                    panel.add(boot).setLocation(875 - ((pos + face) * 80), 950);
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) <= 20
                        && board.getCurrentPlayer().getPiece().equals("Boot")) {
                    int pos = board.getCurrentPlayer().position - 10;
                    panel.add(boot).setLocation(75, 900 - ((pos + face) * 80));
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) <= 30
                        && board.getCurrentPlayer().getPiece().equals("Boot")) {
                    int pos = board.getCurrentPlayer().position - 20;
                    panel.add(boot).setLocation(90 + ((pos + face) * 80), 85);
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) <= 39
                        && board.getCurrentPlayer().getPiece().equals("Boot")) {
                    int pos = board.getCurrentPlayer().position - 30;
                    panel.add(boot).setLocation(975, 90 + ((pos + face) * 80));
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) == 40
                        && board.getCurrentPlayer().getPiece().equals("Boot")) {
                    panel.add(boot).setLocation(975, y + 90);
                    panel.revalidate();
                }

                // Move battleship
                if ((board.getCurrentPlayer().position + face) <= 10
                        && board.getCurrentPlayer().getPiece().equals("Battleship")) {
                    int pos = board.getCurrentPlayer().position;
                    panel.add(battleship).setLocation(875 - ((pos + face) * 80), 975);
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) <= 20
                        && board.getCurrentPlayer().getPiece().equals("Battleship")) {
                    int pos = board.getCurrentPlayer().position - 10;
                    panel.add(battleship).setLocation(100, 900 - ((pos + face) * 80));
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) <= 30
                        && board.getCurrentPlayer().getPiece().equals("Battleship")) {
                    int pos = board.getCurrentPlayer().position - 20;
                    panel.add(battleship).setLocation(90 + ((pos + face) * 80), 110);
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) <= 39
                        && board.getCurrentPlayer().getPiece().equals("Battleship")) {
                    int pos = board.getCurrentPlayer().position - 30;
                    panel.add(battleship).setLocation(1000, 90 + ((pos + face) * 80));
                    panel.revalidate();
                } else if ((board.getCurrentPlayer().position + face) == 40
                        && board.getCurrentPlayer().getPiece().equals("Battleship")) {
                    panel.add(battleship).setLocation(1000, y + 90);
                    panel.revalidate();
                }

                board.movePlayer(board.getCurrentPlayer(), face);
                board.getCurrentPlayer().setInJail(false);
            }

            board.nextTurn();
        }
        System.out.println("========");
        if (board.hasWinner()) {
            System.out.println(board.getWinner().getName() + " the " + board.getWinner().getPiece()
                    + " is the winner because everyone else went broke!");
        } else {
            System.out.println(board.getPlayerMostMoney().getName() + " the " + board.getPlayerMostMoney().getPiece()
                    + " is the winner because they have the most money!");
        }
        System.out.println("Game over!");
    }

    public boolean isGameEnd() {
        for (Player player : board.getPlayers()) {
            if (player.getTurn() < 10) {
                return false;
            }
        }
        return true;
    }
}

class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
