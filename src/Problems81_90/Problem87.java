package Problems81_90;

import Util.Prime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem87 {

    private static List<Long> primes = Prime.primesStrictlyBelow(7072);
    private static List<Long> primeSquares = new ArrayList<>(primes.size());
    private static List<Long> primeCubes = new ArrayList<>(primes.size());
    private static List<Long> primeFourths = new ArrayList<>(primes.size());
    private static Set<Long> values = new HashSet<>();

    private static long limit = 50000000;

    public static void main(String[] args) {
        for (long p: primes){
            long val = p*p;
            primeSquares.add(val);
            val *= p;
            primeCubes.add(val);
            val *= p;
            primeFourths.add(val);
        }
        long val;

        for (long s: primeSquares){
            for (long c: primeCubes){
                val = s+c;
                for (long f: primeFourths){
                    if (val+f < limit){
                        values.add(val+f);
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println(values.size());
    }
}
