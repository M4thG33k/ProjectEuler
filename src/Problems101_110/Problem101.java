package Problems101_110;

import Util.LargeInteger;
import Util.LargeIntegerPolynomial;
import Util.Point;
import Util.PolynomialDoubles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Problem101 {

    public static void main(String[] args) {

//        int[] coefs = new int[]{0, 0, 0, 1};
        int[] coefs = new int[]{1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1};

        List<LargeInteger> coefficients = new ArrayList<>();
        for (int c : coefs) {
            coefficients.add(new LargeInteger(c));
        }

        LargeIntegerPolynomial poly = new LargeIntegerPolynomial(coefficients);

        List<Point<Integer, LargeInteger>> points = new ArrayList<>();
        for (int x = 1; x <= coefficients.size(); x++) {
            points.add(new Point<>(x, poly.evaluateAt(x)));
        }

        System.out.println(points);

        LargeInteger total = new LargeInteger(0);

        for (int i=0; i<2; i++){
            System.out.println("Getting FIT for " + i);
            LargeInteger fit = getFIT(points, i);
            System.out.println("Fit found! " + fit);
            total.add(getFIT(points, i));
            System.out.println(total);
        }

//        for (int i=0; i<points.size(); i++){
//            System.out.println("Getting FIT for " + i);
//            LargeInteger fit = getFIT(points, i);
//            System.out.println("Fit found! " + fit);
//            total.add(getFIT(points, i));
//            System.out.println(total);
//        }

        System.out.println(total);

//
//        double total = 0.0;
////        System.out.println(generateLagrangePolynomialForPoints(points, 1));
//        for (int i=0; i<coefficients.size(); i++) {
//            PolynomialDoubles fitted = generateLagrangePolynomialForPoints(points, i);
//            System.out.println(fitted);
//            total += findFIT(poly, fitted);
//        }
//
//        System.out.println(Math.round(total));
    }

    private static LargeInteger getFIT(List<Point<Integer, LargeInteger>> points, int maxPower) {
        if (maxPower < 0 || maxPower + 1 > points.size()) {
            return null;
        }

        System.out.println("Init");

        List<LargeIntegerPolynomial> pieces = new ArrayList<>();
        List<LargeInteger> factors = new ArrayList<>();
        for (int i = 0; i <= maxPower; i++) {
            List<LargeInteger> coefs = new ArrayList<>();
            coefs.add(new LargeInteger(1));
            pieces.add(new LargeIntegerPolynomial(coefs));
            factors.add(new LargeInteger(1));
        }

        List<LargeInteger> tempCoefs;

        System.out.println("Generating polys");

        for (int j = 0; j <= maxPower; j++) {
            for (int m = 0; m <= maxPower; m++) {
                if (j == m) {
                    continue;
                }

                factors.get(j).times(points.get(j).getFirst() - points.get(m).getFirst());

                tempCoefs = new ArrayList<>();
                tempCoefs.add(new LargeInteger(points.get(m).getFirst()));
                tempCoefs.get(0).negate();
                tempCoefs.add(new LargeInteger(1));

                LargeIntegerPolynomial tempPoly = new LargeIntegerPolynomial(tempCoefs);
                pieces.set(j, pieces.get(j).product(tempPoly));
            }
        }

        System.out.println(pieces);
        System.out.println(factors);
        System.out.println("Applying multiplications");

        // apply factor multiplications (including the values from points)
        LargeInteger bigFactor = new LargeInteger(1);
        for (int i = 0; i < pieces.size(); i++) {
            bigFactor.times(factors.get(i));
            pieces.get(i).multiplyByFactor(points.get(i).getSecond());
            for (int j = 0; j < pieces.size(); j++) {
                if (i != j) {
                    pieces.get(i).multiplyByFactor(factors.get(j));
                }
            }
        }

        System.out.println(pieces);
        System.out.println("Combining polys");
        LargeIntegerPolynomial bigPoly = new LargeIntegerPolynomial(new ArrayList<LargeInteger>(){{add(new LargeInteger(0));}});
        for (LargeIntegerPolynomial piece:pieces){
//            System.out.println(piece);
            bigPoly = bigPoly.add(piece);
        }
//        System.out.println(bigPoly);

        System.out.println("Finding FIT");

        for (AtomicInteger i=new AtomicInteger(maxPower+1); i.get()<=points.size(); i.incrementAndGet()){
//            LargeInteger evaluated = pieces.stream().map(x -> x.evaluateAt(i.get())).reduce(new LargeInteger(0), LargeInteger::addReturn);
            LargeInteger evaluated = bigPoly.evaluateAt(i.get());
            if (!evaluated.equals(points.get(i.get()-1).getSecond().timesReturn(bigFactor))){
                System.out.println("Calculating return value");
                System.out.println("BigFactor: " + bigFactor);
                return evaluated.absDivMod(bigFactor).getFirst();
            }
        }

        return new LargeInteger(0);
    }

    private static PolynomialDoubles generateLagrangePolynomialForPoints(List<Point<Double, Double>> points, int maxPower) {
        if (maxPower < 0 || maxPower + 1 > points.size()) {
            return null;
        }

        PolynomialDoubles ret = new PolynomialDoubles();

        for (int j = 0; j <= maxPower; j++) {
            double divisor = 1.0;
            PolynomialDoubles temp = new PolynomialDoubles(new ArrayList<Double>() {{
                add(1.0);
            }});

            for (int k = 0; k <= maxPower; k++) {
                if (k == j) {
                    continue;
                }
                divisor *= (points.get(j).getFirst() - points.get(k).getFirst());
                List<Double> tempCoes = new ArrayList<>(2);
                tempCoes.add(-points.get(k).getFirst());
                tempCoes.add(1.0);
                PolynomialDoubles polyFact = new PolynomialDoubles(tempCoes);
                temp = temp.product(polyFact);
            }
            temp.multiplyBy(points.get(j).getSecond() / divisor);
            ret = ret.add(temp);
        }

        return ret;
    }

    private static double findFIT(PolynomialDoubles original, PolynomialDoubles fitted) {
        int i = 0;
        double val;

        while (true) {
            if (i > fitted.maxPower()) {
                return 0;
            }
            i += 1;
            val = fitted.evaluateAt(i);
            if (val != original.evaluateAt(i)) {
                break;
            }
        }

        return val;
    }
}
