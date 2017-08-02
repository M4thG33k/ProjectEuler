package Problems41_50;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class Problem42 {

    private static long maxT = 1;
    private static long dt = 2;
    private static Set<Long> tris;


    public static void main(String[] args) {
        try
        {
            tris = new HashSet<>();
            tris.add(1L);

            int tWords = 0;
            BufferedReader reader = new BufferedReader(new FileReader("p042_words.txt"));

            String[] words = reader.readLine().split(",");

            for (String word : words)
            {
                if (isTriangular(getScore(word)))
                {
                    tWords += 1;
                }
            }

            reader.close();

            System.out.println(tWords);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static long getScore(String word)
    {
        word = word.substring(1, word.length()-1);
        long total = 0;
        for (char c : word.toCharArray())
        {
            total += ((int)c) - 64;
        }

        return total;
    }

    private static boolean isTriangular(long value)
    {
        while (maxT < value)
        {
            maxT += dt;
            dt += 1;
            tris.add(maxT);
        }

        return tris.contains(value);
    }
}
