package Problems41_50;

import Util.Prime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem50 {

    private static List<Long> primes = Prime.primesStrictlyBelow(1000000);

    public static void main(String[] args) {
        Set<Long> pset = new HashSet<>(primes);
        int size = primes.size();
        int longestChain = 0;
        long answer = 0L;

        int startIndex = 0;
        while (startIndex < size)
        {
            long total = primes.get(startIndex);
            int chain = 1;
            for (int i=startIndex + 1; i < size; i++)
            {
                total += primes.get(i);
                chain += 1;
                if (total >= 1000000)
                {
                    break;
                }

                if (pset.contains(total) && chain > longestChain)
                {
                    longestChain = chain;
                    answer = total;
                }
            }

            startIndex += 1;
        }

        System.out.println(answer);
    }
}
