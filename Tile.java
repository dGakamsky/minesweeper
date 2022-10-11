public class Tile {
    int minesAdjacent;

    boolean mine;
    boolean hidden;
    boolean flag;
    int[] position = new int[2];

    public Tile( int x, int y){

        this.flag = false;
        this.hidden = true;
        this.position[0] = x;
        this.position[1] = y;

    }


    void flagTile(){
        if (this.hidden && !this.flag){
            this.flag = true;
        } else if (this.flag){
            this.flag = false;
        }
    }

}



