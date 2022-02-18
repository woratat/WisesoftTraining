package train02;

import java.util.Stack;

public class ButterflyShape {
    private int n;
    String plus = "+";
    String minus = "-";
    String blank = " ";

    public ButterflyShape(int count) {
        this.n = count;
    }

    public void printButterfly() {
        Stack<String> stack = pushStack();

        stack.stream().forEach(System.out::println);
        stack.pop();
        for (int i = stack.size(); i > 0; i--) {
            System.out.println(stack.peek());
            stack.pop();
        }
    }

    public Stack pushStack() {
        String line = "";
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j <= i; j++) {
                line += plus;
            }
            for (int k = 0; k < 2 * (n - i) - 1; k++) {
                line += blank;
            }

            line += line.replaceAll(blank, "");

            stack.push(line);
            line = "";
        }

        for (int j = 0; j < n; j++) { //center line
            line += plus;
        }
        line = line + minus + line.replaceAll(blank, "");
        stack.push(line);

        return stack;
    }
}