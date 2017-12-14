package Problems71_80;

public class Problem71 {

    // It's important to realize with this problem that for each denominator d < 1000000, there is exactly one numerator, n,
    // for which the fraction n/d is strictly less than 3/7, but (n+1)/d is at least 3/7. We only care about n/d and only
    // if n and d are relatively prime, that is if GCD(n, d) == 1. Out of these options, we choose the largest.
    public static void main(String[] args) {
        int max_denom = 1000000;
        double largest_val = 0;
        int numerator = 0;
        int denominator = 0;

        double testVal;
        for (int d=2; d<=max_denom; d++){
            int x = (int)Math.floor((3.0*d)/7);
            if (d%7==0){
                x -= 1;
            }
            if (gcd(x, d) == 1){
                testVal = (double)x/((double)(d));
                if (testVal > largest_val){
                    largest_val = testVal;
                    numerator = x;
                    denominator = d;
                }
            }
        }

        System.out.println(numerator + "/" + denominator);
    }

    static int gcd(int a, int b){
        if (a==0){
            return b;
        }
        if (b==0 || a==b){
            return a;
        }
        return gcd(b, a%b);
    }
}
