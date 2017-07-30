package Problems21_30;

import java.util.*;

public class Problem23 {

    public static void main(String[] args) {
        List<Integer> abundants = new ArrayList<>();
        for (int i=1; i<=28123; i++)
        {
            if (isAbundant(i))
            {
                abundants.add(i);
            }
        }
        Collections.sort(abundants);
        Set<Integer> abSet = new HashSet<>(abundants);

        long total = 0L;
        boolean good;
        for (int i=1; i<=28123; i++)
        {
            good = true;
            for (int val : abundants)
            {
                if (val >= i)
                {
                    break;
                }
                if (abSet.contains(i-val))
                {
                    good = false;
                    break;
                }
            }
            if (good)
            {
                total += i;
            }
        }

        System.out.println(total);
    }

    public static boolean isAbundant(int num)
    {
        int total = 1;
        double sqrt = Math.sqrt(num);
        for (int i=2; i <= (int)sqrt; i++)
        {
            if (num%i == 0)
            {
                total += i + ((i==sqrt) ? 0 : num/i);
                if (total > num)
                {
                    return true;
                }
            }
        }
        return total > num;
    }
}
