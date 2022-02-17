package train01;

public class ButterflyShape {
    private int n;

    public ButterflyShape(int count) {
        this.n = count;
    }

    public void printButterfly() {
        String plus = "+"; String blank = " "; String center = "-";

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(plus);
            }
            for (int k = 0; k < 2 * (n - i) - 1; k++) {
                System.out.print(blank);
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(plus);
            }
            System.out.println();
        }

        for (int j = 0; j < n; j++) {
            System.out.print(plus);
        }
        System.out.print(center);
        for (int j = 0; j < n; j++) {
            System.out.print(plus);
        }

        System.out.println();

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                System.out.print(plus);
            }
            for (int k = 0; k < 2 * (n - i) - 1; k++) {
                System.out.print(blank);
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(plus);
            }
            System.out.println();
        }
    }
}