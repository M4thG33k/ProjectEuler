package Problems41_50;

public class Problem48 {

    public static void main(String[] args) {
        long total = 0;
        for (long i = 1L; i <= 1000L; i++)
        {
            total = (total + getLastDigits(i)) % 10000000000L;
        }
        System.out.println(total);
    }

    private static long getLastDigits(long value)
    {
        long ret = 1;

        for (int i=0; i<value; i++)

        {
            ret = (ret * value) % 10000000000L;
            if (ret == 0)
            {
                return 0;
            }
        }

        return ret;
    }
}
