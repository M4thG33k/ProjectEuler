package Problems1_10;

import java.util.ArrayList;
import java.util.List;

public class Problem7 {

    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<>(10001);
        int found = 1;
        primes.add(2);

        int value = 3;
        boolean isPrime;

        while (found != 10001)
        {
            isPrime = true;
            for (int p : primes)
            {
                if (value%p == 0)
                {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime)
            {
                primes.add(value);
                found += 1;
            }
            value += 2;
        }

        System.out.println(primes.get(10000));
    }
}
