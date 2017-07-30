package Problems21_30;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem22 {

    public static void main(String[] args) {
        String raw_input = "";
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("p022_names.txt"));
            String line;
            while ((line = reader.readLine()) != null)
            {
                raw_input = line;
            }
            reader.close();

            List<String> names = new ArrayList<>(6000);
            for (String name : raw_input.split(","))
            {
                names.add(name.substring(1, name.length()-1));
            }

            Collections.sort(names);

            long total = 0;
            for (int i=0; i<names.size(); i++)
            {
                int charSum = 0;
                for (char chr : names.get(i).toCharArray())
                {
                    charSum += (int)chr - 64;
                }
                total += charSum * (i+1);
            }
            System.out.println(total);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
