package Problems1_10;

public class Problem1 {

    public static void main(String[] args) {
        int[] FACTORS = new int[]{3, 5};
        int MAX = 1000;
        int total = 0;

        // Start at 1 and go up to MAX (exclusive)
        for (int i=1; i<MAX; i++)
        {
            // Iterate over each factor in our array
            for (int factor : FACTORS)
            {
                if (i%factor == 0) // If this is true, factor evenly divides i
                {
                    total += i;
                    break;
                }
            }
        }

        System.out.println(total);
    }
}
