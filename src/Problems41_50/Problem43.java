package Problems41_50;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem43 {

    /*
    NOT FULLY WORKING
     */

    private static int[] divisors = new int[]{2, 3, 5, 7, 11, 13, 17};
    private static Map<Integer, Set<String>> map = new HashMap<>();

    public static void main(String[] args) {

        for (int d : divisors)
        {
            generateFor(d);
        }

        Set<String> partial = new HashSet<>();

        for (String a : map.get(13))
        {
            for (String b: map.get(17))
            {
                if (a.substring(1, 3).equals(b.substring(0,2)) &&
                        !a.substring(0,1).equals(b.substring(2)))
                {
                    partial.add(a + b.substring(2));
                }
            }
        }

        for (int i : new int[]{11, 7, 5, 3, 2})
        {
            Set<String> temp = new HashSet<>();
            for (String a : map.get(i))
            {
                for (String b : partial)
                {
                    if (a.substring(1, 3).equals(b.substring(0,2)) &&
                            !a.substring(0,1).equals(b.substring(b.length()-1)))
                    {
                        temp.add(a + b.substring(b.length()-1));
                    }
                }
            }

            partial = temp;
        }

        long total = 0;
        for (String val : partial)
        {
            System.out.println(val);
            total += Long.parseLong(val);
        }
        System.out.println(total);
    }

    private static void generateFor(int val)
    {
        map.put(val, new HashSet<>());
        int iter = val;
        while (iter < 1000)
        {
            if (iter >= 100 && !containsDupes(iter))
            {
                map.get(val).add(""+iter);
            }
            iter += val;
        }
    }

    private static boolean containsDupes(int val)
    {
        boolean[] digits = new boolean[10];

        int digit;
        while (val > 0)
        {
            digit = val%10;
            if (digits[digit])
            {
                return true;
            }

            digits[digit] = true;
            val /= 10;
        }

        return false;
    }
}
