package Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PolynomialDoubles {

    private List<Double> coefficients;

    public PolynomialDoubles(){
        coefficients = new ArrayList<>();
        coefficients.add(0.0);
    }

    public PolynomialDoubles(List<Double> coes) {
        this.coefficients = new ArrayList<>(coes);
    }

    public List<Double> getCoefficients() {
        return this.coefficients;
    }

    public PolynomialDoubles add(PolynomialDoubles other) {
        PolynomialDoubles ret = new PolynomialDoubles(this.coefficients);
        for (int i = 0; i < other.coefficients.size(); i++) {
            if (i < ret.coefficients.size()) {
                ret.coefficients.set(i, ret.coefficients.get(i) + other.coefficients.get(i));
            } else {
                ret.coefficients.add(other.coefficients.get(i));
            }
        }

        return ret;
    }

    public PolynomialDoubles negate() {
        return new PolynomialDoubles(this.coefficients.stream().map(x -> -x).collect(Collectors.toList()));
    }

    public PolynomialDoubles subtract(PolynomialDoubles other) {
        return this.add(other.negate());
    }

    @Override
    public String toString() {
        if (coefficients == null || coefficients.size() == 0) {
            return "0";
        }
        StringBuilder builder = new StringBuilder();
        AtomicInteger power = new AtomicInteger(0);

        List<String> temp = coefficients.stream().map(
                co -> {
                    String part = (co >= 0 ? " + " : " - ") + Math.abs(co) + (power.get() == 0 ? "" : "(x^" + power.get() + ")");
                    power.incrementAndGet();
                    return part;
                }
        ).collect(Collectors.toList());
        Collections.reverse(temp);
        temp.forEach(builder::append);

        return builder.toString();
    }

    public PolynomialDoubles product(PolynomialDoubles other) {
        int myLength = this.coefficients.size();
        int otherLength = other.coefficients.size();
        if (myLength < otherLength) {
            return other.product(this);
        }
        int newLength = (myLength) + (otherLength) - 1;

        List<Double> newCoes = new ArrayList<>(newLength);
        int currentPower = 0;
        while (currentPower < newLength) {
            int i = currentPower;
            int j = 0;
            double currentTotal = 0;
            while (i >= 0 && j < otherLength) {
                if (i < myLength) {
                    currentTotal += this.coefficients.get(i) * other.coefficients.get(j);
                }
                i -= 1;
                j += 1;
            }
            newCoes.add(currentTotal);
            currentPower += 1;
        }

        return new PolynomialDoubles(newCoes);
    }

    public double evaluateAt(double x){
        int prod = 1;
        double total = 0;

        for (Double coefficient : coefficients) {
            total += coefficient * prod;
            prod *= x;
        }

        return total;
    }

    public void multiplyBy(double factor){
        this.coefficients = this.coefficients.stream().map(x -> (double)Math.round(x*factor)).collect(Collectors.toList());
    }

    public int maxPower(){
        return this.coefficients.size();
    }
}
