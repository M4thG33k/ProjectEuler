package Problems71_80;

import Util.Prime;

import java.util.*;

public class Problem72 {

    static List<Long> plist;
    static Set<Long> pset;

    // Let's use our good old friend the Euler Phi Function (from problems 69&70) to help us out.
    // The number of proper, irreducible fractions with a denominator of d is equal to phi(d)!
    public static void main(String[] args) {
        plist = Prime.primesStrictlyBelow(1000000);
        pset = new HashSet<>(plist);

        int maxDenom = 1000000;

        // (SPOILER: our answer is larger than an int can hold!)
        long answer = 1; // start with 1/2 to make things a tad bit easier

        // Since phi(2*m) = 2*phi(m) if m is even and phi(2*m) = phi(m) if m is odd, we can cache some info to speed
        // up the calculations a bit
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2, 1);
        int phi;
        for (int d=3; d<=maxDenom; d++){
            // If d is even, we can use our shortcut!
            if (d%2==0){
                if (d%4==0){
                    phi = 2 * map.get(d/2);
                } else {
                    phi = map.get(d/2);
                }
            } else {
                phi = getPhi(d);
            }
//            System.out.println(d + "==>" + phi);
            map.put(d, phi);
            answer += phi;
        }

        System.out.println(answer);
    }

    public static int getPhi(int number){
        if (pset.contains((long)number)){
            return number-1;
        }
        double phi = (double)number;
        long lastQ = number;
        for (Long p: plist){
            if (p>=lastQ){
                break;
            }
            if (number%p==0){
                phi *= 1 - (1/(double)p);
                long q = number / p;
                lastQ = q;
                if (pset.contains(q) && p < q){
                    phi *= 1 - (1/(double)q);
                }
            }
        }
        return (int)Math.round(phi);
    }
}
