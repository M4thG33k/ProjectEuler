package Problems31_40;

public class Problem39 {

    public static void main(String[] args) {
        int max_sols = 0;
        int answer = 0;

        for (int p=5; p<=1000; p++)
        {
            int num = numSolutions(p);
            if (num > max_sols)
            {
                max_sols = num;
                answer = p;
            }
        }
        System.out.println(answer);
    }

    private static int numSolutions(int p)
    {
        int num = 0;
        for (int a = 1; a<p; a++)
        {
            for (int b=a; b<p; b++)
            {
                double c = Math.sqrt(a*a + b*b);
                if ((int)c != c)
                {
                    continue;
                }
                if (a+b+c > p)
                {
                    break;
                }
                if (a+b+c == p)
                {
                    num += 1;
                    break;
                }
            }
        }

        return num;
    }
}
