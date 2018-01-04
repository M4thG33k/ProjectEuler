package Problems81_90;

import java.util.ArrayList;
import java.util.List;

public class Problem85 {

    public static void main(String[] args) {
        List<List<Long>> values = new ArrayList<>();

        List<Long> row = createRow(1, 1);
        while (true){
            if (row.size() == 1 && values.size() > 1 && values.get(values.size()-1).size() == 1){
                break;
            }
            values.add(row);
            row = createRow(values.get(values.size()-1).get(0)+(values.size()+1), values.size()+1);
        }

        long diff = 2000000;
        int bestX=0;
        int bestY=0;
        int size;
        long delta;

        for (int i=0; i< values.size(); i++){
            row = values.get(i);
            size = row.size();
            delta = row.get(size-1) - 2000000L;
            if (delta < diff){
                diff = delta;
                bestX = i+1;
                bestY = size;
            }

            if (size > 1){
                delta = 2000000 - row.get(size-2);
                if (delta < diff){
                    diff = delta;
                    bestX = i+1;
                    bestY = size-1;
                }
            }
        }

        System.out.println(String.format("%dx%d=%d => %d", bestX, bestY, bestX*bestY, diff));

    }

    private static List<Long> createRow(long firstVal, int rowNumber){
        List<Long> row = new ArrayList<>();
        row.add(firstVal);

        while (row.get(row.size()-1) < 2000000L){
            row.add(row.get(row.size()-1) + (1+row.size())*((rowNumber*(rowNumber+1))/2));
        }

        return row;
    }
}
