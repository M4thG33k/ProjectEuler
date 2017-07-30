package Problems21_30;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem21 {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>(10000);

        for (int i=2; i<=10000; i++)
        {
            map.put(i, sumOfProperDivisors(i));
        }

        Set<Integer> amicables = new HashSet<>(10000);
        for (int val : map.keySet())
        {
            int val2 = map.get(val);
            if (val2 != val && map.containsKey(val2) && map.get(val2) == val)
            {
                amicables.add(val);
                amicables.add(val2);
            }
        }

        long total = 0L;
        for (int val : amicables)
        {
            total += val;
        }

        System.out.println(total);
    }

    public static int sumOfProperDivisors(int val)
    {
        int total = 1;
        int sqrt = (int)Math.sqrt(val);
        for (int i=2; i <= sqrt; i++)
        {
            if (val%i == 0)
            {
                total += i;
                if (i != sqrt)
                {
                    total += val / i;
                }
            }
        }

        return total;
    }
}
