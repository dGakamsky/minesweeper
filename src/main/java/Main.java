import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int[] inputs = getInputs();
        Grid mineSweeper = new Grid(inputs[0], inputs[1], inputs[2]);
        mineSweeper.startGame();
    }

    static int[] getInputs(){
        System.out.println("Welcome to minesweeper");
        int x = prompt_xSize();
        int y = prompt_ySize();
        int mines = prompt_getMines(x,y);
        return new int[]{x, y, mines};
    }

    static int prompt_xSize(){
        System.out.println("Please enter the X dimensions of the desired game field");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println(Message.errorMsg);
        }
        int x = scan.nextInt();
        if (x<=3 || x>30){
            System.out.println("Are you sure you would like this size? This may have an impact on your game experience");
            System.out.println(Message.confirmMsg);
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println(Message.errorMsg);
            }
            x = scan.nextInt();
        }
        return x;
    }

    static int prompt_ySize(){
        System.out.println("Please enter the Y dimensions of the desired game field");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println(Message.errorMsg);
        }
        int y = scan.nextInt();
        if (y<=3 || y>30){
            System.out.println("Are you sure you would like this size? This may have an impact on your game experience");
            System.out.println(Message.confirmMsg);
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println(Message.errorMsg);
            }
            y = scan.nextInt();
        }
        return y;
    }

    static int prompt_getMines(int x, int y){
        System.out.println("Please enter the number of mines you would like in game");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println(Message.errorMsg);
        }
        int mines = scan.nextInt();
        while ((mines>x*y)){
            System.out.println("the number of mines must be able to be placed within the created game field");
            System.out.println("please enter the number of mines");
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println(Message.errorMsg);
            }
            mines = scan.nextInt();
        }
        if ((mines>(x*y/2))){
            System.out.println("That is a very high number of bombs to place, are you sure about that?");
            System.out.println(Message.confirmMsg);
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println(Message.errorMsg);
            }
            mines = scan.nextInt();
        }
        return mines;
    }
}