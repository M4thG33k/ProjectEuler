package Problems71_80;

import Util.Prime;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Problem77 {

    private static Deque<Integer> stack = new ArrayDeque<>();
    private static List<Long> primes = Prime.primesStrictlyBelow(1000000);
    private static long total;
    private static int ways = 0;
    private static long cap;

    // Use the same idea as problem 76. However, instead of keeping the actual values in the stack, we're keeping
    // track of the index of the prime in the primes list - this makes incrementing to the next prime *easy*
    public static void main(String[] args) {
        long val = 10L;
        while (ways <= 5000) {
            val += 1L;
            runFor(val);
            System.out.println(val + " ==> " + ways);
        }
        System.out.println(val);
    }

    private static void initFor(long val){
        stack = new ArrayDeque<>();
        cap = val/2;
        ways = 0;
        total = 0;
        while (total < val){
            stack.push(0);
            total += 2L;
        }
    }

    private static void runFor(long val){
        initFor(val);
        while (stack.size() > 1 || (stack.size() == 1 && primes.get(stack.peek()) <= cap)){
            if (total >= val){
                if (total == val){
                    ways += 1;
                }
                popInc();
            } else {
                add();
            }
        }
    }

    private static void popInc(){
        total -= primes.get(stack.pop());
        int next = stack.pop();
        total -= primes.get(next);
        next += 1;
        stack.push(next);
        total += primes.get(next);
    }

    private static void add(){
        total += primes.get(stack.peek());
        stack.push(stack.peek());
    }

}
