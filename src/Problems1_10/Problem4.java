package Problems1_10;

public class Problem4 {

    public static void main(String[] args) {
        int num_digits = 3;

        int low = (int)Double.parseDouble("1e"+(num_digits-1));
        int high = (int)Double.parseDouble("1e"+num_digits);

        int maxVal = -1;
        int prod;

        for (int i = low; i < high; i++) {
            for (int j = i; j < high; j++) { // If j < i, we've already seen that product
                prod = i*j;
                if (prod > maxVal && isPalindrome(prod)) //if prod <= maxVal, we aren't interested in it
                {
                    maxVal = prod;
                }
            }
        }

        System.out.println(maxVal);
    }

    public static boolean isPalindrome(int number)
    {
        int oldNum = number;
        int newNum = 0;

        // This loop reverses the digits of number and stores them in newNum
        while (number > 0)
        {
            newNum = (newNum*10) + (number%10);
            number /= 10;
        }

        return oldNum == newNum;
    }
}
