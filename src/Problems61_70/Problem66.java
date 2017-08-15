package Problems61_70;

import Util.LargeNumber;
import Util.LargeNumberFraction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem66 {
    private static Set<Integer> squares = new HashSet<>();

    // This problem borrows a lot of work from Problem 64. Look up Pell's equation!

    public static void main(String[] args) {
        for (int i=1; i<=100; i++)
        {
            squares.add(i*i);
        }


        LargeNumber largeX = new LargeNumber(0);
        int largeD = 0;

        LargeNumber temp;
        for (int D = 2; D<1001; D++)
        {
            temp = getMinimumSolution(D);
            if (temp != null && temp.isGreaterThan(largeX))
            {
                largeX = temp;
                largeD = D;
            }
        }

        System.out.println(largeD);
    }

    private static boolean doesSatisfy(LargeNumber x, LargeNumber y, LargeNumber D)
    {
        LargeNumber Dy2 = y.squared();
        Dy2.times(D);
        Dy2.add(new LargeNumber(1));
        return x.squared().equals(Dy2);
    }

    private static LargeNumber getMinimumSolution(int D)
    {
        List<Integer> periodic = getPeriodic(D);
        if (periodic.size() == 0)
        {
            return null;
        }

        LargeNumber largeD = new LargeNumber(D);
        int i=0;

        while (true)
        {
            LargeNumberFraction frac = getFraction(periodic, i);
            if (doesSatisfy(frac.getNum(), frac.getDenom(), largeD))
            {
                return frac.getNum();
            }
            i += 1;
        }
    }

    private static LargeNumberFraction getFraction(List<Integer> values, int index)
    {
        List<Integer> realValues = new ArrayList<>(values);
        int j = 1;
        while (index >= realValues.size())
        {
            realValues.add(values.get(j));
            j += 1;
            if (j >= values.size())
            {
                j = 1;
            }
        }

        LargeNumberFraction frac = new LargeNumberFraction(realValues.get(index), 1);
        index -= 1;
        while (index >= 0)
        {
            frac.flip();
            frac.add(realValues.get(index));

            index -= 1;
        }

        return frac;
    }

    private static List<Integer> getPeriodic(int value)
    {
        if (squares.contains(value))
        {
            return new ArrayList<>();
        }

        int remainder = (int)Math.sqrt(value);
        int first = remainder;
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

        repeated.add(0, first);
        return repeated;
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
