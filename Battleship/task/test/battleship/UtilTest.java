package battleship;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class UtilTest {
    @Test
    public void testCoordinateParserTest() {
        String input = "F1 F5";
        int[] expected = {5, 0, 5, 4};
        int[] result = Util.queryCoordinates(new ByteArrayInputStream(input.getBytes()));
        Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void coordinatesAreInARow() {
        int[] input = {5, 0, 5, 4};
        int length = 5;
        Assert.assertEquals(ValidationResult.SUCCESS, Util.areCoordinatesValid(input, length));
    }

    @Test
    public void coordinatesAreInAColumn() {
        int[] input = {0, 5, 4, 5};
        int length = 5;
        Assert.assertEquals(ValidationResult.SUCCESS, Util.areCoordinatesValid(input, length));
    }

    @Test
    public void coordinatesAreNOTInALine() {
        int[] input = {0, 2, 4, 5};
        int length = 5;
        Assert.assertEquals(ValidationResult.WRONG_POSITION, Util.areCoordinatesValid(input, length));
    }

    @Test
    public void coordinatesAreToShort() {
        int[] input = {5, 0, 5, 4};
        int length = 6;
        Assert.assertEquals(ValidationResult.WRONG_LENGTH, Util.areCoordinatesValid(input, length));
    }

    @Test
    public void coordinatesAreToLong() {
        int[] input = {5, 0, 5, 4};
        int length = 4;
        Assert.assertEquals(ValidationResult.WRONG_LENGTH, Util.areCoordinatesValid(input, length));
    }

    @Test
    public void coordinatesAreInvalid() {
        int[] input = {12, 0, 12, 4};
        int length = 4;
        Assert.assertEquals(ValidationResult.WRONG_OOB, Util.areCoordinatesValid(input, length));
    }

    @Test
    public void testTest() {
        int[] input = {1, 2, 1,};
        int length = 2;
        Assert.assertEquals(ValidationResult.WRONG_OVERALL, Util.areCoordinatesValid(input, length));
    }
}