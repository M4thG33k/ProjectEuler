package Problems81_90;

import Util.Roman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem89 {

    private static Roman roman = new Roman();
    private static List<String> data;

    public static void main(String[] args) {
        loadFile("files/p089_roman.txt");

        int numSaved = 0;

        for (String number: data){
            numSaved += number.length() - roman.toRoman(roman.toDecimal(number)).length();
        }

        System.out.println(numSaved);
    }

    protected static void loadFile(String fname){
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(fname));
            Stream<String> lines = reader.lines();
            List<String> theLines = lines.collect(Collectors.toList());

            data = new ArrayList<>();
            for (String line: theLines){
                if (line.length() == 0){
                    continue;
                }
                data.add(line);
            }
        } catch (IOException e){
            System.out.println("IOException");
        }
    }
}
