package Problems21_30;

import Util.Prime;

import java.util.ArrayList;
import java.util.List;

public class Problem27 {

    private static List<Long> primes;
    private static long last_max;

    public static void main(String[] args) {
        // b has to be prime, otherwise our formula will fail for n=0
        List<Long> validBs = new ArrayList<>(Prime.primesStrictlyBelow(1000));

        Long prod = 0L;
        Long maxN = 0L;

        for (long b : validBs)
        {
            for (int a = -999; a<1000; a++)
            {
                if (a==0)
                {
                    continue;
                }
                List<Long> foundPrimes = new ArrayList<>();
                int n=0;
                long partial = n*n + b;
                while (isPrime(partial + n*a))
                {
                    foundPrimes.add(partial + n*a);
                    n += 1;
                    partial = b + n*n;
                }

                if (foundPrimes.size() > maxN)
                {
                    maxN = (long)foundPrimes.size();
                    prod = a*b;
                }
            }
        }

        System.out.println(prod);
    }

    private static boolean isPrime(long value)
    {
        if (primes == null || value > last_max)
        {
            primes = Prime.primesStrictlyBelow(value+1);
            last_max = value;
        }

        return primes.contains(value);
    }
}
