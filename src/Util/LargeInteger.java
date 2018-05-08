package Util;

public class LargeInteger{

    public static final LargeInteger ZERO = new LargeInteger(0);

    // Can also be negative!

    private boolean isNegative = false;
    private LargeNumber internal = null;

    public LargeInteger(int number) {
        isNegative = number < 0;
        internal = new LargeNumber(isNegative ? -number : number);
    }

    public LargeInteger(LargeNumber old) {
        internal = new LargeNumber(old);
    }

    public LargeInteger(long old) {
        isNegative = old < 0;
        internal = new LargeNumber(isNegative ? -old : old);
    }

    public LargeInteger(LargeInteger old){
        internal = new LargeNumber(old.internal);
        isNegative = old.isNegative;
    }

    public LargeInteger addReturn(int other) {
        return this.addReturn(new LargeInteger(other));
    }

    public LargeInteger addReturn(LargeInteger other) {
        LargeInteger ret = new LargeInteger(this);
        ret.add(other);
        return ret;
    }

    public void add(int other) {
        this.add(new LargeInteger(other));
    }

    public void add(LargeInteger other) {
        if (this.isNegative == other.isNegative){
            // Both have the same sign. No problem!
            this.internal.add(other.internal);
        } else if (this.isNegative){
            if (!this.internal.isGreaterThan(other.internal)){
                this.isNegative = false;
            }
            this.internal = internal.findAbsDifference(other.internal);
        } else {
            if (!this.internal.isGreateThanOrEqualTo(other.internal)){
                this.isNegative = true;
            }
            this.internal = internal.findAbsDifference(other.internal);
        }
    }

    public LargeInteger timesReturn(long value) {
        return this.timesReturn(new LargeInteger(value));
    }

    public LargeInteger timesReturn(LargeInteger other) {
        LargeInteger ret = new LargeInteger(this);
        ret.times(other);
        return ret;
    }

    public void times(long value) {
        this.times(new LargeInteger(value));
    }

    public void times10toThe(int n) {
        this.internal.times10toThe(n);
    }

    public void times(LargeInteger other) {
        this.isNegative = !(this.isNegative == other.isNegative);
        this.internal.times(other.internal);
    }

    public String toString() {
        return (this.isNegative ? "-" : "") + internal.toString();
    }

    public int toInt() {
        return (this.isNegative ? -1 : 1) * this.internal.toInt();
    }

    public boolean isItNegative(){
        return this.isNegative;
    }

    public long digitSum() {
        return this.internal.digitSum();
    }

    public int getNthDigit(int n) {
        return this.internal.getNthDigit(n);
    }

    public LargeInteger squared() {
        return this.timesReturn(this);
    }

    public boolean isGreaterThanOrEqualTo(LargeInteger other) {
        return this.isGreaterThan(other) || this.equals(other);
    }

    public boolean isGreaterThan(LargeInteger other) {
        if (this.isNegative) {
            return other.isNegative && other.internal.isGreaterThan(this.internal);
        } else {
            return other.isNegative || this.internal.isGreaterThan(other.internal);
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof LargeInteger) && (this.isNegative == ((LargeInteger) obj).isNegative) && (this.internal.equals(((LargeInteger) obj).internal));
    }

    // ignores negative sign
    public int length() {
        return internal.length();
    }

    public LargeInteger findAbsDifference(LargeInteger other) {
        LargeInteger ret = new LargeInteger(0);
        ret.internal = this.internal.findAbsDifference(other.internal);
        return ret;
    }


    public LargeInteger getPerfectSquare() {
        if (this.isNegative){
            return null;
        }
        return new LargeInteger(this.internal.getPerfectSquare());
    }


    public LargeInteger dividedByTwo() {
        LargeInteger ret = new LargeInteger(0);
        ret.isNegative = this.isNegative;
        ret.internal = this.internal.dividedByTwo();
        return ret;
    }

    public void negate(){
        this.isNegative = !this.internal.equals(LargeNumber.ZERO) && !this.isNegative;
    }

    public void subtract(LargeInteger other){
        LargeInteger temp = new LargeInteger(other);
        temp.negate();
        this.add(temp);
    }

    public void subtract(int other){
        this.subtract(new LargeInteger(other));
    }

    public LargeInteger subtractReturn(LargeInteger other){
        LargeInteger ret = new LargeInteger(this);
        ret.subtract(other);
        return ret;
    }

    public LargeInteger subtractReturn(int other){
        return this.subtractReturn(new LargeInteger(other));
    }

    public LargeInteger getAbs(){
        LargeInteger ret = new LargeInteger(this);
        ret.isNegative = false;
        return ret;
    }

    public Point<LargeInteger, LargeInteger> absDivMod(LargeInteger other){
        Point<LargeNumber, LargeNumber> temp = this.internal.divMod(other.internal);
        return new Point<>(new LargeInteger(temp.getFirst()), new LargeInteger(temp.getSecond()));
    }
}
