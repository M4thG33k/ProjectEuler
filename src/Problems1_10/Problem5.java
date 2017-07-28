package Problems1_10;

import java.util.HashMap;

public class Problem5 {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int number;
        // This loop creates a map where the keys are prime numbers and the values are the maximum number of times
        // that prime factor is used in any of the numbers between 1 and 20
        for (int i=2; i<=20; i++)
        {
            number = i;
            for (int factor : map.keySet())
            {
                int times = 0;
                while (number > 1 && number%factor == 0)
                {
                    number /= factor;
                    times += 1;
                }

                map.put(factor, Math.max(times, map.get(factor)));
            }
            if (number > 1)
            {
                map.put(number, 1);
            }
        }

        // Now we create a number that is the product of p^k for each (p, k) in the map
        int total = 1;
        for (int factor : map.keySet())
        {
            for (int i = 0; i < map.get(factor); i++) {
                total *= factor;
            }
        }

        System.out.println(total);
    }
}
