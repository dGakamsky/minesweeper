public class Grid {
    Tile[][] tileGrid;
    int xDimension;
    int yDimension;

    Grid(int x, int y){
        this.xDimension = x;
        this.yDimension = y;
        this.tileGrid = generaTileGrid(this.xDimension, this.yDimension);
    }

    void gameOver(){
        /* TODO: ends the game */
    }

    Tile[][] generaTileGrid (int x, int y){
        tileGrid = new Tile[x][y];
        for (int i = 0 ; i < x ; i++) {
            for (int j = 0; j < y ; j++){
                tileGrid[i][j] = new Tile(this, x, y);

            }
        }
        return tileGrid;
    }


}
