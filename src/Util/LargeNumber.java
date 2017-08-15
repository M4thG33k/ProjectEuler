package Util;

import java.util.ArrayList;
import java.util.List;

public class LargeNumber
{
    private List<Integer> vals = new ArrayList<>();

    public LargeNumber(int number)
    {
        while (number > 0)
        {
            vals.add(number%10);
            number /= 10;
        }

        if (vals.size() == 0)
        {
            vals.add(0);
        }
    }

    public LargeNumber(LargeNumber old)
    {
        this.vals.addAll(old.vals);
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

    public void times(long value)
    {
        List<Integer> newList = new ArrayList<>();
        long carry = 0;
        long temp;
        for (int digit : this.vals)
        {
            temp = carry + digit * value;
            newList.add((int)temp%10);
            carry = temp/10;
        }

        while (carry > 0)
        {
            newList.add((int)carry%10);
            carry /= 10;
        }

        this.vals = newList;
    }

    public void times10toThe(int n)
    {
        for (int i=0; i<n; i++)
        {
            this.vals.add(0, 0);
        }
    }

    public void times(LargeNumber other)
    {
        LargeNumber newNumber = new LargeNumber(0);

        LargeNumber temp;
        int i = 0;
        for (int digit : other.vals)
        {
            temp = new LargeNumber(this);
            temp.times(digit);
            temp.times10toThe(i);
            newNumber.add(temp);
            i += 1;
        }

        this.vals = newNumber.vals;
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

    public long digitSum()
    {
        long total = 0;
        for (int val : vals)
        {
            total += val;
        }
        return total;
    }

    public LargeNumber squared()
    {
        LargeNumber ret = new LargeNumber(this);
        ret.times(ret);
        return ret;
    }

    public boolean isGreaterThan(LargeNumber other)
    {
        int flag = Integer.compare(this.vals.size(), other.vals.size());
        if (flag != 0)
        {
            return flag > 0;
        }

        for (int i=this.vals.size()-1; i >= 0; i--)
        {
            flag = Integer.compare(this.vals.get(i), other.vals.get(i));
            if (flag != 0)
            {
                return flag > 0;
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LargeNumber))
        {
            return false;
        }

        if (this.vals.size() != ((LargeNumber) obj).vals.size())
        {
            return false;
        }

        for (int i=0; i<this.vals.size(); i++)
        {
            if (!this.vals.get(i).equals(((LargeNumber) obj).vals.get(i)))
            {
                return false;
            }
        }

        return true;
    }
}