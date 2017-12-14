package Problems71_80;

import Util.LargeNumber;

public class Problem80 {

    public static void main(String[] args) {
        long total = 0;
        for (int i=2; i<100; i++){
            LargeNumber sqrt = squareRoot(i);
            if (sqrt.length() == 100){
                total += sqrt.digitSum();
            }
        }

        System.out.println(total);
    }

    // This is using the decimal Digit-by-Digit calculation method from:
    // https://en.wikipedia.org/wiki/Methods_of_computing_square_roots
    // This specific implementation will only work for integer inputs strictly between 1 & 100!
    private static LargeNumber squareRoot(int val) {
        LargeNumber result = new LargeNumber(0);
        LargeNumber remainder;
        LargeNumber c = new LargeNumber(val);
        int x;
        LargeNumber y;

        while (result.length() < 100) {
            x = calculateX(result, c);
            if (x == 0){
                y = new LargeNumber(0);
            } else {
                y = new LargeNumber(result);
                y.times(20);
                y.add(x);
                y.times(x);
            }

            if (!result.equals(new LargeNumber(0))) {
                result.times10toThe(1);
            }
            result.add(x);

            remainder = y.findAbsDifference(c);
            if (remainder.equals(new LargeNumber(0))) {
                result.removeLeadingZeros();
                return result;
            }

            c = new LargeNumber(remainder);
            c.times10toThe(2);
        }

        return result;
    }

    private static int calculateX(LargeNumber p, LargeNumber c) {
        int left = 0;
        int right = 10;
        int mid = (left + right) / 2;

        LargeNumber temp = new LargeNumber(p);
        temp.times(20);

        LargeNumber temp2;
        while (left + 1 < right) {
            temp2 = new LargeNumber(temp);
            temp2.add(mid);
            temp2.times(mid);

            if (c.isGreaterThan(temp2)) {
                left = mid;
            } else if (c.equals(temp2)) {
                return mid;
            } else {
                right = mid;
            }

            mid = (left + right) / 2;
        }

        return left;
    }
}
