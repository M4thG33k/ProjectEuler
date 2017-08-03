package Problems41_50;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem44 {

    private static long max_pent = 1;
    private static int next_n = 2;
    private static Set<Long> pents;
    private static List<Long> pentList;

    public static void main(String[] args) {
        pentList = new ArrayList<>();
        pentList.add(1L);
        pents = new HashSet<>();
        pents.add(1L);

        long answer = 0L;
        boolean shouldContinue = true;
        int dIndex = 0;
        while (shouldContinue)
        {
            long D = getPent(dIndex);
            long dD = getPent(dIndex + 1) - D;
            int jIndex = 0;
            while (getPent(jIndex) < dD)
            {
                jIndex += 1;
            }
            for (int i = 0; i < 1000; i++)
            {
                long J = getPent(jIndex + i);
                if (isPentagonal(D+J) && isPentagonal(D+J+J))
                {
                    shouldContinue = false;
                    answer = D;
                    break;
                }
            }
            dIndex += 1;
        }
        System.out.println(answer);
    }

    private static long getPent(int index)
    {
        while (pentList.size() <= index)
        {
            max_pent = (next_n * (3*next_n -1)) / 2;
            next_n += 1;
            pentList.add(max_pent);
            pents.add(max_pent);
        }

        return pentList.get(index);
    }

    private static boolean isPentagonal(long val)
    {
        while (max_pent < val)
        {
            max_pent = (next_n*(3*next_n - 1)) / 2;
            pents.add(max_pent);
            pentList.add(max_pent);
            next_n += 1;
        }

        return pents.contains(val);
    }
}
