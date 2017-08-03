package Problems41_50;

import Util.Prime;

public class Problem47 {

    public static void main(String[] args) {

        int factors_wanted = 4;
        int consec_needed = 4;

        long number = 2;
        int consec;
        boolean doWork = true;

        while (doWork)
        {
            consec = 0;
            while (Prime.primeFactorMap(number).size() == factors_wanted)
            {
                consec += 1;
                number += 1;

                if (consec == consec_needed)
                {
                    System.out.println(number - consec);
                    doWork = false;
                    break;
                }
            }
            number += 1;
        }
    }
}
