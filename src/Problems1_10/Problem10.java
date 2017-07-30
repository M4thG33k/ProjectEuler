package Problems1_10;

import Util.Prime;

public class Problem10 {

    public static void main(String[] args) {
        long sum = 0;
        for (long prime : Prime.primesStrictlyBelow(2000000))
        {
            sum += prime;
        }

        System.out.println(sum);
    }
}
