package battleship;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        CoordinateBuilder coordinateBuilder = new CoordinateBuilder(new int[]{5,2,0,2});
        System.out.println(Arrays.deepToString(coordinateBuilder.getIntArray()));
    }
}
