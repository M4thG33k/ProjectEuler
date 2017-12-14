package Problems81_90;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem81 {

    protected static List<List<Integer>> data;

    // We recalculate the values starting from the bottom right corner, going up rows and then across columns (dynamic programming)
    // If we do this correctly, our answer should end up in the top left corner!
    public static void main(String[] args) {
        String filename = "files/p081_matrix.txt";

        loadFile(filename);

        for (int rowcol=data.size()-1; rowcol>=0; rowcol--){
            for (int row = rowcol; row>=0; row--){
                recalculateValue(row, rowcol);
            }
            // We don't need to recalculate <row, col> again
            for (int col = rowcol-1; col >= 0; col--){
                recalculateValue(rowcol, col);
            }
        }

        System.out.println(data.get(0).get(0));

    }

    protected static void loadFile(String fname){
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(fname));
            Stream<String> lines = reader.lines();
            List<String> theLines = lines.collect(Collectors.toList());

            data = new ArrayList<>();
            for (String line: theLines){
                List<Integer> lineData = new ArrayList<>();
                if (line.length() == 0){
                    continue;
                }
                for (String val : line.split(",")){
                    lineData.add(Integer.parseInt(val));
                }
                data.add(lineData);
            }
        } catch (IOException e){
            System.out.println("IOException");
        }
    }

    private static void recalculateValue(int row, int col){
        // If in the last row, only look right
        if (row == data.size()-1){
            if (col < data.size() -1){
                data.get(row).set(col, data.get(row).get(col)+data.get(row).get(col+1));
            }
        }
        // Otherwise, if in the last column, only look down (we know we have something below otherwise we wouldn't be in this block)
        else if (col == data.size()-1){
            data.get(row).set(col, data.get(row).get(col)+data.get(row+1).get(col));
        }
        // Otherwise we have an entry both to the right and below this entry; take the smaller of the two
        else {
            data.get(row).set(col, data.get(row).get(col) + Math.min(data.get(row).get(col+1), data.get(row+1).get(col)));
        }
    }
}
