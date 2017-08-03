package Problems51_60;

import Util.Prime;

import java.util.ArrayList;
import java.util.List;

public class Problem57 {

//    private static List<Long> primes = Prime.primesStrictlyBelow(2000000);

    public static void main(String[] args) {
        Frac frac = new Frac(3, 2);
        int expansion = 0;

        int numTopHeavy = 0;

        while (expansion < 1000)
        {
            if (frac.isTopHeavy())
            {
                numTopHeavy += 1;
            }
            frac.iterate();
            expansion += 1;
        }
        System.out.println(numTopHeavy);
    }

    private static class LargeNumber
    {
        private List<Integer> vals = new ArrayList<>();

        public LargeNumber(int number)
        {
            while (number > 0)
            {
                vals.add(number%10);
                number /= 10;
            }
        }

        public void add(LargeNumber other)
        {
            List<Integer> result = new ArrayList<>();
            int i=0;
            int carry = 0;
            int temp;
            while (i < Math.min(other.vals.size(), this.vals.size()))
            {
                temp = carry + other.vals.get(i) + this.vals.get(i);
                result.add(temp%10);
                carry = temp / 10;
                i += 1;
            }
            while (i < Math.max(other.vals.size(), this.vals.size()))
            {
                if (i < other.vals.size())
                {
                    temp = carry + other.vals.get(i);
                }
                else
                {
                    temp = carry + this.vals.get(i);
                }

                result.add(temp%10);
                carry = temp / 10;
                i += 1;
            }

            while (carry > 0)
            {
                result.add(carry%10);
                carry /= 10;
            }

            this.vals = result;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int i : vals)
            {
                builder.append(i);
            }

            return builder.reverse().toString();
        }
    }

    private static class Frac
    {
        private LargeNumber num;
        private LargeNumber denom;

        public Frac(int num, int denom)
        {
            this.num = new LargeNumber(num);
            this.denom = new LargeNumber(denom);
        }

        private void recip()
        {
            LargeNumber temp = this.num;
            this.num = this.denom;
            this.denom = temp;
        }

        private void addOne()
        {
            this.num.add(this.denom);
        }

        @Override
        public String toString() {
            return num + "/" + denom;
        }

        public void iterate()
        {
//            System.out.println("\t" + "Adding");
            this.addOne();
//            System.out.println("\t" + "Reciprocating");
            this.recip();
//            System.out.println("\t" + "Adding");
            this.addOne();
//            System.out.println("\t" + "Reducing");
//            this.reduce();
        }

        public boolean isTopHeavy()
        {
            return this.num.vals.size() > this.denom.vals.size();
        }
    }
}
