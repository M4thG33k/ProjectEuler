package Util;

public class Prime {

    public static long findLargestPrimeFactor(long n)
    {
        if (n < 2)
        {
            return -1; //error
        }

        long value = 2L; // this value will keep track of the largest prime factor we find

        while (value != n)
        {
            while (n%value == 0)
            {
                n /= value;
            }

            if (value != 2)
            {
                value += 2; // once value != 2, all primes are odd (and n is odd as well)
            }
            else
            {
                value += 1;
            }
        }

        return value;
    }
}
