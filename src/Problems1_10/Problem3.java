package Problems1_10;

import Util.Prime;

public class Problem3 {

    public static void main(String[] args) {
        long number = 600851475143L;

        // Using a utility class Prime in the Util directory
        System.out.println(Prime.findLargestPrimeFactor(number));
    }
}
