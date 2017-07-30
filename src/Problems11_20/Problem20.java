package Problems11_20;

import java.util.ArrayList;
import java.util.List;

public class Problem20 {

    public static void main(String[] args) {
        LargeNumber number = new LargeNumber();

        for (int i=1; i <= 100; i++)
        {
            number.times(i);
        }

        System.out.println(number.sumOfDigits());
    }

    private static class LargeNumber
    {
        List<Integer> vals;

        public LargeNumber()
        {
            vals = new ArrayList<>();
            vals.add(1);
        }

        public void times(int num)
        {
            int carry = 0;
            for (int i=0; i<vals.size(); i++)
            {
                int val = vals.get(i)*num + carry;
                vals.set(i, val%10);
                carry = val / 10;
            }

            while (carry > 0)
            {
                vals.add(carry % 10);
                carry /= 10;
            }
        }

        @Override
        public String toString() {
            String ret = "";
            for (int val : vals)
            {
                ret = val + ret;
            }
            return ret;
        }

        public long sumOfDigits()
        {
            long total = 0L;
            for (int val : vals)
            {
                total += val;
            }
            return total;
        }
    }
}
