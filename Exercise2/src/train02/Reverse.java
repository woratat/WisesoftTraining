package train02;

import java.util.Stack;

public class Reverse {
    static Stack reverse(Stack stack){
        Stack<String> stackCopy = new Stack<>();
        stack.pop();

        while(!stack.empty()){
            stackCopy.push((String) stack.pop());
        }
        return stackCopy;
    }
}
