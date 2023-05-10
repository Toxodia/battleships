package battleship;

import java.util.Scanner;

public class Player {
    private PlayingBoard playingBoard = new PlayingBoard();
    private Scanner scanner = new Scanner(System.in);

    public Player() {
        playingBoard.printFullBoard();
        fillBoardWithShips();
    }

    private void fillBoardWithShips() {
        placeShip("Aircraft Carrier", 5);
        placeShip("Battleship", 4);
        placeShip("Submarine", 3);
        placeShip("Cruiser", 3);
        placeShip("Destroyer", 2);
    }

    private void placeShip(String name, int size) {
        System.out.println("Enter the coordinates of the " + name + " (" + size + " cells):");
        Ship ship = new Ship(name, size);
        ship.collectCoordinates(playingBoard);
        ship.placeOnBoard(playingBoard);
        playingBoard.addToShipList(ship);
    }
}
