package battleship;

import java.util.Arrays;

public class CoordinateBuilder {
    private static final String letterBoardString = "ABCDEFGHIJ";
    int[] coordinatesAsInts;
    private int[][] intArray;
    private String[] coordinateArray;

    public CoordinateBuilder(int[] coordinatesAsInts) {
        this.coordinatesAsInts = coordinatesAsInts;
        buildCoordinateArray();
    }

    private void buildCoordinateArray() {
        int x1 = coordinatesAsInts[0];
        int y1 = coordinatesAsInts[1] + 1;
        int x2 = coordinatesAsInts[2];
        int y2 = coordinatesAsInts[3] + 1;

        int rowDiff = Math.abs(y1 - y2);
        int colDiff = Math.abs(x1 - x2);

        int numCoords = Math.max(rowDiff, colDiff) + 1;


        coordinateArray = new String[numCoords];
        intArray = new int[numCoords][2];

        // Fill in the in-between coordinates
        int index = 0;
        if (rowDiff == 0) {
            // The coordinates are in the same row
            int startCol = Math.min(x1, x2);
            int endCol = Math.max(x1, x2);
            for (int col = startCol; col <= endCol; col++) {
                coordinateArray[index] = "" + letterBoardString.charAt(col) + y1;
                intArray[index][0] = col;
                intArray[index][1] = y1 - 1;
                index++;
            }
        } else {
            // The coordinates are in the same column
            int startRow = Math.min(y1, y2);
            int endRow = Math.max(y1, y2);
            for (int row = startRow; row <= endRow; row++) {
                coordinateArray[index] = "" + letterBoardString.charAt(x1) + row;
                intArray[index][0] = x1;
                intArray[index][1] = row - 1;
                index++;
            }
        }
    }

    public String[] getCoordinateArray() {
        return coordinateArray;
    }

    public int[][] getIntArray() {
        return intArray;
    }
}
