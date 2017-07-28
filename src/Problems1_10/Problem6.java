package Problems1_10;

public class Problem6 {

    /*
    Note that the following formula holds:
        (x1 + x2 + ... + xn) ^ 2 = (x1^2 + x2^2 + ... + x3^2) + 2* Sum(xi*xj, 1 <= i,j <= n with i<j)

    Using this, we can simply the problem by quite a bit:
     */

    public static void main(String[] args) {
        int answer = 0;

        for (int i=1; i<=100; i++)
        {
            for (int j=i+1; j<=100; j++)
            {
                answer += i*j;
            }
        }

        System.out.println(2*answer);
    }
}
