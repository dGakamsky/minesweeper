public class Tile {
    int mineAdjacent;

    boolean mine;
    boolean hidden;
    boolean flag;
    int[] position = new int[2];

    Tile[] adjacent;


    Tile(){
        this.flag = false;
        this.hidden = false;
    }

    void flag(){
        if (this.hidden){
            this.flag = true;
        }
    }

    void setPosition (int x, int y){
        this.position[0] = x;
        this.position[1] = y;
    }

    void getMines(){
        int mines = 0;
        for (Tile adjacentTile : adjacent){
            if (adjacentTile.mine){
                mines++;
            }
        }
        this.mineAdjacent = mines;
    }


}
