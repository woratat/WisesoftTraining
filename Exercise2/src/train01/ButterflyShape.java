package train01;

public class ButterflyShape {
    private int n;

    public ButterflyShape(int count) {
        this.n = count;
    }

    public void printButterfly() {
        System.out.println();

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("+");
            }
            for (int k = 1; k <= 2 * (n - i) + 1; k++) {
                System.out.print(" ");
            }
            for (int l = 1; l <= i; l++) {
                System.out.print("+");
            }
            System.out.println();
        }

        for (int j = 1; j <= n; j++) {
            System.out.print("+");
        }
        System.out.print("-");
        for (int j = 1; j <= n; j++) {
            System.out.print("+");
        }

        System.out.println();

        for (int i = n - 1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("+");
            }
            for (int k = 1; k <= 2 * (n - i) + 1; k++) {
                System.out.print(" ");
            }
            for (int l = 1; l <= i; l++) {
                System.out.print("+");
            }
            System.out.println();
        }
    }
}

