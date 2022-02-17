package train01;

import java.util.ArrayList;
import java.util.List;

public class ButterflyShape {
    private int n;

    public ButterflyShape(int count) {
        this.n = count;
    }

    public void printButterfly() {
        List<String> arr = new ArrayList<>();
        String plus = "+";
        String blank = " ";
        String center = "-";
        String line = "";

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= i; j++) {
                line += plus;
            }
            for (int k = 0; k < 2 * (n - i) - 1; k++) {
                line += blank;
            }
            for (int j = 0; j <= i; j++) {
                line += plus;
            }
            arr.add(line);
            line = "";
        }

        for (int j = 0; j < n; j++) {
            line += plus;
        }
        line += center;
        for (int j = 0; j < n; j++) {
            line += plus;
        }
        arr.add(line);
        line = "";

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                line += plus;
            }
            for (int k = 0; k < 2 * (n - i) - 1; k++) {
                line += blank;
            }
            for (int j = 0; j <= i; j++) {
                line += plus;
            }
            arr.add(line);
            line = "";
        }

        for(String s: arr){
            System.out.println(s);
        }
    }
}