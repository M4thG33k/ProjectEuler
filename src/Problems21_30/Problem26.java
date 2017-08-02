package Problems21_30;

public class Problem26 {
    public static void main(String[] argv)
    {
        int sequenceLength = 0;
        int answer = 0;

        for (int i=999; i>1; i--)
        {
            if (sequenceLength >= i)
            {
                break;
            }

            int remainder = 1;
            int[] values = new int[i];
            int position = 0;

            while (values[remainder] == 0 && remainder > 0)
            {
                values[remainder] = position;
                position += 1;

                remainder = (remainder*10) % i;
            }

            if (position - values[remainder] > sequenceLength)
            {
                sequenceLength = position - values[remainder];
                answer = i;
            }

        }

        System.out.println(answer);
    }
}
