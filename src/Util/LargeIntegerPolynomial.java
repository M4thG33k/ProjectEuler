package Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LargeIntegerPolynomial {

    private List<LargeInteger> coefficients;

    public LargeIntegerPolynomial(){
        coefficients = new ArrayList<>();
        coefficients.add(new LargeInteger(0));
    }

    public LargeIntegerPolynomial(List<LargeInteger> coes) {
        this.coefficients = new ArrayList<>(coes);
    }

    public List<LargeInteger> getCoefficients() {
        return this.coefficients;
    }

    public LargeIntegerPolynomial add(LargeIntegerPolynomial other) {
        LargeIntegerPolynomial ret = new LargeIntegerPolynomial(this.coefficients);
        for (int i = 0; i < other.coefficients.size(); i++) {
            if (i < ret.coefficients.size()) {
                ret.coefficients.set(i, ret.coefficients.get(i).addReturn(other.coefficients.get(i)));
            } else {
                ret.coefficients.add(other.coefficients.get(i));
            }
        }

        return ret;
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
                    String part = (co.isGreaterThanOrEqualTo(LargeInteger.ZERO) ? " + " : " - ") + co.getAbs() + (power.get() == 0 ? "" : "(x^" + power.get() + ")");
                    power.incrementAndGet();
                    return part;
                }
        ).collect(Collectors.toList());
        Collections.reverse(temp);
        temp.forEach(builder::append);

        return builder.toString();
    }

    public LargeIntegerPolynomial product(LargeIntegerPolynomial other) {
        int myLength = this.coefficients.size();
        int otherLength = other.coefficients.size();
        if (myLength < otherLength) {
            return other.product(this);
        }
        int newLength = (myLength) + (otherLength) - 1;

        List<LargeInteger> newCoes = new ArrayList<>(newLength);
        int currentPower = 0;
        while (currentPower < newLength) {
            int i = currentPower;
            int j = 0;
            LargeInteger currentTotal = new LargeInteger(0);
            while (i >= 0 && j < otherLength) {
                if (i < myLength) {
                    currentTotal.add(this.coefficients.get(i).timesReturn(other.coefficients.get(j)));
                }
                i -= 1;
                j += 1;
            }
            newCoes.add(currentTotal);
            currentPower += 1;
        }

        return new LargeIntegerPolynomial(newCoes);
    }

    public LargeInteger evaluateAt(int x){
        int prod = 1;
        LargeInteger total = new LargeInteger(0);

        for (LargeInteger coefficient : coefficients) {
            total.add(coefficient.timesReturn(prod));
            prod *= x;
        }

        return total;
    }

    public void multiplyByFactor(int factor){
        this.coefficients = this.coefficients.stream().map(x -> x.timesReturn(factor)).collect(Collectors.toList());
    }

    public void multiplyByFactor(LargeInteger factor){
        this.coefficients = this.coefficients.stream().map(x -> x.timesReturn(factor)).collect(Collectors.toList());
    }


    public int maxPower(){
        return this.coefficients.size();
    }
}
