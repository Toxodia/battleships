package battleship;


import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class ShipTest {
    @Test
    public void isHitShipPossible() {
        Ship ship = new Ship("Destroyer", new String[]{"A1", "B1", "C1"});
        Assert.assertTrue(ship.checkHit("B1"));
    }

    @Test
    public void missShip() {
        Ship ship = new Ship("Destroyer", new String[]{"A1", "B1", "C1"});
        Assert.assertFalse(ship.checkHit("G4"));
    }

    @Test
    public void getShipHP(){
        Ship ship = new Ship("Destroyer", new String[]{"A1", "B1", "C1"});
        Assert.assertEquals(3,ship.getHp());
    }

    @Test
    public void hitAShip(){
        Ship ship = new Ship("Destroyer", new String[]{"A1", "B1", "C1"});
        Assert.assertTrue(ship.hitShip("A1"));
        Assert.assertFalse(ship.hitShip("D5"));
    }

    @Test
    public void getShipHPAfterHit(){
        Ship ship = new Ship("Destroyer", new String[]{"A1", "B1", "C1"});
        Assert.assertEquals(3,ship.getHp());
        ship.hitShip("A1");
        Assert.assertEquals(2,ship.getHp());
    }


    private Method getCheckShipSizeMethod() throws NoSuchMethodException {
        Method method = Ship.class.getDeclaredMethod("checkShipSize");
        method.setAccessible(true);
        return method;
    }
    //TODO
    @Test
    public void checkShipSizeTest() throws Exception{
        Ship ship = new Ship("Destroyer", 5);

    }

    //TODO
    @Test
    public void convertCoordinatesToIntTest(){
        Ship ship = new Ship("Destroyer", 5);

    }
}