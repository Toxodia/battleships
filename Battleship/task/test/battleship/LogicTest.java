package battleship;

import org.junit.Assert;
import org.junit.Test;

public class LogicTest {
    @Test
    public void putShipOnBoard(){
        PlayingBoard playingBoard = new PlayingBoard();
        Ship ship = new Ship("Destroyer", new String[]{"A1", "B1", "C1"});
        ship.placeOnBoard(playingBoard);
        Assert.assertEquals("O",playingBoard.getCoordinates("A1"));
        Assert.assertEquals("O",playingBoard.getCoordinates("B1"));
        Assert.assertEquals("O",playingBoard.getCoordinates("C1"));
    }
}
