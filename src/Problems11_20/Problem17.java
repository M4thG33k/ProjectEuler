package Problems11_20;

import java.util.HashMap;
import java.util.Map;

public class Problem17 {

    private static Map<Integer, Integer> onesAndTeens = new HashMap<>(20);
    private static Map<Integer, Integer> tens = new HashMap<>(8);

    private static int hundred = 7;
    private static int thousand = 8;
    private static int and = 3;

    public static void main(String[] args) {
        init();

        int total = 0;
        for (int i=1; i<= 1000; i++)
        {
            total += getLetterCount(i);
        }
        System.out.println(total);
    }

    public static void init() {
        onesAndTeens.put(0, 0);
        onesAndTeens.put(1, 3);
        onesAndTeens.put(2, 3);
        onesAndTeens.put(3, 5);
        onesAndTeens.put(4, 4);
        onesAndTeens.put(5, 4);
        onesAndTeens.put(6, 3);
        onesAndTeens.put(7, 5);
        onesAndTeens.put(8, 5);
        onesAndTeens.put(9, 4);
        onesAndTeens.put(10, 3);
        onesAndTeens.put(11, 6);
        onesAndTeens.put(12, 6);
        onesAndTeens.put(13, 8);
        onesAndTeens.put(14, 8);
        onesAndTeens.put(15, 7);
        onesAndTeens.put(16, 7);
        onesAndTeens.put(17, 9);
        onesAndTeens.put(18, 8);
        onesAndTeens.put(19, 8);

        tens.put(2, 6);
        tens.put(3, 6);
        tens.put(4, 5);
        tens.put(5, 5);
        tens.put(6, 5);
        tens.put(7, 7);
        tens.put(8, 6);
        tens.put(9, 6);
    }

    public static int getLetterCount(int number)
    {
        if (number < 20)
        {
            return onesAndTeens.get(number);
        }

        if (number < 100)
        {
            return tens.get(number/10) + getLetterCount(number%10);
        }

        if (number < 1000)
        {
            int lesser = getLetterCount(number%100);
            return onesAndTeens.get(number/100) + hundred + (lesser > 0 ? and : 0) + lesser;
        }

        else
        {
            return onesAndTeens.get(1) + thousand;
        }
    }
}
