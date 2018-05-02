package Problems91_100;

public class Problem94 {

    public static void main(String[] args) {
        long perim = 0;


        long a = 3;
        while (a < 333333334) {
            if (hasIntegralArea(a, -1)) {
                System.out.println(String.format("Found: a=%d & b=%d", a, a - 1));
                perim += 3 * a - 1;
            }
            if (hasIntegralArea(a, 1)) {
                System.out.println(String.format("Found: a=%d & b=%d", a, a + 1));

                perim += 3 * a + 1;
            }

            a += 2;
        }

        System.out.println(perim);
    }

    private static long sqrt(long val) {
        double sq = Math.sqrt(val);
        return (long) sq == sq ? (long) sq : -1;
    }

    private static boolean hasIntegralArea(long a, int posneg) {
        long sq = sqrt(a * (3 * a - posneg * 2) - 1);
        if (sq < 0) {
            return false;
        }
        if (sq%2 == 1){
            return false;
        }
        long h = sq/2;
        long b2 = (a+posneg)/2;
        if (h*h+b2*b2 != a*a){
            return false;
        }
        sq *= (a + posneg);
        return sq % 4 == 0;
    }
}
