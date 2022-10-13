public class Tile {
    private int minesAdjacent;
    private boolean mine;
    private boolean hidden;
    private boolean flag;


    public Tile(){
        this.setFlag(false);
        this.setHidden(true);
        this.setMine(false);

    }

    void setFlag(boolean flag){
        this.flag = flag;
    }
    boolean getFlag(){
        return this.flag;
    }
    void setMine(boolean mine){
        this.mine = mine;
    }
    boolean getMine(){
        return this.mine;
    }
    boolean getHidden(){
        return this.hidden;
    }
    void setHidden(boolean hidden){
        this.hidden = hidden;
    }
    int getMinesAdjacent(){
        return this.minesAdjacent;
    }

    void setMinesAdjacent(int minesAdjacent){
        this.minesAdjacent = minesAdjacent;
    }



}



