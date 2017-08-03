package Problems41_50;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem43 {

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
                if (canPrepend(a, b))
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
                    if (canPrepend(a, b))
                    {
                        temp.add(a + b.substring(2));
                    }
                }
            }

            partial = temp;
        }

        long total = 0;
        int missing;
        for (String val : partial)
        {
            missing = findMissing(val);
            total += Long.parseLong(missing + val);
        }
        System.out.println(total);
    }

    private static boolean canPrepend(String first, String second)
    {
        if (first.substring(1).equals(second.substring(0,2)))
        {
            Set<Character> sec = new HashSet<>();
            for (char c : second.toCharArray())
            {
                sec.add(c);
            }

            return !sec.contains(first.charAt(0));
        }

        return false;
    }

    private static void generateFor(int val)
    {
        map.put(val, new HashSet<>());
        int iter = val;
        while (iter < 1000)
        {
            if (iter >= 10 && iter < 100)
            {
                String temp = "0" + iter;
                if (!containsDupes(temp))
                {
                    map.get(val).add(temp);
                }
            }
            else if (iter >= 100 && !containsDupes(iter))
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

    private static boolean containsDupes(String val)
    {
        Set<Character> chars = new HashSet<>();
        for (char c : val.toCharArray())
        {
            if (chars.contains(c))
            {
                return true;
            }
            chars.add(c);
        }

        return false;
    }

    private static int findMissing(String val)
    {
        boolean[] digits = new boolean[10];
        long value = Long.parseLong(val);
        int digit;
        for (int i=0; i<val.length(); i++)
        {
            digit = (int)(value%10);
            digits[digit] = true;
            value /= 10;
        }

        for (int i=0; i<10; i++)
        {
            if (!digits[i])
            {
                return i;
            }
        }

        return -1;
    }
}
