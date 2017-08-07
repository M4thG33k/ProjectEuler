package Problems61_70;

import java.util.*;

public class Problem62 {

    private static Map<String, Set<Long>> map = new HashMap<>();

    public static void main(String[] args) {

        long i = 1;
        long cube;
        long max = -1;
        Set<String> possibilities = new HashSet<>();

        while (true)
        {
            cube = i*i*i;
            if (max > 0 && cube >= max)
            {
                break;
            }
            String hash = getHashed(cube);
            if (!map.containsKey(hash))
            {
                map.put(hash, new HashSet<>());
            }
            map.get(hash).add(cube);
            if (map.get(hash).size() == 5)
            {
                if (max == -1)
                {
                    max = getMax(cube);
                }

                possibilities.add(hash);
            }
            i += 1;
        }

        long smallest = Long.MAX_VALUE;

        for (String hash : possibilities)
        {
            if (map.get(hash).size() == 5)
            {
                List<Long> list = new ArrayList<>(map.get(hash));
                Collections.sort(list);
                if (smallest > list.get(0))
                {
                    smallest = list.get(0);
                }
            }
        }

        System.out.println(smallest);

    }

    private static long getMax(long value)
    {
        long max = 1;
        while (value > 0)
        {
            max *= 10;
            value /= 10;
        }
        return max;
    }

    private static String getHashed(long value)
    {
        List<String> chars = new ArrayList<>();
        while (value > 0)
        {
            chars.add("" + (value%10));
            value /= 10;
        }

        Collections.sort(chars);
        StringBuilder builder = new StringBuilder();
        for (String s : chars)
        {
            builder.append(s);
        }

        return builder.toString();
    }
}
