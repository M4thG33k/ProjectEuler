package Problems31_40;

import java.util.HashSet;
import java.util.Set;

public class Problem31 {

    public static void main(String[] args) {
        String pattern = "%d#%d#%d#%d#%d#%d#%d";

        Set<String> combos = new HashSet<>();

        int t1, t2, t3, t4, t5, t6;
        int lb = 0;
        int fifty;
        int twenty;
        int ten;
        int five;
        int two;
        int one;

        while (lb < 3)
        {
            t1 = lb*100;
            if (t1 == 200)
            {
                combos.add(String.format(pattern, lb, 0, 0, 0, 0, 0, 0));
                break;
            }
            fifty = 0;
            while (fifty < 5)
            {
                t2 = fifty * 50;
                if (t1+t2 == 200)
                {
                    combos.add(String.format(pattern, lb, fifty, 0, 0, 0, 0, 0));
                    break;
                }
                else if (t1+t2 > 200)
                {
                    break;
                }

                twenty = 0;
                while (twenty < 11)
                {
                    t3 = twenty * 20;
                    if (t1+t2+t3 >= 200)
                    {
                        if (t1+t2+t3 == 200)
                        {
                            combos.add(String.format(pattern, lb, fifty, twenty, 0, 0, 0, 0));
                            break;
                        }
                    }

                    ten = 0;
                    while (ten < 21)
                    {
                        t4 = ten * 10;
                        if (t1+t2+t3+t4 >= 200)
                        {
                            if (t1+t2+t3+t4 == 200)
                            {
                                combos.add(String.format(pattern, lb, fifty, twenty, ten, 0, 0, 0));
                                break;
                            }
                        }
                        five = 0;
                        while (five < 41)
                        {
                            t5 = five*5;
                            if (t1+t2+t3+t4+t5 >= 200)
                            {
                                if (t1+t2+t3+t4+t5 == 200)
                                {
                                    combos.add(String.format(pattern, lb, fifty, twenty, ten, five, 0, 0));
                                    break;
                                }
                            }
                            two = 0;
                            while (two < 101)
                            {
                                t6 = two*2;
                                one = 200-t6-t5-t4-t3-t2-t1;
                                if (one < 0)
                                {
                                    break;
                                }
                                combos.add(String.format(pattern, lb, fifty, twenty, ten, five, two, one));
                                two += 1;
                            }

                            five += 1;
                        }

                        ten += 1;
                    }
                    twenty += 1;
                }
                fifty += 1;
            }
            lb += 1;
        }

        // add one because we didn't include just using the 2-pound coin
        System.out.println(combos.size() + 1);
    }
}
