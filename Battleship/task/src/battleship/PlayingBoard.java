package battleship;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PlayingBoard {
    private static final String letterBoardString = "ABCDEFGHIJ";
    final int xIndex = 0;
    final int yIndex = 1;

    private final String[][] playingBoard = new String[10][10];
    private List<Ship> shipList = new ArrayList<>();

    public PlayingBoard() {
        for (int i = 0; i < playingBoard.length; i++) {
            for (int j = 0; j < playingBoard[0].length; j++) {
                playingBoard[i][j] = "~";
            }
        }
    }

    public void addToShipList(Ship ship) {
        shipList.add(ship);
        putOnBoard(ship);
    }

    private int[] buildCoordinate(String coordinate) {

        String[] temp = coordinate.split("", 2);
        int[] intArray = new int[2];
        intArray[xIndex] = letterBoardString.indexOf(temp[0]);
        intArray[yIndex] = Integer.parseInt(temp[1]) - 1;
        return intArray;
    }

    /**
     * @param coordinate f5 g8
     * @return M, X, O, ~
     */
    public String getCoordinates(String coordinate) {
        int[] pos = buildCoordinate(coordinate);
        return playingBoard[pos[xIndex]][pos[yIndex]];
    }

    public String[][] getMatrix() {
        return playingBoard;
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public void printFullBoard() {
        System.out.print("\n ");
        for (int i = 0; i < playingBoard.length; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < playingBoard.length; i++) {
            System.out.print(letterBoardString.charAt(i));
            for (int j = 0; j < playingBoard[0].length; j++) {
                System.out.print(" " + playingBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printFogOfWarBoard() {
        System.out.print("\n ");
        for (int i = 0; i < playingBoard.length; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        for (int i = 0; i < playingBoard.length; i++) {
            System.out.print(letterBoardString.charAt(i));
            for (int j = 0; j < playingBoard[0].length; j++) {
                if (playingBoard[i][j].equals("O")) {
                    System.out.print(" ~");
                } else {
                    System.out.print(" " + playingBoard[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void putOnBoard(Ship ship) {
        for (int[] coordinate : ship.getCoordinateArray()) {
            playingBoard[coordinate[0]][coordinate[1]] = Symbol.SHIP_SYMBOL.getSymbol();
        }
    }

    /**
     * @param coordinate f5 g8
     * @param value      true false
     */
    public void setCoordinates(String coordinate, String value) {
        int[] pos = buildCoordinate(coordinate);
        playingBoard[pos[xIndex]][pos[yIndex]] = value;
    }

    public boolean shoot(InputStream input) {
        Scanner scanner = new Scanner(input);
        String coordinate = scanner.next().toUpperCase();

        for (Ship ship : shipList) {
            if (ship.hitShip(coordinate)) {
                setCoordinates(coordinate, Symbol.HIT_SYMBOL.getSymbol());
                if (ship.isDestroyed()) {
                    if (shipList.stream().allMatch(Ship::isDestroyed)) {
                        clearUpAfterShot(ShotStatus.WIN);
                        return false;
                    }
                    clearUpAfterShot(ShotStatus.DESTROYED);
                    return true;
                }
                clearUpAfterShot(ShotStatus.HIT);
                return true;
            }
        }
        setCoordinates(coordinate, Symbol.MISS_SYMBOL.getSymbol());
        clearUpAfterShot(ShotStatus.MISS);
        return true;
    }

    public void clearUpAfterShot(ShotStatus status) {
        printFogOfWarBoard();
        if (status.equals(ShotStatus.HIT)) {
            System.out.println("You hit a ship!");
        } else if (status.equals(ShotStatus.DESTROYED)) {
            System.out.println("You sank a ship! Specify a new target:\n");
        } else if (status.equals(ShotStatus.WIN)) {
            System.out.println("You sank the last ship. You won. Congratulations!\n");
        } else if (status.equals(ShotStatus.MISS)) {
            System.out.println("You missed!");
        }
    }

}
