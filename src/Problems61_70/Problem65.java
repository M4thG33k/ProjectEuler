package Problems61_70;

import Util.LargeNumberFraction;

public class Problem65 {

    public static void main(String[] args) {
        // we really want one less since we're 0-indexed
        int term = 99;

        LargeNumberFraction frac = new LargeNumberFraction(getValueForTerm(term), 1);
        term -= 1;
        while (term >= 0)
        {
            frac.flip();
            frac.add(getValueForTerm(term));
            term -= 1;
        }

        System.out.println(frac.getNum().digitSum());
    }

    private static int getValueForTerm(int n)
    {
        if (n == 0)
        {
            return 2;
        }

        switch (n%3)
        {
            case 0:
            case 1:
                return 1;
            default:
                return ((n-2)/3 + 1) * 2;
        }
    }


}
