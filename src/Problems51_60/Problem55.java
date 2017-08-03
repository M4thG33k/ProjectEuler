package Problems51_60;

public class Problem55 {

    public static void main(String[] args) {
        int total = 0;
        for (int i=0; i<10000; i++)
        {
            if (isLychrel(i))
            {
                total += 1;
            }
        }
        System.out.println(total);
    }

    private static boolean isPalindromic(long number)
    {
        return number == reverse(number);
    }

    private static boolean isLychrel(long number)
    {
        int iterations = 1;
        long ret = process(number);

        while (iterations < 50 && !isPalindromic(ret))

        {
            iterations += 1;
            ret = process(ret);
        }

        return !isPalindromic(ret);
    }

    private static long process(long number)
    {
        return number + reverse(number);
    }

    private static long reverse(long number)
    {
        long rev = 0L;
        while (number > 0)
        {
            rev = (rev*10) + (number%10);
            number /= 10;
        }

        return rev;
    }
}
