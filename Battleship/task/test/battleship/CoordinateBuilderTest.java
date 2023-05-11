package battleship;

import org.junit.Assert;
import org.junit.Test;

public class CoordinateBuilderTest {

    @Test
    public void buildReverseCoordinates() {
        CoordinateBuilder coordinateBuilder = new CoordinateBuilder(new int[]{5, 2, 0, 2});
        String[] expected = {"A3", "B3", "C3", "D3", "E3", "F3"};
        String[] result = coordinateBuilder.getCoordinateArray();
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void buildHorizontalCoordinates() {
        CoordinateBuilder coordinateBuilder = new CoordinateBuilder(new int[]{3, 1, 3, 6});
        String[] expected = {"D2", "D3", "D4", "D5", "D6", "D7"};
        String[] result = coordinateBuilder.getCoordinateArray();
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void buildVerticalCoordinates() {
        CoordinateBuilder coordinateBuilder = new CoordinateBuilder(new int[]{0, 2, 5, 2});
        String[] expected = {"A3", "B3", "C3", "D3", "E3", "F3"};
        String[] result = coordinateBuilder.getCoordinateArray();
        Assert.assertArrayEquals(expected, result);

    }



}