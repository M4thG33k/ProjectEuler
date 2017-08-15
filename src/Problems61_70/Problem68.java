package Problems61_70;

import Util.Permutations;

import java.util.ArrayList;
import java.util.List;

public class Problem68 {

    private static List<Integer> init_nodes = new ArrayList<>();
    private static List<Integer> nodes;
    private static long maxString = 0;

    public static void main(String[] args) {
        for (int i=1; i<11; i++)
        {
            init_nodes.add(i);
        }

        for (long i=0; i < Permutations.factorial(init_nodes.size()); i++)
        {
            nodes = Permutations.permutation(i, init_nodes);
            long string = generateString();
            if (string > maxString && string < 10000000000000000L)
            {
                maxString = string;
            }
        }

        System.out.println(maxString);
    }

    private static int[] getLine(int wheel)
    {
        if (wheel == 4)
        {
            return new int[]{nodes.get(4), nodes.get(9), nodes.get(5)};
        }

        return new int[]{nodes.get(wheel), nodes.get(wheel+5), nodes.get(wheel+6)};
    }

    private static List<int[]> getAll()
    {
        List<int[]> lines = new ArrayList<>();
        int smallest = 11;
        int smallestI = 0;
        int big_total = 0;
        int total;
        for (int i=0; i<5; i++)
        {
            int[] line = getLine(i);
            total = 0;
            for (int val : line)
            {
                total += val;
            }
            if (big_total == 0)
            {
                big_total = total;
            }
            else if (total != big_total)
            {
                return null;
            }
            if (line[0] < smallest)
            {
                smallest = line[0];
                smallestI = i;
            }
            lines.add(line);
        }

        List<int[]> ret = new ArrayList<>();
        for (int i=0; i<5; i++)
        {
            ret.add(lines.get((smallestI+i)%5));
        }

        return ret;
    }

    private static long generateString()
    {
        List<int[]> lines = getAll();
        if (lines == null)
        {
            return 0;
        }

        String ret = "";
        for (int[] line : lines)
        {
            for (int val : line)
            {
                ret += val;
            }
        }
        return Long.parseLong(ret);
    }

    private static void iterate()
    {
        nodes.set(9, nodes.get(9)+1);
        for (int i=9; i>=0; i--)
        {
            if (nodes.get(i) == 11)
            {
                if (i == 0)
                {
                    nodes.set(i, -1);
                    return;
                }
                nodes.set(i, 1);
                nodes.set(i-1, nodes.get(i-1) + 1);
            }
            else
            {
                return;
            }
        }
    }
}
