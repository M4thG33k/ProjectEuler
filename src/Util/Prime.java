package Util;

import java.util.*;
import java.util.stream.Collectors;

public class Prime {

    private static List<Long> PRIMEZ;

    public static List<Long> init()
    {
        return primesStrictlyBelow(1000000);
    }

    public static long findLargestPrimeFactor(long n)
    {
        if (n < 2)
        {
            return -1; //error
        }

        long value = 2L; // this value will keep track of the largest prime factor we find

        while (value != n)
        {
            while (n%value == 0)
            {
                n /= value;
            }

            if (value != 2)
            {
                value += 2; // once value != 2, all primes are odd (and n is odd as well)
            }
            else
            {
                value += 1;
            }
        }

        return value;
    }

    public static List<Long> primesStrictlyBelow(long max)
    {
        if (max < 2)
        {
            return new ArrayList<>();
        }
        if (PRIMEZ != null && PRIMEZ.get(PRIMEZ.size()-1) >= max)
        {
            return PRIMEZ.stream().filter(x -> x<=max).collect(Collectors.toList());
        }
        List<Long> primes;
        if (PRIMEZ == null)
        {
            primes =new ArrayList<>();
            primes.add(2L);
        } else
        {
            primes = PRIMEZ;
        }

        long lastPrime = primes.get(primes.size()-1);
        long n = lastPrime == 2 ? 3 : lastPrime + 2;
        long m;
        boolean isPrime;
        while (n < max)
        {
//            System.out.println("n = " + n);
            m = (long)Math.sqrt(n);
            isPrime = true;
            for (long p : primes)
            {
                if (p > m)
                {
                    break;
                }
                if (n%p == 0)
                {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime)
            {
                primes.add(n);
            }
            if ((n&1) == 1)
            {
                n += 2;
            }
            else
            {
                n += 1;
            }
        }

        PRIMEZ = primes;

        return primes;
    }

    public static Map<Long, Integer> primeFactorMap(long number)
    {
        List<Long> primes = primesStrictlyBelow(number + 1);

        Map<Long, Integer> map = new HashMap<>(primes.size());

        long n;
        for (long p : primes)
        {
            int count = 0;
            n = number;

            while (n%p == 0)
            {
                n /= p;
                count += 1;
            }

            if (count > 0)
            {
                map.put(p, count);
            }
        }

        return map;
    }

    public static int numFactors(long number)
    {
        Map<Long, Integer> map = primeFactorMap(number);

        int total = 1;
        for (long key : map.keySet())
        {
            total *= (map.get(key) + 1);
        }

        return total;
    }

    public static long numDivisors(long number)
    {
        long total = 0;

        for (int i=1; i <= Math.sqrt(number); i++)
        {
            if (number%i == 0)
            {
                total += (number/i == i) ? 1 : 2;
            }
        }

        return total;
    }
}
