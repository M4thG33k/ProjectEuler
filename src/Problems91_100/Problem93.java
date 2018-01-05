package Problems91_100;

import java.util.ArrayList;
import java.util.List;

public class Problem93 {

    private static List<Integer> digits;
    private static int longestChain = 0;
    private static String answer = "";

    public static void main(String[] args) {
        digits = new ArrayList<>();
        digits.add(0);
        digits.add(1);
        digits.add(2);
        digits.add(3);



        do {
            System.out.println(digits);
            int chain = 1;
            while (canFormWith(chain, digits)){
                chain += 1;
            }
            if (chain > longestChain){
                longestChain = chain;
                answer = String.format("%d%d%d%d", digits.get(0), digits.get(1), digits.get(2), digits.get(3));
            }
            System.out.println(chain);
        } while (incrementDigits());

        System.out.println(answer);
        System.out.println(longestChain);


    }

    private static boolean incrementDigits(){
        int i = 3;
        digits.set(i, digits.get(i)+1);
        while (i>0 && digits.get(i) > 6+i){
            digits.set(i-1, digits.get(i-1)+1);
            i -= 1;
        }

        if (i > 0 || digits.get(0) < 7){
            i += 1;
            while (i < 4){
                digits.set(i, digits.get(i-1)+1);
                i += 1;
            }
            return true;
        } else{
            return false;
        }

    }

    private static boolean canFormWith(double target, List<Integer> vals){
        if (vals.size() == 0){
            return false;
        }
        if (vals.size() == 1){
            return target == vals.get(0);
        }

        boolean flag;
        for (int i=0; i<vals.size(); i++){
            int val = vals.get(i);
            List<Integer> remainder = new ArrayList<>(vals);
            remainder.remove(i);

            if (canFormWith(target-val, remainder)){
                return true;
            }
            if (canFormWith(target+val, remainder)){
                return true;
            }
            if (val!=0 && canFormWith(target*val, remainder)){
                return true;
            }
            if (val!=0 && canFormWith(target/val, remainder)){
                return true;
            }
        }
        return false;
    }
}
