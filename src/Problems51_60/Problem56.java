package Problems51_60;

import java.util.ArrayList;
import java.util.List;

public class Problem56 {

    public static void main(String[] args) {
        int max_sum = 0;
        int total;
        for (int a=1; a<100; a++)
        {
            for (int b=1; b<100; b++)
            {
                LargeNumber temp = new LargeNumber(a, b);
                total = temp.digitSum();
                if (total > max_sum)
                {
                    max_sum = total;
                }
            }
        }

        System.out.println(max_sum);
    }

    private static class LargeNumber
    {
        private List<Integer> vals;
        private int base;

        public LargeNumber(int base, int exp)
        {
            vals = new ArrayList<>();
            vals.add(1);

            this.base = base;

            for (int i=0; i<exp; i++)
            {
                this.timesBase();
            }
        }

        public void timesBase()
        {
            int temp;
            int carry = 0;
            for (int  i=0; i<vals.size(); i++)
            {
                temp = carry + (base * vals.get(i));
                vals.set(i, temp%10);
                carry = temp / 10;
            }

            while (carry > 0)
            {
                vals.add(carry%10);
                carry /= 10;
            }
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

        public int digitSum()
        {
            int total = 0;
            for (int i : vals)
            {
                total += i;
            }
            return total;
        }
    }
}
