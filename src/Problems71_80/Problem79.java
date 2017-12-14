package Problems71_80;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem79 {

    static class PathData {
        private int last_node;
        private List<Integer> path;
        private Map<Integer, Integer> counts;
        private Set<Integer> localUniverse;

        public PathData(int start) {
            this.last_node = start;
            this.path = new ArrayList<>();
            this.path.add(start);
            this.counts = new HashMap<>();
            this.counts.put(start, 1);
            this.localUniverse = new HashSet<>();
            this.localUniverse.add(start);
        }

        public PathData add(int tail) {
            PathData ret = new PathData(tail);
            ret.path = new ArrayList<>(this.path);
            ret.path.add(tail);
            ret.counts = new HashMap<>(this.counts);
            if (!ret.counts.containsKey(tail)) {
                ret.counts.put(tail, 1);
            } else {
                ret.counts.put(tail, 1 + ret.counts.get(tail));
            }
            ret.localUniverse.addAll(this.localUniverse);

            return ret;
        }

        public int getLastNode() {
            return this.last_node;
        }

        public List<Integer> getPath() {
            return this.path;
        }

        public Map<Integer, Integer> getCounts() {
            return this.counts;
        }

        public int getLength() {
            return this.path.size();
        }

        public boolean seesWholeUniverse(Set<Integer> universe) {
            Set<Integer> temp = new HashSet<>(universe);
            temp.removeAll(this.localUniverse);
            return temp.size() == 0;
        }

        public boolean hasValidNodeCounts() {
            for (int key : this.counts.keySet()) {
                if (this.counts.get(key) > 3) {
                    return false;
                }
            }
            return true;
        }

        public boolean hasValidLength() {
            return this.path.size() <= 30;
        }

        public boolean satisfiesAllCodes(List<List<Integer>> codes) {
            int[] indices = new int[codes.size()];
            int need = codes.size();
            for (int p : this.path) {
                for (int i = 0; i < codes.size(); i++) {
                    if (indices[i] > 2) {
                        continue;
                    }

                    if (p == codes.get(i).get(indices[i])) {
                        indices[i] += 1;
                        if (indices[i] == 3) {
                            need -= 1;
                        }
                    }
                }
            }
            return need == 0;
        }

        public String getPathCode(){
            StringBuilder builder = new StringBuilder();
            for (int p: this.path){
                builder.append(p);
            }

            return builder.toString();
        }


    }


    private static Deque<PathData> queue = new LinkedList<>();
    private static Map<Integer, Set<Integer>> adj = new HashMap<>();
    private static PathData bestPath = null;
    private static Set<Integer> universe = new HashSet<>();
    private static List<List<Integer>> codes = new ArrayList<>();

    public static void main(String[] args) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("files/p079_keylog.txt"));
            Stream<String> lines = reader.lines();
            List<String> theLines = lines.filter(x -> x.length() > 0).collect(Collectors.toList());
            for (String line : theLines) {
                for (int i = 0; i < line.length() - 1; i++) {
                    int from = Integer.parseInt(line.substring(i, i + 1));
                    List<Integer> temp = new ArrayList<>();
                    temp.add(from);
                    universe.add(from);
                    if (!adj.containsKey(from)) {
                        adj.put(from, new HashSet<>());
                    }
                    for (int j = i + 1; j < line.length(); j++) {
                        int to = Integer.parseInt(line.substring(j, j + 1));
                        temp.add(to);
                        adj.get(from).add(to);
                        universe.add(to);
                    }
                    if (i == 0) {
                        codes.add(temp);
                    }
                }
            }

            for (int start: universe){
                PathData best = getBestPathStartingWith(start);
                if (best != null){
                    if (bestPath == null || bestPath.getLength() > best.getLength()){
                        bestPath = best;
                    }
                }
            }

            System.out.println(bestPath.getPathCode());

        } catch (IOException e) {
            System.out.println("IOException");
        }

    }

    private static PathData getBestPathStartingWith(int val) {
        queue = new LinkedList<>();
        queue.add(new PathData(val));
        while (queue.size() > 0) {
            PathData current = queue.pop();
            if (adj.containsKey(current.getLastNode())) {
                for (int child : adj.get(current.getLastNode())) {
                    PathData next = current.add(child);
                    if (next.hasValidLength() && next.hasValidNodeCounts()) {
                        if (next.seesWholeUniverse(universe) && next.satisfiesAllCodes(codes)) {
                            return next;
                        } else {
                            queue.add(next);
                        }
                    }
                }
            }
        }
        return null;
    }
}
