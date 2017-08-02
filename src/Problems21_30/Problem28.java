package Problems21_30;

public class Problem28 {

    public static void main(String[] args) {

        /* Notice that the upper-right diagonal consists of power of odd numbers.
            Also note that if we are in the nth "ring" (with n>=1; n=0 is the center),
            the perfect square along this diagonal is (2n+1)^2 and the sum of this
            "ring" is:
                [(2n+1)^2] + [(2n+1)^2 - 2n] + [(2n+1)^2 - 4n] + [(2n+1)^2 - 6n]
                    = 4 * (2n+1)^2 - 12n
                    = 4(4n^2 + 4n + 1) - 12n
                    = 16n^2 + 4n + 4
                    = 4n (4n + 1) + 4
         */
        long total = 1;
        int matrix_size = 1001;
        for (int i = 1; i <= (matrix_size-1)/2; i++)
        {
            total += 4 * i * (4*i + 1) + 4;
        }

        System.out.println(total);
    }
}
