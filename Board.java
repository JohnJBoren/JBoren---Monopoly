import java.io.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.*;

public class Board {
	int currentTurn = 0;
	int totalPlayer = 0;
	Player[] players;
	Square[] squares = new Square[40];
	StringTokenizer[] names = new StringTokenizer[40];
	int howMany = 5;
	Card[] commCardsArray = new Card[howMany]; // Per Spec Sheet
	Card[] chanceCardsArray = new Card[howMany]; // Per Spec Sheet
	String[] pieces = new String[howMany];

	public Board(int totalPlayer) {

		this.players = new Player[totalPlayer];
		this.totalPlayer = totalPlayer;

		// Import the Chance Cards
		String chanceFile = "chance.txt";
		String communityFile = "community.txt";
		String square = "squares.txt";
		String piece = "pieces.txt";

		File chanceCards = new File(chanceFile);
		File communityCards = new File(communityFile);
		File squaresFile = new File(square);
		File pieceFile = new File(piece);

		try {
			Scanner takeAChanceCard = new Scanner(chanceCards);
			Scanner takeACommCard = new Scanner(communityCards);
			Scanner takeASquareCard = new Scanner(squaresFile);
			Scanner takeAPiece = new Scanner(pieceFile);

			// Create Chance Cards
			int j = 0;
			while (takeAChanceCard.hasNextLine()) {
				this.chanceCardsArray[j] = new Card(takeAChanceCard.nextLine());
				j++;
			}

			// Create Community Chest Cards
			int k = 0;
			while (takeACommCard.hasNextLine()) {
				this.commCardsArray[k] = new Card(takeACommCard.nextLine());
				k++;
			}

			// Create Squares
			int l = 0;
			while (takeASquareCard.hasNextLine()) {
				this.names[l] = new StringTokenizer(takeASquareCard.nextLine(), ",");
				l++;
			}

			// Create the pieces
			int c = 0;
			while (takeAPiece.hasNextLine()) {
				this.pieces[c] = takeAPiece.nextLine();
				c++;
			}

			// Create the various Squares
			for (int m = 0; m < names.length; m++) {
				int typeOfCard = Integer.parseInt(names[m].nextToken());
				String nameOfCard = names[m].nextToken().toString();
				if (typeOfCard == 1) {
					this.squares[m] = new GoSquare(typeOfCard, nameOfCard);
				} else if (typeOfCard == 2) {
					String color = (String) names[m].nextToken();
					Double price = Double.parseDouble(names[m].nextToken());
					Double rent = Double.parseDouble(names[m].nextToken());
					Double rentWitOne = Double.parseDouble(names[m].nextToken());
					Double rentWitTwo = Double.parseDouble(names[m].nextToken());
					Double rentWitThree = Double.parseDouble(names[m].nextToken());
					Double rentWitFour = Double.parseDouble(names[m].nextToken());
					this.squares[m] = new PropertySquare(typeOfCard, nameOfCard, color, price, rent, rentWitOne,
							rentWitTwo, rentWitThree, rentWitFour);
				} else if (typeOfCard == 3) {
					Double tax = Double.parseDouble(names[m].nextToken());
					this.squares[m] = new TaxSquare(typeOfCard, nameOfCard, tax);

				} else if (typeOfCard == 4) {
					Double price = Double.parseDouble(names[m].nextToken());
					Double rent = Double.parseDouble(names[m].nextToken());
					this.squares[m] = new RailRoadSquare(typeOfCard, nameOfCard, price, rent);
				} else if (typeOfCard == 5) {
					Double price = Double.parseDouble(names[m].nextToken());
					int multiplier = Integer.parseInt(names[m].nextToken());
					this.squares[m] = new UtilitySquare(typeOfCard, nameOfCard, price, multiplier);
				} else if (typeOfCard == 6) {
					this.squares[m] = new CommunityChest(typeOfCard, nameOfCard);
				} else if (typeOfCard == 7) {
					this.squares[m] = new Chance(typeOfCard, nameOfCard);
				} else if (typeOfCard == 8) {
					this.squares[m] = new JailSquare(typeOfCard, nameOfCard);
				} else if (typeOfCard == 9) {
					this.squares[m] = new FreeParking(typeOfCard, nameOfCard);
				} else {
					this.squares[m] = new GoToJailSquare(typeOfCard, nameOfCard);
				}
				takeAChanceCard.close();
				takeACommCard.close();
				takeASquareCard.close();
				takeAPiece.close();
			}

		} catch (FileNotFoundException nothere) {
			System.out.println("File Not Found");
		}
	}

	public Square movePlayer(Player player, int face) {
		return this.movePlayer(player, face, true);
	}

	public Square movePlayer(Player player, int face, boolean count) {
		if (player.isBroke()) {
			return this.squares[player.getCurrentPosition()];
		}
		// This is my attempt to skip moving a player if they are in Jail
		if (player.isInJail()) {
			player.setInJail(false);
			return this.squares[player.getCurrentPosition()];
		}

		int newPosition = normalizePosition(player, player.getCurrentPosition() + face);
		player.setPosition(newPosition);
		Util.print(player, player.getName() + " lands on " + squares[player.getCurrentPosition()].getName());
		this.squares[newPosition].doAction(player, this); // Polymorphism in action
		if (player.getMoney().isBroke()) {
			Util.print(player, player.getName() + " has gone broke!");
			player.setIsBroke(true);
		} else {
			if (count) {
				player.nextTurn();
			}
		}
		return this.squares[newPosition];
	}

	// If all but one player goes broke there is a winner
	public boolean hasWinner() {
		int ingame = 0;
		for (Player player : this.players) {
			if (!player.isBroke()) {
				ingame++;
			}
		}
		return ingame <= 1;
	}

	// The winner is the only player that did not go broke
	public Player getWinner() {
		if (!this.hasWinner()) {
			return null;
		}
		for (Player player : players) {
			if (!player.isBroke()) {
				return player;
			}
		}
		return null;
	}

	// The winner after ten rounds is the Player with the most money
	public Player getPlayerMostMoney() {
		Player maxplayer = null;
		for (Player player : this.players) {
			if (maxplayer == null || maxplayer.getMoney().getMoney() < player.getMoney().getMoney()) {
				maxplayer = player;
			}
		}
		return maxplayer;
	}

	// this loops the Player around the board and adds money when they pass go.
	public int normalizePosition(Player player, int position) {
		if (position >= 40) {
			player.getMoney().addMoney(200.0);
		}
		return position % squares.length;
	}

	public Player getCurrentPlayer() {
		return this.players[currentTurn];
	}

	public Player[] getPlayers() {
		return this.players;
	}

	public void nextTurn() {
		if (++this.currentTurn >= this.players.length) {
			this.currentTurn = 0;
		}
	}

	public Player getPlayer(int id) {

		return this.players[id];
	}

	public int getTotalSquare() {
		return this.squares.length;
	}

}
