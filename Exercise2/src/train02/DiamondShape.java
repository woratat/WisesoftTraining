package train02;

import java.util.Stack;

public class DiamondShape {
    private int n;
    String plus = "+";
    String blank = " ";

    public DiamondShape(int count) {
        this.n = count;
    }

    public void printDiamond() {
        int odd = 1, space = n/2;
        String line = "";
        Stack<String> stack = new Stack<>();

        for (int i = n; i>=1; i-=2) {
            for (int k = space; k >= 1; k--) {
                line += blank;
            }
            for (int j = 0; j < odd; j++) {
                line += plus;
            }

            stack.push(line);
            line = "";

            odd += 2;
            space -= 1;
        }

        stack.stream().forEach(System.out::println); // print upper half
        Reverse.reverse(stack).stream().forEach(System.out::println); // print bottom half
    }
}