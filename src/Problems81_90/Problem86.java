package Problems81_90;

public class Problem86 {

    public static void main(String[] args) {
        int M = 0;
        int total = 0;

        while (total < 1000000){
            M += 1;
            for (int a=1; a<=M; a++){
                for (int b=a; b<=M; b++){
                    total += calcPythag(a, b, M);
                }
            }
        }

        System.out.println(M);
        System.out.println(total);
    }

    private static int calcPythag(long a, long b, long c){
        long val = Math.min(Math.min(a*a+(b+c)*(b+c), (a+b)*(a+b)+c*c), (a+c)*(a+c)+b*b);
        double sqrt = Math.sqrt(val);
        return sqrt == (int)sqrt ? 1 : 0;
    }
}
