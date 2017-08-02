package Problems31_40;

import Util.Prime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem37 {

    private static List<Long> primes;
    private static Set<Long> pset;

    public static void main(String[] args) {
        primes = Prime.primesStrictlyBelow(1000000);
        pset = new HashSet<>(primes);

        long total = 0L;
        for (long prime : primes)
        {
            if (prime < 9L)
            {
                continue;
            }
            if (isFromRight(prime) && isFromLeft(prime))
            {
                total += prime;
            }
        }

        System.out.println(total);
    }

    private static boolean isFromLeft(long prime)
    {
        int window = 10;
        long val = prime%window;
        while (val != prime)
        {
            if (!pset.contains(val))
            {
                return false;
            }
            window *= 10;
            val = prime%window;
        }

        return true;
    }

    private static boolean isFromRight(long prime)
    {
        do{
            prime /= 10;
            if (prime == 0)
            {
                return true;
            }
            if (!pset.contains(prime))
            {
                return false;
            }
        } while (true);
    }
}
