package Problems11_20;

public class Problem15 {

    /*
    In order to move from the top left -> bottom right square of an NxN grid, you have to travel a total distance of
    2*N (assuming shortest paths only). Of these 2*N segments, N of them must be horizontal (and the other N are
    vertical). Therefore the number of ways to travel from corner->corner is Choose(2*N, N), using the choose function.
     */

    public static void main(String[] args) {
        System.out.println(choose(40, 20));
    }

    public static double choose(double n, double k)
    {
        return factorial(n) / (factorial(n-k)*factorial(k));
    }

    public static double factorial(double n)
    {
        double answer = 1;
        while (n > 1){
            answer *= n;
            n -= 1;
        }

        return answer;
    }
}
