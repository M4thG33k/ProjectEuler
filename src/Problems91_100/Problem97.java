package Problems91_100;

import java.util.HashMap;
import java.util.Map;

public class Problem97 {

    private static final long modulo = 10000000000L;

    public static void main(String[] args) {
        long twosPower = 2;
        int pow = 1;

        while (pow < 7830457){
//            System.out.println(twosPower);
            pow += 1;
            twosPower = (twosPower * 2) % modulo;
        }

        System.out.println((28433 * twosPower + 1) % modulo);

//        Map<Integer, Long> twosPowers = new HashMap<>();
//        twosPowers.put(0, 1L);
//        twosPowers.put(1, 2L);
//
//        int lastPow = 1;
//        int desiredPow = 7830457;
//
//        while (lastPow*2 < desiredPow){
//            twosPowers.put(lastPow*2, (twosPowers.get(lastPow)*twosPowers.get(lastPow)) % modulo);
//
//            lastPow *= 2;
//        }
//
//        for (int k :
//                twosPowers.keySet()) {
//            System.out.println( k + " => " + twosPowers.get(k));
//        }
    }
}
