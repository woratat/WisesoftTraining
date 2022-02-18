package train01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
            line += line.replaceAll(blank,"");
            arr.add(line);
            line = "";
        }

        for (int j = 0; j < n; j++) {
            line += plus;
        }
        line += center + line;
        arr.add(line);


        arr.stream().forEach(System.out::println);
        Collections.reverse(arr);
        arr.stream().skip(1).collect(Collectors.toList()).forEach(System.out::println);
    }
}