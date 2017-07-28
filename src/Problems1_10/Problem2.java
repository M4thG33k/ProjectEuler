package Problems1_10;

public class Problem2 {

    public static void main(String[] args) {

        // We want to stop running when we reach 4 million = 4e6 = 4x10^6
        int max_val = (int)4e6;

        // set up first two Fibonacci numbers
        int f1 = 1;
        int f2 = 2;

        // Initialize the total with the first even F-number: 2
        int total = 2;

        // Get the next F-number
        int f3 = f1 + f2;
        while (f3 <= max_val)
        {
            // If even (bit-wise AND with 1 returns 1 if odd and 0 if even - could have used %2 instead)
            if ((f3&1)==0)
            {
                total += f3;
            }

            // shift the F-numbers down and get the new number
            f1 = f2;
            f2 = f3;
            f3 = f1 + f2;
        }

        System.out.println(total);
    }
}
