package Problems71_80;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem76 {

    private static Deque<Integer> stack = new ArrayDeque<>(101);
    private static int total = 0;

    public static void main(String[] args) {
        int cap = 100;
        int maxSolo = cap/2;
        int answer = 0;
        total = cap;
        initStack(cap);

        while (stack.size() > 1 || (stack.size() == 1 && stack.peek() <= maxSolo)){
            if (total >= cap){
                if (total == cap){
                    answer += 1;
                }
                popInc();
            } else {
                add();
            }
        }

        System.out.println(answer);
    }

    private static void initStack(int size){
        for (int i=0; i<size; i++){
            stack.push(1);
        }
    }

    private static void popInc(){
        total = total - stack.pop() + 1;
        stack.push(stack.pop()+1);
    }

    private static void add(){
        total += stack.peek();
        stack.push(stack.peek());
    }
}
