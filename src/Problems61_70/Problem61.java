package Problems61_70;

import java.util.*;

public class Problem61 {

    private static char[] types = new char[]{'t', 's', 'p', 'x', 'h', 'o'};
    private static Map<Character, Set<Integer>> polygonalNumbers = new HashMap<>();
    private static Set<Integer> seenSet = new HashSet<>();

    private static Map<Integer, Map<Character, Set<Integer>>> map = new HashMap<>();

    public static void main(String[] args) {
        init();

        for (char t : types)
        {
            if (polygonalNumbers.containsKey(t))
            {
                System.out.println(t + "\t" + polygonalNumbers.get(t).size());
                seenSet.addAll(polygonalNumbers.get(t));
            }
        }

        for (int i : seenSet)
        {
            if (!map.containsKey(i))
            {
                map.put(i, new HashMap<>());
                for (char t : types)
                {
                    map.get(i).put(t, new HashSet<>());
                }
            }

            for (char t : types)
            {
                if (polygonalNumbers.get(t).contains(i))
                {
                    continue;
                }
                int end = i%100;
                for (int val : polygonalNumbers.get(t))
                {
                    if (end == val/100)
                    {
                        map.get(i).get(t).add(val);
                    }
                }
            }
        }

        for (int k : new HashSet<>(map.keySet()))
        {
            boolean isValid = false;
            for (char t : types)
            {
                if (map.get(k).get(t).size() > 0)
                {
                    isValid = true;
                    break;
                }
            }
            if (!isValid)
            {
                map.remove(k);
            }
        }

        int sum = 0;

        for (int oct : polygonalNumbers.get('o'))
        {
            if (map.get(oct) == null)
            {
                continue;
            }
            List<Integer> list = new ArrayList<>();
            list.add(oct);
            Set<Character> charSet = new HashSet<>();
            charSet.add('o');

            List<Integer> result = getChain(list, charSet);
            if (result != null)
            {
                System.out.println(result);
                for (int i : result)
                {
                    sum += i;
                }
                break;
            }
        }

        System.out.println(sum);
    }

    private static List<Integer> getChain(List<Integer> currentChain, Set<Character> seenChars)
    {
        if (currentChain == null || currentChain.size() == 0)
        {
            return null;
        }

        int last = currentChain.get(currentChain.size() - 1);
        Map<Character, Set<Integer>> thisMap = map.get(last);
        if (thisMap == null)
        {
            return null;
        }

        for (char t : types)
        {
            if (seenChars.contains(t))
            {
                continue;
            }

            for (int value : thisMap.get(t))
            {
                if (currentChain.contains(value))
                {
                    continue;
                }

                List<Integer> subChain = new ArrayList<>(currentChain);
                subChain.add(value);

                if (subChain.size() == 6)
                {
                    if (subChain.get(5)%100 == subChain.get(0)/100)
                    {
                        return subChain;
                    }
                    else
                    {
                        return null;
                    }
                }
                Set<Character> subChar = new HashSet<>(seenChars);
                subChar.add(t);

                List<Integer> subquery= getChain(subChain, subChar);
                if (subquery != null)
                {
                    return subquery;
                }
            }
        }

        return null;
    }

    private static void init()
    {
        int min = 1000;
        int max = 10000;

        int n = 1;
        int val;
        // Tri
        Set<Integer> set = new HashSet<>();
        val = (n * (n+1)) / 2;
        while (val < max)
        {
            if (val >= min)
            {
                set.add(val);
            }
            n += 1;
            val = (n * (n+1)) / 2;
        }

        polygonalNumbers.put('t', set);

        // Square
        n = 1;
        set = new HashSet<>();
        val = n*n;
        while (val < max)
        {
            if (val >= min)
            {
                set.add(val);
            }
            n += 1;
            val = n*n;
        }

        polygonalNumbers.put('s', set);

        // Pent
        n = 1;
        set = new HashSet<>();
        val = (n * (3*n-1)) / 2;
        while (val < max)
        {
            if (val >= min)
            {
                set.add(val);
            }
            n += 1;
            val = (n * (3*n-1)) / 2;
        }

        polygonalNumbers.put('p', set);

        // Hex
        n = 1;
        set = new HashSet<>();
        val = n * (2*n-1);
        while (val < max)
        {
            if (val >= min)
            {
                set.add(val);
            }
            n += 1;
            val = n * (2*n-1);
        }

        polygonalNumbers.put('x', set);

        // Hept
        n = 1;
        set = new HashSet<>();
        val =(n * (5*n-3)) / 2;
        while (val < max)
        {
            if (val >= min)
            {
                set.add(val);
            }
            n += 1;
            val =(n * (5*n-3)) / 2;
        }

        polygonalNumbers.put('h', set);

        // Oct
        n = 1;
        set = new HashSet<>();
        val =(n * (3*n - 2));
        while (val < max)
        {
            if (val >= min)
            {
                set.add(val);
            }
            n += 1;
            val =(n * (3*n - 2));
        }

        polygonalNumbers.put('o', set);

    }
}
