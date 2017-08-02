package Problems31_40;

public class Problem40 {

    public static void main(String[] args) {
        int[] digitsWanted = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};

        int digitsSeen = 0;

        int n = 1;
        int prod = 1;
        for (int i : digitsWanted)
        {
            while (digitsSeen + (""+n).length() < i)
            {
                digitsSeen += ("" + n).length();
                n += 1;
            }

            int neededDigit = i - digitsSeen - 1;
            prod *= Integer.parseInt((""+n).substring(neededDigit, neededDigit+1));
        }

        System.out.println(prod);
    }
}
