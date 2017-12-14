package Problems81_90;

import java.util.ArrayList;
import java.util.List;

public class Problem82 extends Problem81{

    protected static List<List<Integer>> fromLeft;
    protected static List<List<Integer>> fromDown;
    protected static List<List<Integer>> fromUp;
    protected static List<List<Integer>> best;

    // This solution works only because we have no negative values in our matrix!
    // Starting with the second column, for each column calculate the following in order:
    // 1) path from left -> right
    // 2) path from up
    // 3) path from down
    // 4) best path
    public static void main(String[] args) {
        initialize();

        for (int col=1; col<data.size(); col++){
            for (int row=0; row<data.size(); row++){
                updateFromLeft(row, col);
            }
            for (int row=0; row<data.size(); row++){
                updateFromUp(row, col);
            }
            // Note the direction change here!
            for (int row=data.size()-1; row>=0; row--){
                updateFromDown(row, col);
            }

            for (int row=0; row<data.size(); row++){
                updateBest(row, col);
            }
        }

        // Our answer should now be the smallest value in the last column!
        int bestPath = best.get(0).get(best.size()-1);
        for (int row=1; row<best.size(); row++){
            int val = best.get(row).get(best.size()-1);
            if (val < bestPath){
                bestPath = val;
            }
        }

        System.out.println(bestPath);
    }

    private static void initialize(){
        loadFile("files/p082_matrix.txt");
        fromLeft = new ArrayList<>();
        best = new ArrayList<>();
        fromDown = new ArrayList<>();
        fromUp = new ArrayList<>();
        for (int i=0; i<data.size(); i++){
            List<Integer> flRow = new ArrayList<>();
            List<Integer> bestRow = new ArrayList<>();
            List<Integer> fdRow = new ArrayList<>();
            List<Integer> fuRow = new ArrayList<>();


            for (int j=0; j<data.size(); j++){
                flRow.add(0);
                fdRow.add(0);
                fuRow.add(0);

                if (j==0){
                    bestRow.add(get(i, j));
                } else {
                    bestRow.add(0);
                }
            }

            fromLeft.add(flRow);
            best.add(bestRow);
            fromDown.add(fdRow);
            fromUp.add(fuRow);
        }
    }

    protected static int get(int row, int col){
        return data.get(row).get(col);
    }

    protected static void updateFromLeft(int row, int col){
        if (col==0){
            fromLeft.get(row).set(col, get(row, col));
        } else{
            fromLeft.get(row).set(col, get(row, col) + best.get(row).get(col-1));
        }
    }

    protected static void updateFromDown(int row, int col){
        // If we're on the bottom row, our value comes from the left
        if (row == data.size()-1){
            fromDown.get(row).set(col, get(row, col) + best.get(row).get(col-1));
        } else {
            fromDown.get(row).set(col, get(row, col) + Math.min(fromDown.get(row+1).get(col), best.get(row).get(col-1)));
        }
    }

    protected static void updateFromUp(int row, int col){
        // If we're on the top row, our value comes from the left
        if (row == 0){
            fromUp.get(row).set(col, get(row, col) + best.get(row).get(col-1));
        } else {
            fromUp.get(row).set(col, get(row, col) + Math.min(fromUp.get(row-1).get(col), best.get(row).get(col-1)));
        }
    }

    protected static void updateBest(int row, int col){
        int bestVal = Math.min(Math.min(fromLeft.get(row).get(col), fromDown.get(row).get(col)), fromUp.get(row).get(col));
        best.get(row).set(col, bestVal);
    }

    private static void printMatrix(List<List<Integer>> matrix){
        for (List<Integer> row: matrix){
            System.out.println(row);
        }
    }

    private static void debug(){
        System.out.println("\nFROM LEFT");
        printMatrix(fromLeft);
        System.out.println("\nFROM DOWN");
        printMatrix(fromDown);
        System.out.println("\nFROM UP");
        printMatrix(fromUp);
        System.out.println("\nBEST");
        printMatrix(best);
    }
}
