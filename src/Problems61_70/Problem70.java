package Problems61_70;

import Util.Prime;

import java.util.*;

public class Problem70 {

    public static List<Long> plist = null;
    public static Set<Long> pset = null;

    public static void main(String[] args) {
        plist = Prime.primesStrictlyBelow(10000000);
        pset = new HashSet<>(plist);
        double min = -1;
        double answer = 0;

        int phi;
        double val;
        for (int i=2; i<10000000; i++){
            phi = getPhi(i);
            if (arePerm(i, phi)){
                val = (double)(i)/((double)(phi));
                if (min==-1 || val<min){
                    min = val;
                    answer = i;
                }
            }
        }
        System.out.println(answer);
    }

    public static int getPhi(int number){
        if (pset.contains((long)number)){
            return number-1;
        }
        double phi = (double)number;
        double sqrt = Math.sqrt(number);
        for (Long p: plist){
            if (p>sqrt){
                break;
            }
            if (number%p==0){
                phi *= 1 - (1/(double)p);
                long q = number / p;
                if (pset.contains(q) && p < q){
                    phi *= 1 - (1/(double)q);
                }
            }
        }
        return (int)Math.round(phi);
    }

    static boolean arePerm(int first, int second){
        String f = "" + first;
        String s = "" + second;
        if (f.length() != s.length()){
            return false;
        }

        char[] fchar = f.toCharArray();
        char[] schar = s.toCharArray();
        Arrays.sort(fchar);
        Arrays.sort(schar);
        return Arrays.equals(fchar, schar);
    }
}
