package Problems51_60;

import Util.Prime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * NOT COMPLETE
 */

public class Problem60 {

    private static List<Long> primes = Prime.init();
    private static Set<Long> pset = new HashSet<>(primes);

    public static void main(String[] args) {
        List<String> valids = new ArrayList<>();
        for (Long p : pset)
        {
            valids.addAll(getConcats("" + p));
        }

        for (String s: valids)
        {
            System.out.println(s);
        }
        System.out.println(valids.size());
    }

    private static List<String> getConcats(String prime)
    {
        List<String> results = new ArrayList<>();

        for (int i=1; i<prime.length()-1; i++)
        {
            String first = prime.substring(0, i);
            String second = prime.substring(i);

            if (second.charAt(0) == '0')
            {
                continue;
            }

            if (pset.contains(Long.parseLong(first)) && pset.contains(Long.parseLong(second)) && pset.contains(Long.parseLong(second+first)))
            {
                if (Long.parseLong(first) < Long.parseLong(second))
                {
                    results.add(first + "," + second);
                }
                else
                {
                    results.add(second + "," + first);
                }
            }
        }

        return results;
    }
}
