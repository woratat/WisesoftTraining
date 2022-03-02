package train01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int odd = 0;
        int even = 0;

        try (Scanner input = new Scanner(System.in)) { //
            while (true) {
                System.out.print("Enter odd number for Diamond shape : ");
                odd = input.nextInt();

                if (odd % 2 == 0) {
                    System.out.println("Please enter odd number only.");
                } else {
                    break;
                }
            }

            while (true) {
                System.out.print("Enter even number for Butterfly shape : ");
                even = input.nextInt();

                if (even % 2 != 0) {
                    System.out.println("Please enter even number only.");
                } else {
                    break;
                }
            }

            DiamondShape diamondShape = new DiamondShape(odd);
            diamondShape.printDiamond();
            ButterflyShape butterflyShape = new ButterflyShape(even);
            butterflyShape.printButterfly();

        } catch (InputMismatchException e) {
            System.out.println("Only number, please.");
        }
    }
}