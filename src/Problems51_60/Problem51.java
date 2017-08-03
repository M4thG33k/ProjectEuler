package Problems51_60;

import Util.Prime;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem51 {

    private static List<String> primes = new ArrayList<>();
    private static char[] cList = new char[]{'1','3','5','7','9'};

    public static void main(String[] args) {
        Map<String, Set<String>> map = new HashMap<>();
        for (long p : Prime.primesStrictlyBelow(1000000))
        {
            if (p<10)
            {
                continue;
            }
            String prime = "" + p;
            primes.add(prime);

            for (String pattern : createPatternsFor(prime))
            {
                if (!map.containsKey(pattern))
                {
                    map.put(pattern, new HashSet<>());
                }
                Pattern pat = Pattern.compile(pattern);
                if (pat.matcher(prime).matches())
                {
                    map.get(pattern).add(prime);
                }
            }
        }

        for (String pattern : map.keySet())
        {
            if (map.get(pattern).size() >= 8)
            {
                List<Long> values = new ArrayList<>();
                for (String val : map.get(pattern))
                {
                    values.add(Long.parseLong(val));
                }
                Collections.sort(values);

                System.out.println(values);
            }
        }
    }

    private static List<String> createPatternsFor(String value)
    {
        List<String> patterns = new ArrayList<>();
        int maxVal = 0;
        for (int i=0; i<value.length()-1; i++)
        {
            maxVal = (maxVal << 1) + 1;
        }

        for (int val=0; val <= maxVal; val++)
        {
            StringBuilder builder = new StringBuilder();
            boolean grouped = false;

            for (int i=0; i<value.length()-1; i++)
            {
                if ((val & (1<<i)) > 0)
                {
                    if (grouped)
                    {
                        builder.append("(\\1)");
                    }
                    else
                    {
                        builder.append("(\\d)");
                        grouped = true;
                    }
                }
                else
                {
                    builder.append(value.charAt(i));
                }
            }
            builder.append(value.charAt(value.length()-1));
            patterns.add(builder.toString());
        }

        return patterns;
    }
}
