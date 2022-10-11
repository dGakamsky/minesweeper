import java.util.Random;
import java.util.Scanner;

public class Grid {
    Tile[][] tileGrid;
    int xDimension;
    int yDimension;
    int maxMines;
    boolean gameRunning;

    Grid(int x, int y, int mines){
        this.xDimension = x;
        this.yDimension = y;
        this.maxMines = mines;
        this.tileGrid = generaTileGrid(this.xDimension, this.yDimension);
    }

    void gameOver(){
        this.gameRunning = false;
        this.printGrid();
        System.out.println("game over");
        System.exit(0);
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

    void revealTile(int xCoord, int yCoord){
        int x = xCoord-1;
        int y = yCoord-1;
        if (this.tileGrid[x][y].hidden) {
            this.tileGrid[x][y].hidden = false;
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
            if (!tileGrid[randomX][randomY].mine){
                tileGrid[randomX][randomY].mine = true;
                currentMines++;
            }
        }
    }

    void update(){
        while (gameRunning) {
            promptPlayer();
            printGrid();
        }
    }

    void startGame(){
        this.gameRunning = true;
        System.out.println("Welcome to minesweeper");
        printGrid();
        update();
    }

    void promptPlayer(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the row of the tile you would like to select");
        int x = scan.nextInt();
        System.out.println("Enter the column of the tile you would like to select");
        int y = scan.nextInt();
        System.out.println("would you like to flag the tile (1) or reveal it (2)");
        int option = scan.nextInt();
        switch (option){
            case 1 -> this.tileGrid[x-1][y-1].flag();
            case 2 -> revealTile(x, y);
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
                if (tileGrid[i][j].hidden && gameRunning && !tileGrid[i][j].flag) {
                    System.out.print("[ ~ ]");
                } else if (tileGrid[i][j].flag) {
                    System.out.print("[ F ]");
                }else if (tileGrid[i][j].mine){
                    System.out.print("[ X ]");
                } else {
                    System.out.print("[ " + getMines( i, j) + " ]");
                }
            }
        }
        System.out.println("");
        System.out.println("[----------------------------------------------------------]");
    }




}
