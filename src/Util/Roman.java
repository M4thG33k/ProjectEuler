package Util;

import java.util.ArrayList;
import java.util.List;

public class Roman {

    private List<Integer> decimal = new ArrayList<>();
    private List<String> roman = new ArrayList<>();

    public Roman(){
        addPair(1000, "M");
        addPair(900, "CM");
        addPair(500, "D");
        addPair(400, "CD");
        addPair(100, "C");
        addPair(90, "XC");
        addPair(50, "L");
        addPair(40, "XL");
        addPair(10, "X");
        addPair(9, "IX");
        addPair(5, "V");
        addPair(4, "IV");
        addPair(1, "I");
    }

    private void addPair(int dec, String rom){
        decimal.add(dec);
        roman.add(rom);
    }

    public String toRoman(int val){
        StringBuilder builder = new StringBuilder();
        int i=0;
        while (val > 0){
            while (val < decimal.get(i)){
                i += 1;
            }
            while (val >= decimal.get(i)){
                builder.append(roman.get(i));
                val -= decimal.get(i);
            }
        }
        return builder.toString();
    }

    public int toDecimal(String val){
        int answer = 0;
        int i=0;
        while (i < val.length()){
            // If there are at least two characters left
            if (i+1 < val.length()){
                // And the two characters we're looking at are in our list
                if (roman.contains(val.substring(i, i+2))){
                    answer += decimal.get(roman.indexOf(val.substring(i, i+2)));
                    i += 2;
                    continue;
                }
            }
            // Otherwise, we have one digit to look at
            answer += decimal.get(roman.indexOf(val.substring(i, i+1)));
            i += 1;
        }

        return answer;
    }
}
