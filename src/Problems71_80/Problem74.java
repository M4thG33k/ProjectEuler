package Problems71_80;

import java.util.HashMap;
import java.util.Map;

public class Problem74 {

    static Map<Integer, Integer> factorials = new HashMap<>();
    static Map<Long, Integer> longToChainMap = new HashMap<>();

    public static void main(String[] args) {
        int total = 1;
        factorials.put(0,1);
        for (int i=1; i<10; i++){
            total *= i;
            factorials.put(i, total);
        }

        // Initialize chain map with some facts we already know!
        // Since any integer (greater than 2) will be forced into a loop that contains one of these 10 integers, we need
        // to watch for them!
        longToChainMap.put(1L, 1);
        longToChainMap.put(2L, 1);
        longToChainMap.put(145L, 1);
        longToChainMap.put(871L, 2);
        longToChainMap.put(45361L, 2);
        longToChainMap.put(872L, 2);
        longToChainMap.put(45362L, 2);
        longToChainMap.put(169L, 3);
        longToChainMap.put(363601L, 3);
        longToChainMap.put(1454L, 3);

        int numChains = 0;

        for (long start = 3; start < 1000000; start += 1){
//        for (long start = 145; start < 146; start += 1){
            if (longToChainMap.containsKey(start)){
                continue;
            }
            int length =getLength(start);
            if (length==60){
                numChains += 1;
            }
        }

        System.out.println(numChains);
    }

    static int getLength(long val){
        if (longToChainMap.containsKey(val)){
            return longToChainMap.get(val);
        }
        long factSum = getFactorialSum(val);
        if (factSum == val){
            return 1;
        }
        longToChainMap.put(val, 1 + getLength(factSum));
        return longToChainMap.get(val);
    }

    static long getFactorialSum(long val){
        long ret = 0;
        while (val > 0){
            ret += factorials.get((int)val%10);
            val /= 10;
        }
        return ret;
    }
}
