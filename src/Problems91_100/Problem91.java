package Problems91_100;

import java.util.ArrayList;
import java.util.List;

public class Problem91 {

    private static int maxCoord = 50;

    public static void main(String[] args) {
        long total = 0;
        List<Integer> sides = new ArrayList<>(3);
        for (int i=0; i<3; i++){
            sides.add(0);
        }

        for (int px=0; px <= maxCoord; px++){
            for (int py=0; py <= maxCoord; py++){
                for (int qx=0;qx <= maxCoord; qx++){
                    for (int qy=0; qy <= maxCoord; qy++){
                        if ((px==qx && py==qy) || (px+py)==0 || (qx+qy==0)){
                            continue;
                        }
                        sides.set(0, px*px+py*py);
                        sides.set(1, qx*qx+qy*qy);
                        sides.set(2, (px-qx)*(px-qx)+(py-qy)*(py-qy));
                        sides.sort(Integer::compareTo);
                        if (sides.get(0)+sides.get(1) == sides.get(2)){
                            System.out.println(String.format("(%d, %d) & (%d, %d)", px, py, qx, qy));
                            total += 1;
                        }
                    }
                }
            }
        }

        System.out.println(total/2);
    }

//    public static void main(String[] args) {
//        // Triangles with a vertex on each axis
//        long total = maxCoord*maxCoord;
//        // Triangles with one vertex on an axis and the other in the quadrant
//        total += 2*calculateAxis();
//        // Triangles with neither vertex on an axis
//        total += 2*floatingTrianglesWithRangleOnRight();
//
//        System.out.println(total);
//    }
//
//    private static long numRightTriangles(long px, long py){
//        long total = 0;
//        long diff;
//        long qy;
//        for (long qx=1; qx<px; qx++){
//            diff = px-qx;
//            if (diff%py == 0){
//                qy = px*((px-qx)/py) + py;
//                if (qy <= maxCoord){
//                    total += 1;
//                    System.out.println(String.format("Float: (%d, %d) & (%d, %d)", px, py, qx, qy));
//                }
//            }
//        }
//
//        return total;
//    }
//
//    private static long floatingTrianglesWithRangleAtX(long x){
//        long total = 0;
//        for (long y=1; y<maxCoord; y++){
//            total += numRightTriangles(x, y);
//        }
//        return total;
//    }
//
//    private static long floatingTrianglesWithRangleOnRight(){
//        long total = 0;
//        for (long x=1; x<=maxCoord; x++){
//            total += floatingTrianglesWithRangleAtX(x);
//        }
//        return total;
//    }
//
//    private static long calculateAxis(){
//        long total = 0;
//        for (long x=1; x<=maxCoord; x++){
//            total += calculateOneAxis(x);
//        }
//        return total;
//    }
//
//    private static long calculateOneAxis(long x){
//        // Triangles with vertical edge on right
//        long total = maxCoord;
//
//        for (long qx = 1; qx < x; qx++){
//            if (x%qx == 0 && isPerfectSquare((x/qx)-1)){
//                System.out.println(String.format("(%d, %d) & (%d, %d)", x, 0, qx, ((long)Math.sqrt(x/qx-1))*qx));
//                total += 1;
//            }
//        }
//        return total;
//    }
//
//    private static boolean isPerfectSquare(long x){
//        double sqrt = Math.sqrt(x);
//        return (int)sqrt == sqrt;
//    }
}
