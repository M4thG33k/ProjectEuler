package Problems41_50;

public class Problem45 {

    private static int tn=2, pn=2, hn=2;

    public static void main(String[] args) {
        int found = 0;

        long T = 3;
        long P = 5;
        long H = 6;

        while (true)
        {
            if (T==P && T==H)
            {
                System.out.println(T);
                T = nextT();
                found += 1;

                if (found == 2)
                {
                    break;
                }
            }
            else if (T < P)
            {
                if (T < H)
                {
                    T = nextT();
                }
                else
                {
                    H = nextH();
                }
            }
            else if (T < H)
            {
                P = nextP();
            }
            else if (P < H)
            {
                P = nextP();
            }
            else
            {
                H = nextH();
            }
        }
    }

    private static long nextT()
    {
        tn += 1;
        return ((long)tn * (tn+1)) / 2;
    }

    private static long nextP()
    {
        pn += 1;
        return ((long)pn * (3*pn - 1)) / 2;
    }

    private static long nextH()
    {
        hn += 1;
        return ((long)hn * (2*hn - 1));
    }
}
