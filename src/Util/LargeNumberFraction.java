package Util;

public class LargeNumberFraction {

    private LargeNumber num;
    private LargeNumber denom;

    public LargeNumberFraction(int numerator, int denominator)
    {
        this.num = new LargeNumber(numerator);
        this.denom = new LargeNumber(denominator);
    }

    public void flip()
    {
        LargeNumber temp = this.num;
        this.num = this.denom;
        this.denom = temp;
    }

    public void add(int number)
    {
        LargeNumber numToAdd = new LargeNumber(this.denom);
        numToAdd.times(number);
        this.num.add(numToAdd);
    }

    @Override
    public String toString() {
        return num.toString() + " / " + denom.toString();
    }

    public LargeNumber getNum() {
        return num;
    }

    public LargeNumber getDenom() {
        return denom;
    }
}
