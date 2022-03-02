package train02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int input;

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                try {
                    System.out.print("Enter number between 1 and 50 or 0 to exit: ");
                    input = scanner.nextInt();

                    if (input < 0 || input > 50) {
                        System.out.println("Please enter only number between 1 and 50 only.");
                    } else {
                        if (input == 0) {
                            break;
                        } else if (input % 2 == 0) {
                            ButterflyShape butterflyShape = new ButterflyShape(input);
                            butterflyShape.printButterfly();
                        } else {
                            DiamondShape diamondShape = new DiamondShape(input);
                            diamondShape.printDiamond();
                        }
                    }
                } catch (InputMismatchException exception) {
                    System.out.println("Integers only, please.");
                }
            }
            while (true);
        }
    }
}