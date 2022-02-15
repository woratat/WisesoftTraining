package train01;

public class DiamondShape {
    private int n;

    public DiamondShape(int count) {
        this.n = count;
    }

    public void printDiamond (){
        int odd = 1, space = n/2;

        for (int i = 0; i < n; i++) {
            for (int k = space; k >= 1; k--) {
                System.out.print(" ");
            }
            for (int j = 0; j < odd; j++) {
                System.out.print("+");
            }

            System.out.println();

            if (i < n/2) {
                odd += 2; //เพิ่มทีละ2
                space -= 1;
            } else {
                odd -= 2; //ลดทีละ2
                space += 1;
            }
        }
    }
}
