package Problems51_60;

import Util.Prime;

import java.util.HashSet;
import java.util.Set;

public class Problem58 {

    public static void main(String[] args) {
//        Set<Long> primes = new HashSet<>(Prime.primesStrictlyBelow(5000000));

        int ring = 1;
        double denom = 5;
        int numPrimes = 3;
        long largest=9;
        double perc = numPrimes / denom;

        while (perc > 0.1)
        {
            ring += 1;
            denom += 4;
            for (int i=0; i<3; i++)
            {
                largest += 2*ring;
                if (isPrime(largest))
                {
                    numPrimes += 1;
                }
            }
            largest += 2*ring;
            perc = numPrimes / denom;
        }

        System.out.println((2*ring) + 1);
    }

    private static boolean isPrime(long val)
    {
        for (int i=3; i<= (int)Math.sqrt(val); i+=2)
        {
            if (val%i == 0)
            {
                return false;
            }
        }

        return true;
    }
}
