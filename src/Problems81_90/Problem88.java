package Problems81_90;

import Util.Prime;

import java.util.*;

public class Problem88 {

    private static Set<Integer> neededKs = new HashSet<>();
    private static Set<Integer> found = new HashSet<>();
    private static int minNeeded = 2;
    private static int maxVal = 12000;

    public static void main(String[] args) throws Exception{
        for (int k=2; k<=maxVal; k++){
            neededKs.add(k);
        }

        int x = 4;
        while (neededKs.size() > 0){
            processValue(x);
            x++;
        }

        long total = 0;
        for (int val : found){
            total += val;
        }
        System.out.println(total);
    }

    private static void processValue(long x) throws Exception{
        DataWrapper wrapper = new DataWrapper(x);
        List<DataWrapper> theList = new ArrayList<>();
        theList.add(wrapper);
        while (theList.size() > 0){
            List<DataWrapper> newList = new ArrayList<>();
            int associated;

            for (DataWrapper dataWrapper: theList){
                associated = dataWrapper.associatedValue();
                if (associated < minNeeded){
                    continue;
                } else if (associated <= maxVal && neededKs.contains(associated)){
                    handleRemoval(associated);
                    found.add((int)dataWrapper.product);
                }
                newList.addAll(dataWrapper.condense());
            }

            theList = newList;
        }
    }

    private static void handleRemoval(int value) throws Exception{
        if (!neededKs.contains(value)){
            throw new Exception("Trying to remove " + value + ", but it doesn't exist!");
        } else {
            neededKs.remove(value);
            if (value == minNeeded){
                if (neededKs.size() > 0){
                    minNeeded = Collections.min(neededKs);
                }
            }
        }
    }

    private static class DataWrapper{
        public Map<Long, Integer> map;
        public int numFactors = 0;
        public long factorSum = 0;
        public long product;

        public DataWrapper(long product){
            this.product = product;
            this.map = Prime.primeFactorMap(product);
            for (long key: map.keySet()){
                numFactors += map.get(key);
                factorSum += key * map.get(key);
            }
        }

        public DataWrapper(){
            this.product = 0;
            this.map = new HashMap<>();
        }

        @Override
        public String toString() {
            return String.format("%d %d %d %s", product, numFactors, factorSum, map.toString());
        }

        public int associatedValue(){
            return (int)(product - factorSum + numFactors);
        }

        public List<DataWrapper> condense(){
            List<DataWrapper> ret = new ArrayList<>();

            if (numFactors <= 2){
                return ret;
            }

            List<Long> factors = new ArrayList<>(this.map.keySet());
            for (int i=0; i<factors.size(); i++){
                long factor = factors.get(i);
                if (this.map.get(factor) > 1){
                    // If there are at least two of this value, we can choose it twice
                    DataWrapper temp = new DataWrapper();
                    temp.product = this.product;
                    temp.factorSum = this.factorSum - 2*factor + factor*factor;
                    temp.numFactors = this.numFactors - 1;
                    temp.map = new HashMap<>(this.map);
                    if (this.map.get(factor) == 2){
                        temp.map.remove(factor);
                    } else {
                        temp.map.put(factor, this.map.get(factor)-2);
                    }
                    long factor2 = factor*factor;
                    if (temp.map.containsKey(factor2)){
                        temp.map.put(factor2, temp.map.get(factor2)+1);
                    } else {
                        temp.map.put(factor2, 1);
                    }
                    ret.add(temp);
                }
                for (int j=i+1; j<factors.size(); j++){
                    long second = factors.get(j);
                    long prod = factor*second;
                    DataWrapper temp = new DataWrapper();
                    temp.product = this.product;
                    temp.factorSum = this.factorSum - factor - second + prod;
                    temp.numFactors = this.numFactors - 1;
                    temp.map = new HashMap<>(this.map);
                    if (this.map.get(factor) == 1){
                        temp.map.remove(factor);
                    } else {
                        temp.map.put(factor, this.map.get(factor)-1);
                    }

                    if (this.map.get(second) == 1){
                        temp.map.remove(second);
                    } else {
                        temp.map.put(second, this.map.get(second)-1);
                    }

                    if (temp.map.containsKey(prod)){
                        temp.map.put(prod, temp.map.get(prod)+1);
                    } else {
                        temp.map.put(prod, 1);
                    }

                    ret.add(temp);
                }
            }

            return ret;
        }
    }
}
