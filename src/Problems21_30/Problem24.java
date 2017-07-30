package Problems21_30;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem24 {

    private static int[] factorial;

    public static void main(String[] args) {
        init(10);
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println(getNthPermOf(1000000 - 1, numbers)); //subtract 1 since the "first" permutation is when n=0
    }

    private static void init(int n)
    {
        factorial = new int[n+1];
        factorial[0] = 1;
        for (int i=1; i < n+1; i++)
        {
            factorial[i] = i * factorial[i-1];
        }
    }

    public static List<Integer> getNthPermOf(int n, List<Integer> objects)
    {
        if (n==0)
        {
            return objects;
        }

        int index = n / factorial[objects.size() - 1];
        List<Integer> ret = new ArrayList<>(objects.size());
        ret.add(objects.get(index));

        List<Integer> rest = new ArrayList<>(objects.size()-1);
        for (int i=0; i<objects.size(); i++)
        {
            if (i != index)
            {
                rest.add(objects.get(i));
            }
        }

        ret.addAll(getNthPermOf(n - index*factorial[objects.size()-1], rest));

        return ret;
    }
}
