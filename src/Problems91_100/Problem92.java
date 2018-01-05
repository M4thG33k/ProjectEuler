package Problems91_100;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Problem92 {

    public static void main(String[] args) {
        int total = 0;
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(1, false);
        map.put(89, true);

        int start = 1;
        Stack<Integer> stack;
        int current;
        boolean result;

        while (start < 10000000){
            stack = new Stack<>();
            stack.push(start);

            while (!stack.empty()){
                current = stack.pop();
                if (map.containsKey(current)){
                    result = map.get(current);
                    while (!stack.empty()){
                        map.put(stack.pop(), result);
                    }
                } else{
                    stack.push(current);
                    stack.push(nextInt(current));
                }
            }

            if (map.get(start)){
                total += 1;
            }

            start += 1;
        }

        System.out.println(total);
    }

    private static int nextInt(int val){
        int ret = 0;
        int digit;
        while (val > 0){
            digit = val%10;
            ret += digit*digit;
            val = val/10;
        }

        return ret;
    }
}
