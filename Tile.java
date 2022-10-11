public class Tile {
    int mineAdjacent;
    Grid grid;
    boolean mine;
    boolean hidden;
    boolean flag;
    int[] position = new int[2];

    public Tile(Grid grid, int x, int y){

        this.flag = false;
        this.hidden = false;
        this.position[0] = x;
        this.position[1] = y;

    }


    void flag(){
        if (this.hidden){
            this.flag = true;
        }
    }

    void getMines(){
        int mines = 0;
        int x = this.position[0];
        int[] xRange = {x - 1, x, x + 1};
        int y = this.position[1];
        int[] yRange = {y-1, y, y+1};
        for (int xIterator : xRange){
            for (int yIterator : yRange){
                if (this.grid.tileGrid[xIterator][yIterator].mine){
                    mines++;
                }
            }
        }
        this.mineAdjacent = mines;
    }

    void flagTile(){
        if (this.hidden){
            this.flag = true;
            /* TODO : add visual change to it when flagged */
        }

    }

    void revealTile(){
        if (this.hidden){
            this.hidden = false;
            /* TODO : add visual change to it when revealed*/
            if (this.mine){
                this.grid.gameOver();
            }
            if (this.mineAdjacent == 0){
                int x = this.position[0];
                int[] xRange = {x - 1, x, x + 1};
                int y = this.position[1];
                int[] yRange = {y-1, y, y+1};
                for (int xIterator : xRange){
                    for (int yIterator : yRange){
                        this.grid.tileGrid[xIterator][yIterator].revealTile();
                        }
                    }
                }
            }
        }
}



