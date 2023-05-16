package battleship;

import java.util.HashMap;
import java.util.Map;


public class Ship {


    private final String name;
    private final Map<String, Boolean> statusIsHealthy = new HashMap<>();
    private int[] intArray = new int[4];
    private int[][] coordinateArray;
    private int size = 0;
    private boolean isDestroyed = false;

    public Ship(String name, int size) {
        this.size = size;
        this.name = name;
    }


    public Ship(String name, String[] coordinates) {
        this.name = name;
        for (String coordinate : coordinates) {
            statusIsHealthy.put(coordinate, true);
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    private int calculateShipSize(int x, int y) {
        return Math.abs(x - y) + 1;
    }


    public boolean checkHit(String targetCoords) {
        if (!statusIsHealthy.containsKey(targetCoords)) {
            return false;
        }
        return statusIsHealthy.get(targetCoords);
    }

    private boolean checkShipSize() {
        return calculateShipSize(intArray[0], intArray[2]) == size || calculateShipSize(intArray[1], intArray[3]) == size;
    }

    public boolean hitShip(String targetCoords) {
        if (!statusIsHealthy.containsKey(targetCoords)) {
            return false;
        }
        statusIsHealthy.replace(targetCoords, false);
        if (getHp()==0) {
            isDestroyed = true;
        }
        return true;
    }

    @SuppressWarnings("CodeBlock2Expr")
    public int getHp() {
        return (int) statusIsHealthy
                .values()
                .stream()
                .filter(value -> {
                    return value;
                }).count();

    }


    public String getName() {
        return name;
    }

    public int[][] getCoordinateArray() {
        return coordinateArray;
    }

    public void collectCoordinates(PlayingBoard playingBoard) {

        boolean coordinatesValid = false;
        while (!coordinatesValid) {
            intArray = Util.queryCoordinates();
            switch (Util.areCoordinatesValid(intArray, size)) {
                case SUCCESS -> {
                    CoordinateBuilder coordinateBuilder = new CoordinateBuilder(intArray);
                    coordinateArray = coordinateBuilder.getIntArray();
                    if (isShipInWay(playingBoard, coordinateArray)) {
                        System.out.println("\nError! You placed it too close to another one. Try again:\n");
                        break;
                    }
                    coordinatesValid = true;
                    buildShip(coordinateBuilder.getCoordinateArray());
                }
                case WRONG_LENGTH -> System.out.println(ValidationResult.WRONG_LENGTH.getMessage(name));
                case WRONG_POSITION -> System.out.println(ValidationResult.WRONG_POSITION.getMessage());
            }

        }
    }

    private void buildShip(String[] coordinates) {
        for (String coordinate : coordinates) {
            statusIsHealthy.put(coordinate, true);
        }
    }

    private boolean isShipInWay(PlayingBoard playingBoard, int[][] coordinates) {
        String[][] matrix = playingBoard.getMatrix();
        for (int[] coordinate : coordinates) {
            {
                int row = coordinate[0];
                int col = coordinate[1];

                for (int i = Math.max(row - 1, 0); i <= Math.min(row + 1, matrix.length - 1); i++) {
                    for (int j = Math.max(col - 1, 0); j <= Math.min(col + 1, matrix[0].length - 1); j++) {
                        if (matrix[i][j].equals(Symbol.SHIP_SYMBOL.getSymbol())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void placeOnBoard(PlayingBoard playingBoard) {
        for (String coordinate : statusIsHealthy.keySet()) {
            if (statusIsHealthy.get(coordinate)) {
                playingBoard.setCoordinates(coordinate, Symbol.SHIP_SYMBOL.getSymbol());
            } else {
                playingBoard.setCoordinates(coordinate, Symbol.HIT_SYMBOL.getSymbol());
            }
        }
    }
}
