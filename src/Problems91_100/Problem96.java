package Problems91_100;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Problem96 {

    private static String filename = "files/p096_sudoku.txt";
    private static final List<String> values = new ArrayList<>();
    private static List<List<Set<Integer>>> grid = new ArrayList<>();
    private static List<Set<Integer>> boxes = new ArrayList<>();
    private static List<Set<Integer>> rows = new ArrayList<>();
    private static List<Set<Integer>> cols = new ArrayList<>();
    private static Set<Integer> digits = new HashSet<>();

    private static int numBlank = 0;

    private static final int finished = 1;
    private static final int didPlaceOne = 2;

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            digits.add(i);
        }

        final AtomicLong total = new AtomicLong(0);

        final AtomicInteger lineNumber = new AtomicInteger(0);
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(line -> {
                if (lineNumber.get() % 10 == 0) {
                    System.out.println(line);
                    values.clear();
                } else {
                    values.add(line);
                }

                if (lineNumber.get() % 10 == 9) {
                    // have a full grid saved
                    initGrid();
                    populateGrid();
                    int[][] solved = solve(grid, rows, cols, boxes);

                    int toAdd = 0;
                    for (int i=0; i<3; i++){
                        toAdd *= 10;
                        toAdd += solved[0][i];
                    }
                    total.addAndGet(toAdd);
                }
                lineNumber.incrementAndGet();
            });

            System.out.println("The answer: " + total.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initGrid() {
        grid.clear();
        boxes.clear();
        rows.clear();
        cols.clear();
        numBlank = 0;
        for (int i = 0; i < 9; i++) {
            boxes.add(new HashSet<>(digits));
            cols.add(new HashSet<>(digits));
            rows.add(new HashSet<>(digits));
            grid.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                grid.get(i).add(new HashSet<>());
//                grid.get(i).add(new HashSet<>(digits));
            }
        }
    }

    private static void populateGrid() {
        int digit;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                digit = Integer.parseInt(values.get(r).substring(c, c + 1));
                if (digit != 0) {
                    grid.get(r).set(c, singleton(digit));
                    rows.get(r).remove(digit);
                    cols.get(c).remove(digit);
                    boxes.get(getBox(r, c)).remove(digit);
                } else {
                    numBlank += 1;
                }
            }
        }
    }

    private static int getBox(int r, int c) {
        return ((r / 3) * 3) + (c / 3);
    }

    private static Set<Integer> singleton(int x) {
        Set<Integer> ret = new HashSet<>();
        ret.add(x);
        return ret;
    }

    private static List<Set<Integer>> createCopy(List<Set<Integer>> input){
        List<Set<Integer>> ret = new ArrayList<>();
        for (Set<Integer> integerSet:
        input){
            ret.add(new HashSet<>(integerSet));
        }

        return ret;
    }

    private static List<List<Set<Integer>>> createCopyBig(List<List<Set<Integer>>> input){
        List<List<Set<Integer>>> ret = new ArrayList<>();
        for (List<Set<Integer>> integerSetList :
                input) {
            ret.add(createCopy(integerSetList));
        }
        return ret;
    }

    private static int[][] solveGrid(List<List<Set<Integer>>> grid, List<Set<Integer>> rows, List<Set<Integer>> cols, List<Set<Integer>> boxes){
        for (int row=0; row<9; row++){
            for (int col=0; col<9; col++){
                if (grid.get(row).get(col).size() == 0){
                    // This is an empty spot on the grid.

                    Set<Integer> validOptions = intersect3(rows.get(row), cols.get(col), boxes.get(getBox(row, col)));
                    if (validOptions.size() == 0){
                        // no valid options
                        return null;
                    }

                    List<List<Set<Integer>>> tempGrid = createCopyBig(grid);
                    List<Set<Integer>> tempRows = createCopy(rows);
                    List<Set<Integer>> tempCols = createCopy(cols);
                    List<Set<Integer>> tempBoxes = createCopy(boxes);

                    for (int opt :
                            validOptions) {
                        tempGrid.get(row).set(col, singleton(opt));
                        tempRows.get(row).remove(opt);
                        tempCols.get(col).remove(opt);
                        tempBoxes.get(getBox(row, col)).remove(opt);

                        int[][] recursedReturn = solveGrid(tempGrid, tempRows, tempCols, tempBoxes);
                        if (recursedReturn != null){
                            return recursedReturn;
                        }

                        tempRows.get(row).add(opt);
                        tempCols.get(col).add(opt);
                        tempBoxes.get(getBox(row, col)).add(opt);
                    }
                    return null;
                }
            }
        }

        return createGrid(grid);
    }

    private static int[][] solve(List<List<Set<Integer>>> grid, List<Set<Integer>> rows, List<Set<Integer>> cols, List<Set<Integer>> boxes) {
//        print2D(createGrid(grid));
        return solveGrid(grid, rows, cols, boxes);
    }

    private static int[][] createGrid(List<List<Set<Integer>>> grid){
        int[][] ret = new int[9][9];
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                ret[i][j] = grid.get(i).get(j).iterator().next();
            }
        }

        return ret;
    }


    private static Set<Integer> intersect(Set<Integer> first, Set<Integer> second){
        Set<Integer> ret = new HashSet<>(first);
        ret.retainAll(second);
        return ret;
    }

    private static Set<Integer> intersect3(Set<Integer> first, Set<Integer> second, Set<Integer> third){
        return intersect(intersect(first, second), third);
    }

}
