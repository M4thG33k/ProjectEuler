package Problems41_50;

import Util.Prime;

import java.util.*;
import java.util.stream.Collectors;

public class Problem49 {

    private static List<Long> primes = Prime.primesStrictlyBelow(10000);

    public static void main(String[] args) {
        primes = primes.stream().filter(x -> x > 1000).collect(Collectors.toList());

        Map<String, Set<Long>> map = new HashMap<>();

        for (long p : primes)
        {
            String key = getKey(p);
            if (!map.containsKey(key))
            {
                map.put(key, new HashSet<>());
            }
            map.get(key).add(p);
        }

        Set<Set<Long>> valids = new HashSet<>();
        for (String key : map.keySet())
        {
            if (map.get(key).size() >= 3)
            {
                valids.add(map.get(key));
            }
        }

        for (Set<Long> value : valids)
        {
            List<Long> sorted = new ArrayList<>(value);
            Collections.sort(sorted);
            int index = 0;
            while (index < sorted.size()-2)
            {
                if ((sorted.get(index+1)-sorted.get(index)) == (sorted.get(index+2)-sorted.get(index+1)))
                {
                    System.out.println(sorted.get(index)+""+sorted.get(index+1)+""+sorted.get(index+2));
                }
                index += 1;
            }
        }
    }

    private static String getKey(long value)
    {
        List<Character> chars = new ArrayList<>();
        for (char c : ("" + value).toCharArray())
        {
            chars.add(c);
        }

        Collections.sort(chars);

        StringBuilder builder = new StringBuilder();
        for (char c: chars)
        {
            builder.append(c);
        }

        return builder.toString();
    }
}
