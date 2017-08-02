package Problems31_40;

import java.util.HashSet;
import java.util.Set;

public class Problem33 {

    private static String pattern = "%d/%d";


    public static void main(String[] args) {
        int n=1, d=1;
        for (int num=10; num<100; num++)
        {
            for (int denom=num+1; denom<100; denom++)
            {
                if (isCandidate(num, denom))
                {
                    String reduced = reduce(num, denom);
                    if (reduced.length() == 3 && getCancelled(num, denom).contains(reduced))
                    {
                        n *= num;
                        d *= denom;
                    }
                }
            }
        }

        System.out.println(reduce(n, d));

    }


    private static boolean isCandidate(int num, int denom)
    {
        return (num%10!=0 && denom%10!=0) && (num%10 == denom/10 || num/10 == denom%10);
    }

    private static String reduce(int num, int denom)
    {
        int n = 2;
        while (n <= num)
        {
            while (num%n==0 && denom%n==0)
            {
                num /= n;
                denom /= n;
            }
            n += 1;
        }

        return String.format(pattern, num, denom);
    }

    private static Set<String> getCancelled(int num, int denom)
    {
        Set<String> ret = new HashSet<>(2);
        if (num/10 == denom%10)
        {
            ret.add(reduce(num%10, denom/10));
        }
        if (num%10 == denom/10)
        {
            ret.add(reduce(num/10, denom%10));
        }

        return ret;
    }
}
