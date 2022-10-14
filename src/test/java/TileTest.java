import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TileTest {

    Tile testTile = new Tile();

    @Test
    void testGetFlag(){
        Assertions.assertFalse(testTile.getFlag(), "the flag has not been gotten correctly");
    }

    @Test
    void testSetFlag(){
        testTile.setFlag(true);
        Assertions.assertTrue(testTile.getFlag(), "the flag has not been set correctly");
    }

    @Test
    void testGetMine(){
        Assertions.assertFalse(testTile.getMine(), "the mine has not been gotten correctly");
    }

    @Test
    void testSetMine(){
        testTile.setMine(true);
        Assertions.assertTrue(testTile.getMine(), "the mine has not been set correctly");
    }

    @Test
    void testGetHidden(){
        Assertions.assertTrue(testTile.getHidden(), "the 'hidden' property has not been gotten correctly");
    }

    @Test
    void testSetHidden(){
        testTile.setHidden(false);
        Assertions.assertFalse(testTile.getHidden(), "the 'hidden' property has not been set correctly");
    }

    @Test
    void testGetMinesAdjacent(){
        Assertions.assertEquals(0, testTile.getMinesAdjacent(), "the mines adjacent has not been gotten correctly");
    }

    @Test
    void testSetMinesAdjacent(){
        testTile.setMinesAdjacent(5);
        Assertions.assertEquals(5,testTile.getMinesAdjacent(),"mines adjacent have not been set correctly");
    }


}
