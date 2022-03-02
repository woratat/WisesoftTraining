package train02;

import java.util.Stack;

public class DiamondShape {
    private int n;
    String symbol = "+";
    String blank = " ";

    public DiamondShape(int count) {
        this.n = count;
    }

    public void printDiamond() {
        int odd = 1, space = n / 2;
        String line = "";
        Stack<String> stack = new Stack<>();

        for (int i = n; i >= 1; i -= 2) {
            for (int k = space; k >= 1; k--) {
                line += blank;
            }
            for (int j = 0; j < odd; j++) {
                line += symbol;
            }

            stack.push(line);
            line = "";

            odd += 2;
            space -= 1;
        }

        stack.stream().forEach(System.out::println);
        stack.pop();
        for (int i = stack.size(); i > 0; i--) {
            System.out.println(stack.peek());
            stack.pop();
        }
    }
}