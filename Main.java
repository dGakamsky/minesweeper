import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] inputs = getInputs();
        Grid mineSweeper = new Grid(inputs[0], inputs[1], inputs[2]);
        mineSweeper.startGame();
    }

    static int[] getInputs(){
        int[] inputs = new int[3];
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to minesweeper");
        System.out.println("Please enter the X dimensions of the desired game field");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Please input a number");
        }
        inputs[0] = scan.nextInt();
        if (inputs[0]<=3 || inputs[0]>30){
            System.out.println("Are you sure you would like this size? This may have an impact on your game experience");
            System.out.println("please enter the number again to confirm");
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println("Please input a number");
            }
            inputs[0] = scan.nextInt();
        }
        System.out.println("Please enter the Y dimensions of the desired game field");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Please input a number");
        }
        inputs[1] = scan.nextInt();
        if (inputs[1]<=3 || inputs[1]>30){
            System.out.println("Are you sure you would like this size? This may have an impact on your game experience");
            System.out.println("please enter the number again to confirm");
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println("Please input a number");
            }
            inputs[1] = scan.nextInt();
        }
        System.out.println("Please enter the number of mines you would like in game");
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("Please input a number");
        }
        inputs[2] = scan.nextInt();
        while ((inputs[2]>inputs[1]*inputs[0])){
                System.out.println("the number of mines must be able to be placed within the created game field");
                System.out.println("please enter the number of mines");
                while (!scan.hasNextInt()) {
                    scan.next();
                    System.out.println("Please input a number");
                }
                inputs[2] = scan.nextInt();
        }

        if ((inputs[2]>(inputs[1]*inputs[0]/2))){
            System.out.println("That is a very high number of bombs to place, are you sure about that?");
            System.out.println("please enter the number of mines again to confirm");
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.println("Please input a number");
            }
            inputs[2] = scan.nextInt();
        }

        return inputs;
    }
}