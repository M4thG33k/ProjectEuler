package Problems41_50;

import Util.Prime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem46 {

    private static List<Long> primes;
    private static Set<Long> squares2;

    public static void main(String[] args) {
        primes = Prime.primesStrictlyBelow(1000000);
        Set<Long> pset = new HashSet<>(primes);
        squares2 = new HashSet<>(1000000);
        for (long i = 1L; i < 1000000L; i++)
        {
            squares2.add(2*i*i);
        }

        long odd = 33;
        while (odd < 1000000)
        {
            if (!pset.contains(odd) && !isGoldbach(odd))
            {
                System.out.println(odd);
                break;
            }
            odd += 2;
        }
    }

    private static boolean isGoldbach(long value)
    {
        for (long prime : primes)
        {
            if (prime >= value)
            {
                return false;
            }
            if (squares2.contains(value - prime))
            {
                return true;
            }
        }

        return false;
    }
}
