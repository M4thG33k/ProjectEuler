package Problems51_60;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem52 {

    public static void main(String[] args) {
        long value = 1;

        while (true)
        {
            boolean isvalid = true;
            String base = orderDigits(value);
            for (int i=2; i<7; i++)
            {
                if (!base.equals(orderDigits(value*i)))
                {
                    isvalid = false;
                    break;
                }
            }
            if (isvalid)
            {
                break;
            }
            value += 1;
        }

        System.out.println(value);
    }

    private static String orderDigits(long value)
    {
        List<Character> chars = new ArrayList<>();
        for (char c : (""+value).toCharArray())
        {
            chars.add(c);
        }

        Collections.sort(chars);

        StringBuilder builder = new StringBuilder();
        builder.append(chars);

        return builder.toString();
    }
}
