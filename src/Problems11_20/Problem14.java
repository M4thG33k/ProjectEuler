package Problems11_20;

public class Problem14 {

    public static void main(String[] args) {

        Collatz sequence = new Collatz();
        long max = 0;
        long answer = 0;
        long val;
        for (int i=1; i < 1000000; i++)
        {
            val = sequence.lengthOf(i);
            if (val > max)
            {
                answer = i;
                max = val;
            }
        }

        System.out.println(answer);
    }
}
