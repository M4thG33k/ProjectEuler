package Problems61_70;

import java.util.ArrayList;
import java.util.List;

public class Problem63 {

    public static void main(String[] args) {

        //count 1 since it is a 1-digit number that is a 1st power
        int count = 1;

        // won't hold for any numbers larger than 10 (since 10^n has [n+1] digits)
        for (int base = 2; base < 10; base++)
        {
            LargeNumber value = new LargeNumber(base);
            int digits = 1;
            int flag = value.size() - digits;
            while (flag <= 0)
            {
                if (flag == 0)
                {
                    System.out.println(base+"^"+digits+" = "+value);
                    count += 1;
                }
                if (flag < -10)
                {
                    break;
                }
                value.mult();
                digits += 1;
                flag = value.size() - digits;
            }
        }

        System.out.println(count);

    }

    private static class LargeNumber
    {
        private List<Integer> vals = new ArrayList<>();
        private int base;

        LargeNumber(int digit)
        {
            base = digit;
            vals.add(digit);
        }

        void mult()
        {
            List<Integer> list = new ArrayList<>();
            int carry = 0;
            for (int val : vals)
            {
                int prod = val * base + carry;
                list.add(prod%10);
                carry = prod/10;
            }

            while (carry > 0)
            {
                list.add(carry%10);
                carry /= 10;
            }

            this.vals = list;
        }

        public int size()
        {
            return vals.size();
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (int i:vals)
            {
                builder.append(i);
            }
            return builder.reverse().toString();
        }
    }
}
