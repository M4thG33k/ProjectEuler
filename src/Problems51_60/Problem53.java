package Problems51_60;

public class Problem53 {

    public static void main(String[] args) {
        long total = 0L;

        boolean isOdd = true;
        int maxR;
        long val;
        int r = 0;
        for (int n=23; n<101; n++)
        {
            maxR = n/2;
            val = 1;
            r = 0;
            while (val <= 1000000L && r < maxR)
            {
                r += 1;
                val = (val * (n-r+1)) / r;
            }

            if (val > 1000000L)
            {
                total += (2 * (maxR-r)) + (isOdd ? 2 : 1);
            }

            isOdd = !isOdd;
        }

        System.out.println(total);
    }
}
