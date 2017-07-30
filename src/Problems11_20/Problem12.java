package Problems11_20;

import Util.Prime;

public class Problem12 {

    public static void main(String[] args) {
        long t = 1;
        long dt = 2;

        long numFact = Prime.numDivisors(t);

        while (numFact <= 500)
        {
            t += dt;
            dt += 1;
            numFact = Prime.numDivisors(t);
        }

        System.out.println(t);
    }
}
