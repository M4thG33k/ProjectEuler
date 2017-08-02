package Problems41_50;

import Util.Prime;

import java.util.Collections;
import java.util.List;

public class Problem41 {

    public static void main(String[] args) {
        // There are cannot be 8/9 digit pandigital primes
        List<Long> primes = Prime.primesStrictlyBelow(9876544);

        Collections.reverse(primes);

        for (long prime : primes)
        {
            if (isPan(prime))
            {
                System.out.println(prime);
                break;
            }
        }
    }

    private static boolean isPan(long val)
    {
        int size = ("" + val).length();
        boolean[] digits = new boolean[size+1];

        int digit;
        while (val > 0)
        {
            digit = (int)val%10;
            if (digit == 0 || digit > size || digits[digit])
            {
                return false;
            }
            digits[digit] = true;
            val /= 10;
        }

        return true;
    }
}
