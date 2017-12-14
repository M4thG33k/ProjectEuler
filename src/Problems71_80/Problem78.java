package Problems71_80;

import java.util.ArrayList;
import java.util.List;

public class Problem78 {

    private static List<Long> P = new ArrayList<>();

    // This problem is like the two before, with the only difference being we're allowing the sum to be made up of one
    // number (a group of 5 counts as a way of grouping 5 coins). Despite this, our previous methods are too slow for
    // this problem, so we need to adopt a new strategy based off of pentagonal numbers and Partitions from number theory
    // Also, since we're looking for a number whose P value%1000000 is zero, and each P value is defined as a sum of other
    // P values, we can apply the modulo operator to the P values, which gets rid of overflow issues. (Note: P values
    // may be negative because of the way Java implements the modulo operator, but this doesn't cause any issues.)
    public static void main(String[] args) {
        P.add(1L); // p(0) = 1

        while (P.get(P.size()-1)%1000000 != 0){
            long myVal = 0L;
            long val = (long)P.size();
            long m = 1;
            long p = pent(m);
            while (p <= val){
                if (m%2!=0){
                    myVal += P.get((int)(val-p));
                } else {
                    myVal -= P.get((int)(val-p));
                }
                if (m > 0){
                    m = -m;
                }else {
                    m = 1-m;
                }
                p = pent(m);

            }
            P.add(myVal%1000000);
            System.out.println(val + " ==> " + myVal);
        }
        System.out.println(P.size()-1);

    }


    // Get the val-th generalized pentagonal number
    private static long pent(long val){
        return (val * (3*val - 1)) / 2;
    }
}
