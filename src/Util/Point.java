package Util;

public class Point<U, T> {

    private U first;
    private T second;

    public Point(U first, T second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }
}
