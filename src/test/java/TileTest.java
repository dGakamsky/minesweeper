import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TileTest {

    Tile testTile;


    @Test
    public void testGetFlag(){
        testTile = new Tile();
        Assertions.assertFalse(testTile.getFlag(), "the flag has not been gotten correctly");
    }

    @Test
    public void testSetFlag(){
        testTile = new Tile();
        testTile.setFlag(true);
        Assertions.assertTrue(testTile.getFlag(), "the flag has not been set correctly");
    }

    @Test
    public void testGetMine(){
        testTile = new Tile();
        Assertions.assertFalse(testTile.getMine(), "the mine has not been gotten correctly");
    }

    @Test
    public void testSetMine(){
        testTile = new Tile();
        testTile.setMine(true);
        Assertions.assertTrue(testTile.getMine(), "the mine has not been set correctly");
    }

    @Test
    public void testGetHidden(){
        testTile = new Tile();
        Assertions.assertTrue(testTile.getHidden(), "the 'hidden' property has not been gotten correctly");
    }

    @Test
    public void testSetHidden(){
        testTile = new Tile();
        testTile.setHidden(false);
        Assertions.assertFalse(testTile.getHidden(), "the 'hidden' property has not been set correctly");
    }

    @Test
    public void testGetMinesAdjacent(){
        testTile = new Tile();
        Assertions.assertEquals(0, testTile.getMinesAdjacent(), "the mines adjacent has not been gotten correctly");
    }

    @Test
    public void testSetMinesAdjacent(){
        testTile = new Tile();
        testTile.setMinesAdjacent(5);
        Assertions.assertEquals(5,testTile.getMinesAdjacent(),"mines adjacent have not been set correctly");
    }


}
