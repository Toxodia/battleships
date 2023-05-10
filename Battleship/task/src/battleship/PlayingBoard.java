package battleship;

import java.util.ArrayList;
import java.util.List;

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
        System.out.print(" ");
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
    }

    public void printFogOfWarBoard() {
        System.out.print(" ");
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
    }

    /**
     * @param coordinate f5 g8
     * @param value      true false
     */
    public void setCoordinates(String coordinate, String value) {
        int[] pos = buildCoordinate(coordinate);
        playingBoard[pos[xIndex]][pos[yIndex]] = value;
    }

    private void shoot(String coordinate) {
        for (Ship ship : shipList) {
            if (ship.hitShip(coordinate)) {
                setCoordinates(coordinate, Symbol.HIT_SYMBOL.getSymbol());
            } else {
                setCoordinates(coordinate, Symbol.MISS_SYMBOL.getSymbol());
            }
        }
    }

}
