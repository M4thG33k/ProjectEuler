package Problems31_40;

import java.util.HashSet;
import java.util.Set;

public class Problem32 {

    public static void main(String[] args) {
        Set<Integer> prods = new HashSet<>();

        // All valid products must either be a 1-digit times a 4-digit number
        // or a 2-digit times a 3-digit number.
        for (int first=1; first<10; first++)
        {
            for (int second=1234; second<9877; second++)
            {
                if (isValid(first, second))
                {
                    prods.add(first*second);
                }
            }
        }
        for (int first=12; first<99; first++)
        {
            for (int second=123; second<988; second++)
            {
                if (isValid(first, second))
                {
                    prods.add(first*second);
                }
            }
        }
        long total = 0;
        for (int val : prods)
        {
            total += val;
        }
        System.out.println(total);
    }

    private static boolean isValid(int first, int second)
    {
        boolean[] digits = new boolean[10];
        int digit;
        int[] numbers = new int[]{first, second, first*second};
        for (int i=0; i<3; i++)
        {
            int number = numbers[i];
            while (number > 0)
            {
                digit = number%10;
                if (digit==0 || digits[digit])
                {
                    return false;
                }
                digits[digit] = true;
                number /= 10;
            }
        }

        return true;
    }
}
