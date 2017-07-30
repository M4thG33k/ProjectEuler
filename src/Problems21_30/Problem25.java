package Problems21_30;

import java.util.ArrayList;
import java.util.List;

public class Problem25 {

    public static void main(String[] args) {
        int maxDigits = 1000;

        LargeNumber f1 = new LargeNumber();
        LargeNumber f2 = new LargeNumber();

        int index = 3;
        LargeNumber f3 = LargeNumber.add(f1, f2);

        while (f3.numDigits() < maxDigits)
        {
            f1 = f2;
            f2 = f3;
            f3 = LargeNumber.add(f1, f2);
            index += 1;
        }

        System.out.println(index);

    }

    private static class LargeNumber
    {
        private List<Integer> vals;

        public LargeNumber()
        {
            vals = new ArrayList<>();
            vals.add(1);
        }

        public static LargeNumber add(LargeNumber first, LargeNumber second)
        {
            int carry = 0;
            LargeNumber ret = new LargeNumber();
            ret.vals = new ArrayList<>();

            int val = 0;
            for (int i=0; i < Math.max(first.vals.size(), second.vals.size()); i++)
            {
                val = carry;
                if (i < first.vals.size())
                {
                    val += first.vals.get(i);
                }
                if (i < second.vals.size())
                {
                    val += second.vals.get(i);
                }

                ret.vals.add(val%10);
                carry = val/10;
            }

            while (carry > 0)
            {
                ret.vals.add(carry%10);
                carry /= 10;
            }

            return ret;
        }

        public int numDigits()
        {
            return vals.size();
        }
    }
}
