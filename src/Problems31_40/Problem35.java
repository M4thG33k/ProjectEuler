package Problems31_40;

import Util.Prime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem35 {

    public static void main(String[] args) {
        List<Long> primes = Prime.primesStrictlyBelow(1000000);
        Set<Long> pSet = new HashSet<>(primes);

        Set<Long> cPrimes = new HashSet<>(pSet.size());

        for (long prime : primes)
        {
            if (cPrimes.contains(prime))
            {
                continue;
            }
            if (prime < 10)
            {
                cPrimes.add(prime);
                continue;
            }

            boolean isCircular = true;
            Set<Long> seen = new HashSet<>();
            seen.add(prime);
            long original = prime;
            prime = Integer.parseInt(original%10 + "" + original/10);
            while (prime != original)
            {
                seen.add(prime);
                if (!pSet.contains(prime))
                {
                    isCircular = false;
                    break;
                }
                prime = Integer.parseInt(prime%10 + ""  + prime/10);
            }

            if (isCircular)
            {
                cPrimes.addAll(seen);
            }
        }

        System.out.println(cPrimes.size());
    }
}
