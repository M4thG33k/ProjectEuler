package Problems81_90;

import java.util.*;

public class Problem90 {

    private static List<Set<List<Integer>>> needed = new ArrayList<>();
    private static List<Integer> first = new ArrayList<>(6);
    private static List<Integer> second = new ArrayList<>(6);
    private static int numValid = 0;

    public static void main(String[] args) {
        initNeeded();
        initDice();

//        System.out.println(second);
        while (incrementSecond()){
            if (isValid()){
                numValid += 1;
                System.out.println(first + " " + second);
            }
//            System.out.println(first + " " + second);
        }

        System.out.println(numValid);
    }

    private static void initDice(){
        for (int i=0; i<6; i++){
            first.add(0);
            second.add(0);
        }
    }

    private static boolean incrementSecond(){
        int i = 5;
        second.set(i, second.get(i)+1);
        while (i > 0 && second.get(i) == 10){
            second.set(i-1, second.get(i-1)+1);
            i -= 1;
        }
        if (i != 0){
            int val = second.get(i);
            i += 1;
            while (i < 6){
                second.set(i, val);
                i += 1;
            }
            return true;
        } else if (second.get(i) == 10){
            if (incrementFirst()){
                second = new ArrayList<>(first);
                return true;
            }
            return false;
        } else {
            int val = second.get(0);
            for (int j=1; j<6; j++){
                second.set(j, val);
            }
            return true;
        }
    }
    
    private static boolean incrementFirst(){
        int i = 5;
        first.set(i, first.get(i)+1);
        while (i > 0 && first.get(i) == 10){
            first.set(i-1, first.get(i-1)+1);
            i -= 1;
        }
        if (i != 0){
            int val = first.get(i);
            i += 1;
            while (i < 6){
                first.set(i, val);
                i += 1;
            }
            return true;
        } else if (first.get(i) == 10){
            return false;
        } else {
            int val = first.get(0);
            for (int j=1; j<6; j++){
                first.set(j, val);
            }
            return true;
        }
    }

    private static void initNeeded(){
        Set<List<Integer>> square;

        //01 - 01/10
        square = new HashSet<>(getFlips(0, 1));

        needed.add(square);

        //04 - 04/40
        square = new HashSet<>(getFlips(0, 4));

        needed.add(square);

        //09 - 09/90
        square = new HashSet<>(getFlips(0, 9));
        //09 -06/60
        square.addAll(getFlips(0, 6));

        needed.add(square);

        //16 - 16/61
        square = new HashSet<>(getFlips(1, 6));
        //16 - 19/91
        square.addAll(getFlips(1, 9));

        needed.add(square);

        //25 - 25/52
        square = new HashSet<>(getFlips(2, 5));

        needed.add(square);

        //36 - 36/63
        square = new HashSet<>(getFlips(3, 6));
        //36 - 39/93
        square.addAll(getFlips(3, 9));

        needed.add(square);

        //49 - 49/94
        square = new HashSet<>(getFlips(4, 9));
        //49 - 46/64
        square.addAll(getFlips(6, 4));
        needed.add(square);

        // 64 & 49 require the same numbers - don't need to check twice!

        //81 - 18/81
        square = new HashSet<>(getFlips(1, 8));
        needed.add(square);
    }

    private static List<List<Integer>> getFlips(int a, int b){
        List<List<Integer>> outer = new ArrayList<>();

        List<Integer> inner = new ArrayList<>();
        inner.add(a);
        inner.add(b);
        outer.add(inner);

        inner = new ArrayList<>(outer.get(0));
        Collections.reverse(inner);
        outer.add(inner);

        return outer;
    }

    private static boolean isValid(){
        // First check to make sure both dice have unique numbers written on them
        Set<Integer> temp = new HashSet<>(first);
        if (temp.size() != 6){
            return false;
        }
        temp = new HashSet<>(second);
        if (temp.size() != 6){
            return false;
        }

        boolean found;
        for (Set<List<Integer>> set : needed){
            found = false;
            for (List<Integer> list : set){
                if (first.contains(list.get(0)) && second.contains(list.get(1))){
                    found = true;
                    break;
                }
            }
            if (!found){
                return false;
            }
        }
        return true;
    }
}
