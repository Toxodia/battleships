package battleship;

import java.io.InputStream;
import java.util.Scanner;

public class Util {
    private static final String letterBoardString = "ABCDEFGHIJ";


    public static int[] queryCoordinates(InputStream in) {
        Scanner scanner = new Scanner(in);
        String[] coordinateOne = scanner.next().split("");
        String[] coordinateTwo = scanner.next().split("");
        int[] intArray = new int[4];
        intArray[0] = letterBoardString.indexOf(coordinateOne[0].toUpperCase());
        intArray[1] = Integer.parseInt(coordinateOne[1]) - 1;
        intArray[2] = letterBoardString.indexOf(coordinateTwo[0].toUpperCase());
        intArray[3] = Integer.parseInt(coordinateTwo[1]) - 1;
        return intArray;
    }

    public static int[] queryCoordinates() {
        return queryCoordinates(System.in);
    }


    public static ValidationResult areCoordinatesValid(int[] coordinates, int size) {
        if (coordinates.length != 4) {
            return ValidationResult.WRONG_OVERALL;
        }
        if (coordinates[0] != coordinates[2] && coordinates[1] != coordinates[3]) {
            return ValidationResult.WRONG_POSITION;
        }
        for (int coordinate : coordinates) {
            if (coordinate > 9) {
                return ValidationResult.WRONG_OOB;
            }
        }
        if (size != Math.abs(coordinates[0] - coordinates[2]) + 1 && size != Math.abs(coordinates[1] - coordinates[3]) + 1) {
            return ValidationResult.WRONG_LENGTH;
        }
        return ValidationResult.SUCCESS;
    }
}
