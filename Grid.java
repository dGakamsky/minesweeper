import java.util.Random;
import java.util.Scanner;

public class Grid {
    Tile[][] tileGrid;
    int xDimension;
    int yDimension;
    int maxMines;
    int defusedMines;
    int maxFlags;


    boolean gameRunning;

    Grid(int x, int y, int mines){
        this.xDimension = x;
        System.out.println(this.xDimension);
        this.yDimension = y;
        System.out.println(this.yDimension);
        this.maxMines = mines;

    }

    void gameOver(){
        this.gameRunning = false;
        printGrid();
        System.out.println("game over");
        gameReset();
    }
    
    void gameWin(){
        this.gameRunning = false;
        printGrid();
        System.out.println("Congratulations, you win!");
        gameReset();
    }

    void gameReset(){
        System.out.println("Play again?");
        System.out.println("Y/N");
        Scanner scan = new Scanner(System.in);
        String in = scan.next().toLowerCase();
        switch(in){
            case "y","yes" -> startGame();
            case "n", "no" -> endGame();
        }
    }

    void generaTileGrid (int x, int y){
        this.tileGrid = new Tile[x][y];

        for (int i = 0 ; i < x ; i++) {
            for (int j = 0; j < y ; j++){
                this.tileGrid[i][j] = new Tile( i, j);

            }
        }

        populateMines(x, y);

        for (int i = 0 ; i < x ; i++) {
            for (int j = 0; j < y ; j++){
                if (!this.tileGrid[i][j].mine) {
                    this.tileGrid[i][j].minesAdjacent = getMines(i, j);
                }
            }
        }
    }

    int getMines(int x, int y){
        int numberOfAdjacentMines = 0;
        int[] xRange = {x - 1, x, x + 1};
        int[] yRange = {y - 1, y, y + 1};
        for (int xIterator : xRange){
            if (xIterator < this.xDimension && xIterator >= 0) {
                for (int yIterator : yRange) {
                    if (yIterator < this.yDimension && yIterator >= 0) {
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
        if (this.tileGrid[xCoord][yCoord].hidden && !this.tileGrid[xCoord][yCoord].flag) {
            this.tileGrid[xCoord][yCoord].hidden = false;
            if (this.tileGrid[xCoord][yCoord].mine) {
                this.gameOver();
            }
            System.out.println(this.tileGrid[xCoord][yCoord].minesAdjacent);
            if (this.tileGrid[xCoord][yCoord].minesAdjacent == 0) {
                int[] xRange = {xCoord - 1, xCoord, xCoord + 1};
                int[] yRange = {yCoord - 1, yCoord, yCoord + 1};
                for (int xIterator : xRange) {
                    if (xIterator<xDimension && xIterator>=0) {
                        for (int yIterator : yRange) {
                            if (yIterator < yDimension && yIterator >= 0) {
                                if (!this.tileGrid[xIterator][yIterator].mine)
                                    revealTile(xIterator, yIterator);
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
            if (!this.tileGrid[randomX][randomY].mine){
                this.tileGrid[randomX][randomY].mine = true;
                currentMines++;
            }
        }
    }

    void update(){
        while (this.gameRunning) {
            promptPlayer();
            printGrid();
        }
    }

    void startGame(){
        this.gameRunning = true;
        this.defusedMines = 0;
        this.maxFlags = this.maxMines;
        generaTileGrid(this.xDimension, this.yDimension);
        System.out.println("Welcome to minesweeper");
        printGrid();
        update();
    }

    void endGame(){
        System.out.println("Thanks for playing!");
        System.exit(0);
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
            case 1:
                if (this.tileGrid[x-1][y-1].flag){
                    this.maxFlags++;
                    this.tileGrid[x-1][y-1].flagTile();
                    if (this.tileGrid[x - 1][y - 1].mine) {
                        defusedMines--;
                    }
                } else {
                    if (this.maxFlags == 0) {
                        System.out.println("you have no more flags left to use");
                    } else if (this.tileGrid[x - 1][y - 1].hidden) {
                        this.tileGrid[x - 1][y - 1].flagTile();
                        if (this.tileGrid[x - 1][y - 1].flag) {
                            this.maxFlags--;
                            if (this.tileGrid[x - 1][y - 1].mine) {
                                defusedMines++;
                                if (defusedMines == maxMines) {
                                    gameWin();
                                }
                            }

                        }                    }
                }
                break;
            case 2:
                revealTile(x-1, y-1);
                System.out.println("tiles revealed");
                break;
        }
    }

    void printGrid(){
        Color c = new Color();
        int x = this.xDimension;
        int y = this.yDimension;
        System.out.println("This is the grid");
        System.out.println("You have " + this.maxFlags + " flags left");
        System.out.println(c.WHITE + "[-------------------------------------------------------]" + c.RESET);
        for (int i = 0 ; i < x ; i++) {
            System.out.println("");
            for (int j = 0; j < y ; j++){
                if (tileGrid[i][j].hidden && gameRunning && !tileGrid[i][j].flag) {
                    System.out.print(c.WHITE + "[ ~ ]"+c.RESET);
                } else if (tileGrid[i][j].flag) {
                    System.out.print(c.GREEN+"[ F ]"+c.RESET);
                }else if (tileGrid[i][j].mine){
                    System.out.print(c.RED+"[ X ]"+c.RESET);
                } else if (tileGrid[i][j].minesAdjacent == 0) {
                    System.out.print(c.BLACK+"[ " + tileGrid[i][j].minesAdjacent + " ]"+c.RESET);
                } else {
                    System.out.print(c.CYAN+"[ " + tileGrid[i][j].minesAdjacent + " ]"+c.RESET);
                }
            }
        }
        System.out.println("");
        System.out.println(c.WHITE + "[-------------------------------------------------------]" + c.RESET);
    }




}
