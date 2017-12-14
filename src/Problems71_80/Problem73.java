package Problems71_80;

public class Problem73 {

    public static void main(String[] args) {
        int maxDenom = 12000;
        long answer = 0;

        for (int d=4; d<=maxDenom; d++){
            // Get the lower bound for the numerator
            int n1 = (int)Math.ceil(d/3.0);
            if (d%n1 == 0){
                n1 += 1;
            }

            // Get the upper bound for the numerator
            int n2 = (int)Math.floor(d/2.0);
            if (d%n2 == 0){
                n2 -= 1;
            }

            for (int n=n1; n<=n2; n++){
                if (isGcdOne(n, d)){
                    answer += 1;
                }
            }
        }

        System.out.println(answer);
    }


    static boolean isGcdOne(int a, int b){
        if (a%2 == 0 && b%2 == 0){
            return false;
        }
        if (a==0){
            return b==1;
        }
        if (b==0 || a==b){
            return a==1;
        }
        return isGcdOne(b, a%b);
    }
}
