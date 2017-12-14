package Problems71_80;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem75 {

    public static void main(String[] args) {
        int numExact = 0;

        Map<Long, Set<String>> triCount = new HashMap<>();

        long a, b, c;
        long L;
        long key;
        long factor;

        long maxVal = 1500000;

        for (long m=2; m <= Math.sqrt(maxVal); m++){
            for (long n=1; n<m; n++){
                // The following three lines are a generator for *primitive* pythagorean triples
                a = m*m - n*n;
                b = 2*m*n;
                c = m*m + n*n;
                // Keep them in order: a < b < c
                if (a > b){
                    long temp = a;
                    a = b;
                    b = temp;
                }
                L = 2*m*(m+n);

                // We still need to take into account multiples of this tuple
                factor = 1;
                key = L;
                while (key <= maxVal){
                    if (!triCount.containsKey(key)){
                        triCount.put(key, new HashSet<>());
                    }

                    triCount.get(key).add((a*factor)+":"+(b*factor)+":"+(c*factor));

//                    System.out.println(key + "==>" + triCount.get(key));
                    key += L;
                    factor += 1;
                }
            }
        }

        for (long k : triCount.keySet()){
            if (triCount.get(k).size() == 1){
                numExact += 1;
            }
        }

        System.out.println(numExact);
    }
}
