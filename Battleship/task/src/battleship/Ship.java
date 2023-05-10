package battleship;

import java.util.HashMap;
import java.util.Map;


public class Ship {


    private final String name;
    private final Map<String, Boolean> statusIsHealthy = new HashMap<>();
    int[] intArray = new int[4];
    private int size = 0;


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

    public void collectCoordinates(PlayingBoard playingBoard) {

        boolean coordinatesValid = false;
        while (!coordinatesValid) {
            intArray = Util.queryCoordinates();
            switch (Util.areCoordinatesValid(intArray, size)) {
                case SUCCESS -> {
                    CoordinateBuilder coordinateBuilder = new CoordinateBuilder(intArray);
                    int[][] coordinateArray = coordinateBuilder.getIntArray();
                    if (isShipInWay(playingBoard, coordinateArray)) {
                        System.out.println("Error! You placed it too close to another one. Try again:\n");
                        break;
                    }
                    coordinatesValid = true;
                }
                case WRONG_LENGTH -> System.out.println(ValidationResult.WRONG_LENGTH.getMessage(name));
                case WRONG_POSITION -> System.out.println(ValidationResult.WRONG_POSITION.getMessage());
            }

        }
    }

    private boolean isShipInWay(PlayingBoard playingBoard, int[][] coordinates) {
        String[][] matrix = playingBoard.getMatrix();
        for (int[] coordinate : coordinates) {
            {
                int row = coordinate[0];
                int col = coordinate[1];

                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (i < matrix.length && j < matrix[0].length && matrix[i][j].equals(Symbol.SHIP_SYMBOL.getSymbol())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

        public void placeOnBoard (PlayingBoard playingBoard){
            for (String coordinate : statusIsHealthy.keySet()) {
                if (statusIsHealthy.get(coordinate)) {
                    playingBoard.setCoordinates(coordinate, Symbol.SHIP_SYMBOL.getSymbol());
                } else {
                    playingBoard.setCoordinates(coordinate, Symbol.HIT_SYMBOL.getSymbol());
                }
            }
        }
    }
