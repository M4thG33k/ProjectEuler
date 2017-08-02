package Problems21_30;

public class Problem30 {

    private static int[] powers;

    public static void main(String[] args) {
        powers = new int[10];
        for (int i=0; i<10; i++)
        {
            powers[i] = i*i*i*i*i;
        }

        long sum = 0L;

        // once we reach 7-digit numbers, all sums of 5th powers will
        // be smaller than the original number
        for (int i=10; i < 1000000; i++)
        {
            if (doesWork(i))
            {
                sum += i;
            }
        }

        System.out.println(sum);
    }

    private static boolean doesWork(int value)
    {
        int temp = value;
        int total = 0;
        while (temp > 0)
        {
            total += powers[temp%10];
            temp /= 10;
            if (total > value)
            {
                return false;
            }
        }

        return total == value;
    }
}
