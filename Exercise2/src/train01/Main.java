package train01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int odd = 0;
        int even = 0;

        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter odd number = ");
                odd = input.nextInt();

                System.out.print("Enter even number = ");
                even = input.nextInt();

                if (odd % 2 == 0) {
                    System.out.println("Please enter odd number only.");
                } else if (even % 2 != 0) {
                    System.out.println("Please enter even number only.");
                } else {
                    DiamondShape d = new DiamondShape(odd);
                    d.printDiamond();

                    ButterflyShape b = new ButterflyShape(even);
                    b.printButterfly();
                    break;
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Only number, please.");
        }
    }
}