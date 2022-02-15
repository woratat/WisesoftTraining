package train01;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int odd = 0; int even = 0;

        try (Scanner input = new Scanner(System.in)) {
            while(true){
                System.out.print("Enter odd number = ");
                int n1 = input.nextInt();

                System.out.print("Enter even number = ");
                int n2 = input.nextInt();

                if (n1 % 2 == 0) {
                    System.out.println("Please enter odd number only.");
                }else if(n2 % 2 != 0) {
                    System.out.println("Please enter even number only.");
                }else {
                    odd = n1;
                    even = n2;
                    break;
                }
            }
        }

        System.out.println("");

        DiamondShape d = new DiamondShape(odd);
        d.printDiamond();

        ButterflyShape b = new ButterflyShape(even);
        b.printButterfly();

    }
}

