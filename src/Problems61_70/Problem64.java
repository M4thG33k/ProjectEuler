package Problems61_70;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem64 {

    private static Set<Integer> squares = new HashSet<>();

    // Hint: http://mathworld.wolfram.com/PeriodicContinuedFraction.html
    public static void main(String[] args) {
        for (int i=1; i<=100; i++)
        {
            squares.add(i*i);
        }

        int numOdd = 0;
        for (int i=2; i < 10001; i++)
        {
            if (getPeriod(i)%2 == 1)
            {
                numOdd += 1;
            }
        }

        System.out.println(numOdd);
    }

    private static int getPeriod(int value)
    {
        if (squares.contains(value))
        {
            return 0;
        }

        int remainder = (int)Math.sqrt(value);
        int need = 2*remainder;
        List<Integer> repeated = new ArrayList<>();
        Frac frac = new Frac(value, remainder);

        while (true)
        {
            frac.rationalize();
            remainder = frac.iterate();
            if (remainder == need)
            {
//                System.out.println("\t" + repeated);
                if (isPalindrome(repeated))
                {
                    repeated.add(remainder);
                    break;
                }
            }
            repeated.add(remainder);
            frac.recip();
        }
        return repeated.size();
//        return repeated.length();
    }

    private static boolean isPalindrome(List<Integer> values)
    {
        for (int i = 0; i < values.size()/2; i++)
        {
            if (!values.get(i).equals(values.get(values.size() - 1 - i)))
            {
                return false;
            }
        }
        return true;
//        StringBuilder builder = new StringBuilder(value);
//        return value.equals(builder.reverse().toString());
    }

    private static class Frac
    {
        int numC;
        int numP;
        int denomC;
        int denomP;

        int n;
        double sqrt;

        Frac(int n, int diff)
        {
            numC = 1;
            numP = 0;
            denomC = -diff;
            denomP = 1;

            this.n = n;
            this.sqrt = Math.sqrt(n);
        }

        public void rationalize()
        {
            if (denomP == 0)
            {
                return;
            }

            int tempDenom = denomP*denomP*n - denomC*denomC;
            for (int i=2; i<= Math.min(Math.abs(numC), Math.abs(tempDenom)); i++)
            {
                while (Math.abs(numC)%i==0 && Math.abs(tempDenom)%i==0)
                {
                    numC /= i;
                    tempDenom /= i;
                }
            }

            int tempNumP = numC * denomP;
            int tempNumC = numC * (-denomC);

            this.numC = tempNumC;
            this.numP = tempNumP;
            this.denomP = 0;
            this.denomC = tempDenom;
        }

        @Override
        public String toString() {
            return "( " + createPiece(numP, numC) + " ) / ( " + createPiece(denomP, denomC) + " )";
        }

        private String createPiece(int rat, int con)
        {
            String ret = "";
            if (rat != 0)
            {
                ret += (rat == 1 ? "" : rat + "*") + "Sqrt[" + n + "] + ";
            }
            ret += con;
            return ret;
        }

        int iterate()
        {
            int remainder = (int)((numP * sqrt + numC) / (denomP * sqrt + denomC));
            this.numC -= remainder*this.denomC;

            return remainder;
        }

        void recip()
        {
            int temp = numC;
            numC = denomC;
            denomC = temp;
            temp = numP;
            numP = denomP;
            denomP = temp;
        }
    }
}
