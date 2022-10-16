import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {
    int testX = 5;
    int testY = 5;
    int testMines = 10;

    Grid testGrid = new Grid(testX, testY, testMines);

    @Test
    public void testX(){
        Assertions.assertEquals(testX, testGrid.getxDimension(),"x dimension was not set correctly");
    }

    @Test
    public void testY(){
        Assertions.assertEquals(testY, testGrid.getyDimension(),"y dimension was not set correctly");
    }

    @Test
    public void testMines(){
        Assertions.assertEquals(testMines,testGrid.getMaxMines(),"Mines were not set correctly");
    }

    @Test
    public void testFlags(){
        Assertions.assertEquals(testMines, testGrid.getMaxFlags(),"Flags were not set correctly");
    }
    @Test
    public void testDefusedMines(){
        Assertions.assertEquals(0, testGrid.getDefusedMines(), "the defused mines were not set correctly");
    }


    @Test
    public void testGridTiles(){
        testGrid.generaTileGrid(testX,testY);
    }

    @Test
    public void testInBounds(){
        Assertions.assertTrue(testGrid.inBounds(0,0),"inbounds does not recognize in bounds value");
        Assertions.assertFalse(testGrid.inBounds(10000,10000), "inbounds does not recognize out of bounds value");
    }

    @Test
    public void testTileGrid(){
        Grid mineGrid = new Grid(5,5,25);
        Grid zeroMineGrid = new Grid(5,5,0);
        mineGrid.generaTileGrid(5,5);
        zeroMineGrid.generaTileGrid(5,5);
        for (int i = 0; i < 5; i++){
            for (int j = 0; j <5; j++){
                Assertions.assertTrue(mineGrid.tileGrid[i][j].getMine(),"mine assignment does not work properly");
            }
        }
        for (int i = 0; i < 5; i++){
            for (int j = 0; j <5; j++){
                Assertions.assertFalse(zeroMineGrid.tileGrid[i][j].getMine(),"zero mine assignment does not work properly");
            }
        }
        for (int i = 0; i < 5; i++){
            for (int j = 0; j <5; j++){
                Assertions.assertTrue(mineGrid.tileGrid[i][j].getHidden(),"mine assignment does not work properly");
            }
        }
    }

    @Test
    void testFlagTile(){
        Grid flagGrid = new Grid(1,2,2);
        flagGrid.generaTileGrid(1,2);
        Assertions.assertEquals(2, flagGrid.getMaxFlags(),"flags were not assigned");
        flagGrid.flagTile(0,0);
        Assertions.assertTrue(flagGrid.tileGrid[0][0].getFlag(),"flag was not placed");
        Assertions.assertEquals(1, flagGrid.getMaxFlags(),"flag was not used");
        Assertions.assertEquals(1,flagGrid.getDefusedMines(),"mine was not diffused");
        flagGrid.flagTile(0,0);
        Assertions.assertFalse(flagGrid.tileGrid[0][0].getFlag(),"flag was not removed");
        Assertions.assertEquals(2, flagGrid.getMaxFlags(),"flag was not retrieved");
        Assertions.assertEquals(0,flagGrid.getDefusedMines(),"mine was not un-diffused");

    }

    @Test
    void testRevealTile(){
        Grid revealGrid = new Grid (5,5,0);
        revealGrid.generaTileGrid(5,5);
        Assertions.assertTrue(revealGrid.tileGrid[0][0].getHidden(),"tile was not instantiated as hidden");
        revealGrid.revealTile(0,0);
        Assertions.assertFalse(revealGrid.tileGrid[0][0].getHidden(),"tile was not revealed");
        for (int i = 0; i<5; i++){
            for (int j = 0; j<5; j++){
                Assertions.assertFalse(revealGrid.tileGrid[i][j].getHidden(),"zero cascade did not occur");
            }
        }
    }

    @Test
    void testPopulateMines(){
        Grid mineGrid = new Grid (5,5,25);
        mineGrid.generaTileGrid(5,5);
        for (int i = 0; i<5; i++) {
            for (int j = 0; j < 5; j++) {
                Assertions.assertTrue(mineGrid.tileGrid[i][j].getMine(), "mines were not set propertly");

            }
        }

    }


}
