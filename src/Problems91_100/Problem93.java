package Problems91_100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            int chain = 1;
            while (canFormWith(chain, digits)) {
                chain += 1;
            }
            if (chain > longestChain) {
                longestChain = chain;
                answer = String.format("%d%d%d%d", digits.get(0), digits.get(1), digits.get(2), digits.get(3));
            }
        } while (incrementDigits());

        System.out.println(answer);
        System.out.println(longestChain);


    }

    private static boolean incrementDigits() {
        int i = 3;
        digits.set(i, digits.get(i) + 1);
        while (i > 0 && digits.get(i) > 6 + i) {
            digits.set(i - 1, digits.get(i - 1) + 1);
            i -= 1;
        }

        if (i > 0 || digits.get(0) < 7) {
            i += 1;
            while (i < 4) {
                digits.set(i, digits.get(i - 1) + 1);
                i += 1;
            }
            return true;
        } else {
            return false;
        }

    }

    private static boolean canFormWith(double target, List<Integer> vals) {
        if (vals.size() == 0) {
            return false;
        }
        if (vals.size() == 1) {
            return target == vals.get(0);
        }

        boolean flag;
        for (int i = 0; i < vals.size(); i++) {
            int val = vals.get(i);
            List<Integer> remainder = new ArrayList<>(vals);
            remainder.remove(i);

            if (canFormWith(target - val, remainder)) {
                return true;
            }
            if (canFormWith(target + val, remainder)) {
                return true;
            }
            if (val != 0 && canFormWith(target * val, remainder)) {
                return true;
            }
            if (val != 0 && canFormWith(target / val, remainder)) {
                return true;
            }
        }
        if (vals.size()==4) {
            return canFormWith2And2(target, vals);
        }
        return false;
    }

    private static boolean canFormWith2And2(double target, List<Integer> vals) {
        int[][] combos = {{0, 1, 2, 3}, {0, 2, 1, 3}, {0, 3, 1, 2}};
        for (int[] comb : combos) {
            for (double a : getPossibilities(vals.get(comb[0]), vals.get(comb[1]))) {
                for (double b : getPossibilities(vals.get(comb[2]), vals.get(comb[3]))) {
                    if (canFormWithDuet(target, a, b)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static Set<Double> getPossibilities(int a, int b) {
        Set<Double> poss = new HashSet<>();
        poss.add((double) a + b);
        poss.add((double) a - b);
        poss.add((double) b - a);
        poss.add((double) a * b);
        if (a != 0) {
            poss.add((double) b / a);
        }
        if (b != 0) {
            poss.add((double) a / b);
        }

        return poss;
    }

    private static boolean canFormWithDuet(double target, double a, double b) {
        if (a == 0 || b == 0) {
            return (target == a || target == b);
        }
        return target == a + b || target == a - b || target == b - a || target == a * b || target == a / ((double) b) || target == b / ((double) a);

    }
}
