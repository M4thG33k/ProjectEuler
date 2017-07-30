package Problems11_20;

public class Problem19 {

    public static int year = 1900;
    public static int month = 1;
    public static int day = 0;

    public static void main(String[] args) {
        int sundays = 0;
        while (year < 2001)
        {
            if (year > 1900 && day == 6)
            {
                sundays += 1;
            }

            advanceMonth();
        }

        System.out.println(sundays);
    }

    public static void advanceMonth()
    {
        int toAdd = 31;
        switch (month)
        {
            case 4:
            case 6:
            case 9:
            case 11:
                toAdd = 30;
                break;
            case 2:
                toAdd = isLeapYear() ? 29 : 28;
                break;
        }

        day = (day+toAdd) % 7; //modding by 7 keeps the numbers small. Sundays are when day == 6
        if (month == 12)
        {
            month = 1;
            year += 1;
        }
        else
        {
            month += 1;
        }
    }

    public static boolean isLeapYear()
    {
        if (year%4 == 0)
        {
            if (year%100 == 0)
            {
                if (year%400==0)
                {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }
}
