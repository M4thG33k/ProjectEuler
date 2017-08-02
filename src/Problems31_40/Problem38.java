package Problems31_40;

public class Problem38 {

    public static void main(String[] args) {
        int max_val = 0;
        for (int i=1; i<9877; i++)
        {
            if (isValid(i))
            {
                int comp = getComposition(i);
                if (comp > max_val)
                {
                    max_val = comp;
                }
            }
        }

        System.out.println(max_val);
    }

    private static int getComposition(int start)
    {
        int n=1;
        StringBuilder builder = new StringBuilder();
        int size = 0;
        while (size != 9)
        {
            builder.append(start*n);
            size += (""+(start*n)).length();
            n += 1;
        }

        return Integer.parseInt(builder.toString());
    }

    private static boolean isValid(int start)
    {
        boolean[] digits = new boolean[10];

        int val = start;
        int digit;
        while (val > 0)
        {
            digit = val%10;
            if (digit == 0 || digits[digit])
            {
                return false;
            }
            digits[digit] = true;
            val /= 10;
        }

        //n=2
        val = start*2;
        while (val>0)
        {
            digit = val%10;
            if (digit == 0 || digits[digit])
            {
                return false;
            }
            digits[digit] = true;
            val /= 10;
        }

        int n=2;
        while (!isDone(digits))
        {
            n += 1;
            val = start * n;
            while (val>0)
            {
                digit = val%10;
                if (digit == 0 || digits[digit])
                {
                    return false;
                }
                digits[digit] = true;
                val /= 10;
            }
        }

        return true;
    }

    private static boolean isDone(boolean[] bools)
    {
        for (int i=1; i<bools.length; i++)
        {
            if (!bools[i])
            {
                return false;
            }
        }

        return true;
    }
}
