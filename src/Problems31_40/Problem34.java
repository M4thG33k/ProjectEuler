package Problems31_40;

public class Problem34 {

    private static int[] factorials;

    private static void init()
    {
        factorials = new int[10];
        factorials[0] = 1;
        for (int i=1; i<10; i++)
        {
            factorials[i] = i*factorials[i-1];
        }
    }

    public static void main(String[] args) {
        init();

        // the largest 6-digit number is 999999 whose factorial sum
        // contains 7 digits, so we will not check any 7+ digit numbers.

        long total = 0;
        for (int i=3; i<1000000; i++)
        {
            if (isFactorialSum(i))
            {
                total += i;
            }
        }

        System.out.println(total);
    }

    private static boolean isFactorialSum(int number)
    {
        int orig = number;
        int total = 0;
        while (number > 0)
        {
            total += factorials[number%10];
            if (total > orig)
            {
                return false;
            }
            number /= 10;
        }

        return orig == total;
    }
}
