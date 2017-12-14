package Problems81_90;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Problem83 extends Problem81 {

    protected static Set<String> visited = new HashSet<>();
    protected static String goal;

    public static void main(String[] args) {
        initialize();

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        queue.add(new Node(0, 0, data.get(0).get(0)));

        while (queue.size() > 0){
            Node next = queue.poll();
            String coord = next.getCoordString();
            if (coord.equals(goal)){
                System.out.println(next.val);
                break;
            }

            if (visited.contains(coord)){
                continue;
            }

            visited.add(coord);

            for (int i=-1; i<2; i++){
                for (int j=-1; j<2; j++){
                    if ((i==j && i==0) || !(i==0 || j==0)){
                        continue;
                    }

                    if (next.row+i < 0 || next.row+i >= data.size() || next.col+j < 0 || next.col+j >= data.size()){
                        continue;
                    }

                    String coord2 = coordString(next.row +i, next.col+j);
                    if (visited.contains(coord2)){
                        continue;
                    }

                    queue.add(new Node(next.row+i, next.col+j, data.get(next.row+i).get(next.col+j) + next.val));
                }
            }
        }

    }

    private static void initialize(){
        loadFile("files/p083_matrix.txt");

        goal = coordString(data.size()-1, data.size()-1);
    }

    protected static String coordString(int x, int y){
        return x + "," + y;
    }

    protected static int[] coords(String str){
        String[] vals = str.split(",");
        int[] ret = new int[2];
        for (int i=0; i<2; i++){
            ret[i] = Integer.parseInt(vals[i]);
        }

        return ret;
    }

    private static class Node{
        public int row;
        public int col;
        public int val;
        public String path = "";

        public Node(int row, int col, int val, String path){
            this.row = row;
            this.col = col;
            this.val = val;
            this.path = path;
        }

        public Node(int row, int col, int val){
            this(row, col, val, "");
        }

        public String getCoordString(){
            return coordString(row, col);
        }

        @Override
        public String toString() {
            return "[" + getCoordString() + "] => " + this.val;
        }
    }
}
