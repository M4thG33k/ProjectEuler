package Problems61_70;

import Util.Prime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem69 {
    public static void main(String[] args) {
        List<Long> primes = Prime.primesStrictlyBelow(1000000);
        Set<Long> pset = new HashSet<>(primes);

        double maxVal = 0;
        int value = 0;
        double testVal = 0;

        for (int i=2; i<=1000000; i++){
            double sqrt = Math.sqrt(i);
            double phi = (double)(i);
            if (pset.contains((long)i)){
                phi = (double)(i-1);
            } else {
                for (Long p :
                        primes) {
                    if (p > sqrt) {
                        break;
                    }
                    if (i % p == 0) {
                        phi *= 1 - (1 / (double) p);
                        long q = i / p;
                        if (pset.contains(q) && p != q) {
                            phi *= 1 - (1 / (double) (q));
                        }
                    }
                }
            }
            testVal = (double)i / Math.round(phi);
            if (testVal > maxVal){
                maxVal = testVal;
                value = i;
            }
        }

        System.out.println(value + " has the maximum value of " + maxVal);
    }
}
