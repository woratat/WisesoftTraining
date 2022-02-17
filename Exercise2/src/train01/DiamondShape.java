package train01;

import java.util.ArrayList;
import java.util.List;

public class DiamondShape {
    private int n;

    public DiamondShape(int count) {
        this.n = count;
    }

    public void printDiamond() {
        List<String> arr = new ArrayList<>();
        int odd = 1, space = n / 2;
        String plus = "+";
        String blank = " ";
        String line = "";

        for (int i = 0; i < n; i++) {
            for (int k = space; k >= 1; k--) {
                line += blank;
            }
            for (int j = 0; j < odd; j++) {
                line += plus;
            }

            arr.add(line);
            line = "";

            if (i < n / 2) {
                odd += 2; //เพิ่มทีละ2
                space -= 1;
            } else {
                odd -= 2; //ลดทีละ2
                space += 1;
            }
        }

        for(String s: arr){
            System.out.println(s);
        }
    }
}