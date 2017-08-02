package Problems21_30;

import java.util.HashSet;
import java.util.Set;

public class Problem29 {

    public static void main(String[] args) {
        Set<Double> values = new HashSet<>();

        for (int a=2; a < 101; a++)
        {
            for (int b=2; b < 101; b++)
            {
                values.add(Math.pow(a, b));
            }
        }

        System.out.println(values.size());
    }
}
