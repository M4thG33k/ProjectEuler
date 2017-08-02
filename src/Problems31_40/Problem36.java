package Problems31_40;

public class Problem36 {

    public static void main(String[] args) {
        long total = 0L;

        for (int i=1; i<1000000; i++)
        {
            if (isPalindrome(i) && isPalindrome(Integer.toBinaryString(i))) {
                total += i;
            }
        }

        System.out.println(total);
    }

    private static boolean isPalindrome(String val)
    {
        for (int i=0; i<(val.length()/2); i++)
        {
            if (val.charAt(i) != val.charAt(val.length()-i-1))
            {
                return false;
            }
        }

        return true;
    }

    private static boolean isPalindrome(int val)
    {
        int rev = 0;
        int original = val;
        while (val > 0)
        {
            rev = (rev*10) + val%10;
            val /= 10;
        }

        return original == rev;
    }
}
