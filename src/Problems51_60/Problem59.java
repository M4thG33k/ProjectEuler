package Problems51_60;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem59 {

    private static int m1 = (int)'a';
    private static int M1 = (int)'z';
    private static int m2 = (int)'A';
    private static int M2 = (int)'Z';
    private static Set<Integer> allowed_punctuation = new HashSet<>();

    private static long total;

    public static void main(String[] args) {
        for (char c : new char[]{' ', ',', '.', '\'', '"', '(', ')', '?', '!', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';'})
        {
            allowed_punctuation.add((int)c);
        }
        try
        {
            List<Integer> values = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("p059_cipher.txt"));

            for (String val : reader.readLine().split(","))
            {
                values.add(Integer.parseInt(val));
            }

            for (int a : getValid(values.get(0)))
            {
                for (int b : getValid(values.get(1)))
                {
                    for (int c : getValid(values.get(2)))
                    {
                        StringBuilder builder = new StringBuilder();
                        builder.append((char)a);
                        builder.append((char)b);
                        builder.append((char)c);
                        String message = decrypt(new int[]{a, b, c}, values);
                        if (!message.equals(""))
                        {
                            System.out.println("Password: " + builder.toString());
                            System.out.println(total);
                            System.out.println(message);
                        }
                    }
                }
            }

            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private static String decrypt(int[] password, List<Integer> code)
    {
        int index = 0;

        total = 0;

        StringBuilder builder = new StringBuilder();

        int eCount = 0;

        for (int c : code)
        {
            int val = c ^ password[index];
            total += val;
            if (isValidChar(val)) {
                if (val == (int)'e')
                {
                    eCount += 1;
                }
                builder.append((char) val);
                index = (index + 1) % 3;
            }
        }

        String s = builder.toString();
        // Is is the most commonly used letter in the English language. This parameter was changed until
        // only one result was found.
        if (((double)eCount) / s.length() < 0.099)
        {
            return "";
        }

        return s;
    }

    private static List<Integer> getValid(int value)
    {
        List<Integer> valids= new ArrayList<>();
        for (int i=m1; i<=M1; i++)
        {
            int temp = value^i;
            if (isValidChar(temp))
            {
                valids.add(i);
            }
        }

        return valids;
    }

    private static boolean isValidChar(int val)
    {
        return allowed_punctuation.contains(val) || (((m1 <= val) && (val <= M1)) || ((m2 <= val) && (val <= M2)));
    }
}
