package Problems11_20;

import java.util.ArrayList;
import java.util.List;

public class Problem16 {

    public static void main(String[] args) {
        LargeNumber number = new LargeNumber();
        for (int i=0; i<1000; i++)
        {
            number.doubleIt();
        }

        System.out.println(number.sumOfDigits());
    }

    private static class LargeNumber {
        private List<Integer> vals;

        public LargeNumber() {
            vals = new ArrayList<>();
            vals.add(1);
        }

        public void doubleIt()
        {
            int temp;
            int carry = 0;
            for (int i=0; i<vals.size(); i++)
            {
                temp = vals.get(i)*2 + carry;
                vals.set(i, temp%10);
                carry = temp/10;
            }
            while (carry > 0)
            {
                vals.add(carry%10);
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

        public int sumOfDigits()
        {
            int total = 0;
            for (int val :
                    vals) {
                total += val;
            }

            return total;
        }
    }
}
