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
                for (int yIterator : yRange) {
                    if (inBounds(xIterator,yIterator)){
                        if (this.tileGrid[xIterator][yIterator].mine) {
                            numberOfAdjacentMines++;
                        }
                    }
                }
        }
        return numberOfAdjacentMines;
    }

    boolean inBounds (int x, int y){
        if (x<this.xDimension && x>=0 && y < this.yDimension && y>= 0){
            return true;
        } else {
            return false;
        }
    }

    void revealTile(int xCoord, int yCoord){
        if (this.tileGrid[xCoord][yCoord].hidden && !this.tileGrid[xCoord][yCoord].flag) {
            this.tileGrid[xCoord][yCoord].hidden = false;
            if (this.tileGrid[xCoord][yCoord].mine) {
                this.gameOver();
            }
            if (this.tileGrid[xCoord][yCoord].minesAdjacent == 0) {
                int[] xRange = {xCoord - 1, xCoord, xCoord + 1};
                int[] yRange = {yCoord - 1, yCoord, yCoord + 1};
                for (int xIterator : xRange) {
                        for (int yIterator : yRange) {
                            if (inBounds(xIterator,yIterator)) {
                                if (!this.tileGrid[xIterator][yIterator].mine)
                                    revealTile(xIterator, yIterator);
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



    void promptPlayer(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the row of the tile you would like to select");
        while (!scan.hasNextInt()) {
                scan.next();
                System.out.println("Please input a number");
        }
        int x = scan.nextInt();
        if (x==0 || x > xDimension){
            while (x==0 || x > xDimension) {
                System.out.println("Please enter a number within bounds (bounds are: 1 - " + xDimension);
                System.out.println("Enter the row of the tile you would like to select");
                while (!scan.hasNextInt()) {
                    scan.next();
                    System.out.println("Please input a number");
                }
                x = scan.nextInt();
            }
        }
        System.out.println("Enter the column of the tile you would like to select");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Please input a number");
        }
        int y = scan.nextInt();
        if (y==0 || y > yDimension){
            while (y==0 || y > yDimension) {
                System.out.println("Please enter a number within bounds (bounds are: 1 - " + yDimension);
                System.out.println("Enter the column of the tile you would like to select");
                while (!scan.hasNextInt()) {
                    scan.next();
                    System.out.println("Please input a number");
                }
                y = scan.nextInt();
            }
        }
        System.out.println("would you like to flag the tile (F) or reveal it (R)");
        while (!scan.hasNext()) {
            scan.next();
            System.out.println("Please input a number");
        }
        String option = scan.next().toLowerCase();
        while (!(option.equals("f") || option.equals("r"))){
            System.out.println("Please enter one of the two options");
            System.out.println("would you like to flag the tile (F) or reveal it (R)");
                while (!scan.hasNext()) {
                    scan.next();
                    System.out.println("Please input a number");
                }
                option = scan.next();

        }
        switch (option){
            case "f":
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
            case "r":
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
        System.out.print(c.purple + "[------");
        for (int j = 0; j < y ; j++){
            System.out.print("-------");
        }
        System.out.print("------]");
        System.out.print("\n"+c.reset);
        System.out.print("       ");
        for (int j = 0; j < y ; j++){
            if (j > 8){
                System.out.print("["+c.purple+" 0" + (j + 1) + " "+c.reset+"]");
            } else {
                System.out.print("["+c.purple+" 00" + (j + 1) + " "+c.reset+"]");
            }
        }
        System.out.print("\n");
        for (int i = 0 ; i < x ; i++) {
            if (i > 8){
                System.out.print("["+c.purple+" 0" + (i + 1) + " "+c.reset+"]");
            } else {
                System.out.print("["+c.purple+" 00" + (i + 1) + " "+c.reset+"]");
            }
            for (int j = 0; j < y ; j++){
                if (tileGrid[i][j].hidden && gameRunning && !tileGrid[i][j].flag) {
                    System.out.print(c.white + "[  ?  ]"+c.reset);
                } else if ((tileGrid[i][j].flag && gameRunning) || (tileGrid[i][j].flag && tileGrid[i][j].mine)) {
                    System.out.print(c.green+"[  F  ]"+c.reset);
                } else if (tileGrid[i][j].flag && !tileGrid[i][j].mine ){
                    System.out.print(c.red+"[  F  ]"+c.reset);
                } else if (tileGrid[i][j].mine){
                    System.out.print(c.red+"[  X  ]"+c.reset);
                } else if (tileGrid[i][j].minesAdjacent == 0) {
                    System.out.print(c.black+"[  " + tileGrid[i][j].minesAdjacent + "  ]"+c.reset);
                } else {
                    System.out.print(c.cyan+"[  " + tileGrid[i][j].minesAdjacent + "  ]"+c.reset);
                }
            }
            System.out.print("\n");
        }
        System.out.print(c.purple + "[------");
        for (int j = 0; j < y ; j++){
            System.out.print("-------");
        }
        System.out.print("------]");
        System.out.print("\n"+c.reset);
    }




}
