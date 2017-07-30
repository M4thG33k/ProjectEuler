package Problems1_10;

public class Problem9 {

    /*
    Note: if a = 333, the smallest b can be is 334, and the smallest c can be is 335 => a+b+c=1002 which is too big,
     so a < 333.

     Once a is fixed, we can write diff := 1000 - a ==> b + c = diff.
     We also know that a < b and the smallest c can be is b+1, so b <= Floor((diff-1)/2)).

     Once a and b are fixed, c = 1000 - a - b. Then we check if a^2 + b^2 == c^2; if it does, we're done!
     */

    public static void main(String[] args) {
        boolean found = false;
        for (int a=1; a < 333; a++)
        {
            if (found)
            {
                break;
            }
            int diff = 1000 - a;

            for (int b = a+1; b <= (int)Math.floor((diff-1)/2); b++)
            {
                if (found)
                {
                    break;
                }
                int c = 1000 - a - b;
                if (c <= b)
                {
                    continue;
                }
                if ((a*a + b*b) == (c*c))
                {
                    found = true;
                    System.out.println(a*b*c);
                }
            }
        }
    }
}
