package Problems91_100;

import Util.Prime;

import java.util.*;

public class Problem95 {

    private static Set<Long> seenValues = new HashSet<>();
    private static Map<Long, List<Long>> chainMap = new HashMap<>();
    private static long longest = 0;
    private static long longestLength = 0;

    public static void main(String[] args) {
        List<Long> vals;
        int size;
        for (long i=0L; i<=1000000; i++){
            System.out.println(i);
            handleValue(i);
            if (chainMap.containsKey(i)){
                vals = chainMap.get(i);
                size = vals.size();
                if (size > 1 && (vals.get(0).equals(vals.get(size-1)))){
                    if (size-1 > longestLength){
                        longestLength = size - 1;
                        longest = i;
                    }
                }
            }
        }

        System.out.println("Longest chain is " + longestLength + " in length, created by: " + longest + " with min value: " + Collections.min(chainMap.get(longest)));
    }

    private static void handleValue(long x){
        if (seenValues.contains(x)){
            return;
        }
        Stack<Long> stack = new Stack<>();
        stack.push(x);
        long sum;

        while (!stack.empty()){
            // If the value on the stack is larger than 1 million, we don't care about any values in the stack
            if (stack.peek() > 1000000){
                while (!stack.empty()){
                    seenValues.add(stack.pop());
                }
                break;
            }
            // If we don't already have a chain for this value
            if (!chainMap.containsKey(stack.peek())) {
                // If it's a seen value, that means it doesn't have a valid chain, so we can ignore all values in the stack
                if (seenValues.contains(stack.peek())){
                    while (!stack.empty()){
                        seenValues.add(stack.pop());
                    }
                    break;
                }
                sum = Prime.getSumOfProperFactors(stack.peek());
                // If the sum of proper divisors is in this stack, we're going to keep looping, so terminate the chain early
                if (stack.contains(sum)) {
                    List<Long> theChain = new ArrayList<>();
                    long oldVal = stack.pop();
                    theChain.add(oldVal);
                    theChain.add(sum);
                    chainMap.put(oldVal, theChain);
                    seenValues.add(oldVal);
                } else {
                    // We haven't seen this value, so put it on the stack
                    stack.push(sum);
                }
            } else {
                // We have a chain for this value. We can now work backward, creating chains for everything else in the stack
                List<Long> theChain = new ArrayList<>(chainMap.get(stack.pop()));
                while (!stack.isEmpty()){
                    theChain.add(0, stack.peek());
                    seenValues.add(stack.peek());
                    chainMap.put(stack.pop(), new ArrayList<>(theChain));
                }
            }
        }
    }
}
