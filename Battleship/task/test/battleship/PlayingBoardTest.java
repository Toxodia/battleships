package battleship;


import org.junit.Assert;
import org.junit.Test;

public class PlayingBoardTest {
    @Test
    public void getRightCoordinates(){
        PlayingBoard playingBoard = new PlayingBoard();
        playingBoard.setCoordinates("F1","X");
        Assert.assertEquals("X",playingBoard.getMatrix()[5][0]);
    }

    @Test
    public void getValueOfBoardCell(){
        PlayingBoard playingBoard = new PlayingBoard();
        playingBoard.setCoordinates("D3","X");
        Assert.assertEquals("X",playingBoard.getCoordinates("D3"));
        Assert.assertNotEquals("X",playingBoard.getCoordinates("A1"));
    }
}