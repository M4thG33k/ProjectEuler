package Problems61_70;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Problem67 {



    public static void main(String[] args) {
        List<List<Long>> pyramid = new ArrayList<>();

        // Let's load the data!
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("p067_triangle.txt"));
            String line;
            while ((line = reader.readLine()) != null)
            {
                List<Long> integers = new ArrayList<>();
                for (String integer :
                        line.split(" ")) {
                    integers.add(Long.parseLong(integer));
                }
                pyramid.add(integers);
            }
            reader.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // And now we use the same idea from Problem 18!
        if (pyramid.size() > 0)
        {
            for (int i=pyramid.size()-2; i>=0; i--)
            {
                for (int j=0; j<pyramid.get(i).size(); j++)
                {
                    pyramid.get(i).set(j, pyramid.get(i).get(j) + Math.max(pyramid.get(i+1).get(j), pyramid.get(i+1).get(j+1)));
                }
            }
        }

        System.out.println(pyramid.get(0).get(0));
    }
}
