import java.util.Random;

public class Grid {
    Tile[][] tileGrid;
    int xDimension;
    int yDimension;
    int maxMines;

    Grid(int x, int y, int mines){
        this.xDimension = x;
        this.yDimension = y;
        this.maxMines = mines;
        this.tileGrid = generaTileGrid(this.xDimension, this.yDimension);
    }

    void gameOver(){
        /* TODO: ends the game */
    }

    Tile[][] generaTileGrid (int x, int y){
        tileGrid = new Tile[x][y];

        for (int i = 0 ; i < x ; i++) {
            for (int j = 0; j < y ; j++){
                tileGrid[i][j] = new Tile( x, y);

            }
        }
        populateMines(x, y);
        for (int i = 0 ; i < x ; i++) {
            for (int j = 0; j < y ; j++){
                tileGrid[i][j].minesAdjacent = getMines(x, y) ;
            }
        }
        return tileGrid;
    }

    int getMines(int x, int y){
        int numberOfAdjacentMines = 0;
        int[] xRange = {x - 1, x, x + 1};
        int[] yRange = {y-1, y, y+1};
        for (int xIterator : xRange){
            if (xIterator < xDimension && xIterator>0) {
                for (int yIterator : yRange) {
                    if (yIterator < yDimension && yIterator > 0) {
                        if (this.tileGrid[xIterator][yIterator].mine) {
                            numberOfAdjacentMines++;
                        }
                    }
                }
            }
        }
        return numberOfAdjacentMines;
    }

    void revealTile(int x, int y){
        if (this.tileGrid[x][y].hidden) {
            this.tileGrid[x][y].hidden = false;
            /* TODO : add visual change to it when revealed*/
            if (this.tileGrid[x][y].mine) {
                this.gameOver();
            }
            if (this.tileGrid[x][y].minesAdjacent == 0) {
                int[] xRange = {x - 1, x, x + 1};
                int[] yRange = {y - 1, y, y + 1};
                for (int xIterator : xRange) {
                    if (xIterator<xDimension && xIterator>0) {
                        for (int yIterator : yRange) {
                            if (yIterator < yDimension && yIterator > 0) {
                                if (this.tileGrid[xIterator][yIterator].mine) {
                                    revealTile(xIterator, yIterator);
                                }
                            }
                        }
                    }
                }

            }
        }

    }

    void populateMines(int x, int y){
        int currentMines = 0;
        Random rand = new Random();
        while (currentMines < this.maxMines){
            int randomX = rand.nextInt(x);
            int randomY = rand.nextInt(y);
            if (tileGrid[randomX][randomY].mine == false){
                tileGrid[randomX][randomY].mine = true;
                currentMines++;
            }
        }
    }

    void printGrid(){
        int x = this.xDimension;
        int y = this.yDimension;
        System.out.println("This is the grid");
        System.out.println("[----------------------------------------------------------]");
        for (int i = 0 ; i < x ; i++) {
            System.out.println("");
            for (int j = 0; j < y ; j++){
                if (tileGrid[i][j].mine){
                    System.out.print("[ 1 ]");
                } else {
                    System.out.print("[ 0 ]");
                }
            }
        }
        System.out.println("");
        System.out.println("[----------------------------------------------------------]");
    }


}
