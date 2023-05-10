package battleship;

import java.util.List;
import java.util.Scanner;

public class Battleship {
    private final String[][] playingBoard = new String[11][11];

    private final Scanner scanner = new Scanner(System.in);



    public Battleship() {
        initGame();
    }

    private void initGame() {

        placeShips();
        startGame();
    }



    /*private boolean checkForOtherShip(int shipSize) {
        shipCoordinates.createCurrentCoordinateArray(shipSize);
        for (List<Integer> coordinateList : shipCoordinates.getCoordinateList()) {
            int row = coordinateList.get(0);
            int col = coordinateList.get(1);

            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i < playingBoard.length && j < playingBoard[0].length && playingBoard[i][j].equals(SHIPSYMBOL)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }*/



    private boolean checkIfHitCoordinateValid(int x, int y) {
        return (x > 0 && x < playingBoard.length) && (y > 0 && y < playingBoard[0].length);
    }


    private void placeShips() {
        placeShip("Aircraft Carrier", 5);
        placeShip("Battleship", 4);
        placeShip("Submarine", 3);
        placeShip("Cruiser", 3);
        placeShip("Destroyer", 2);
    }

    private void placeShip(String shipType, int shipSize) {
        boolean trigger = false;
        System.out.println("Enter the coordinates of the " + shipType + " (" + shipSize + " cells):\n");
        while (!trigger) {

        }

    }


    private void shoot() {
        int x = 0;
        int y = 0;
        boolean trigger = true;
        while (trigger) {
            String[] shotCoordinate = scanner.next().split("");
            x = shotCoordinate[0].charAt(0) - 64;
            if (shotCoordinate.length >= 3) {
                y = Integer.parseInt(shotCoordinate[1] + shotCoordinate[2]);
            } else {
                y = Integer.parseInt(shotCoordinate[1]);
            }
            if (!checkIfHitCoordinateValid(x, y)) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            } else {
                trigger = false;
            }
        }

    }

    private void startGame() {
        System.out.println("The game starts!");

        System.out.println("Take a shot!\n");
        shoot();

    }
}
