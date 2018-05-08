package Problems91_100;

import Util.LargeNumber;

// I've solved 99 problems but this ain't one...yet...
public class Problem100 {

    // We're trying to solve (a/n)*((a-1)/(n-1)) = 1/2.
    // Manipulating this (complete the square too) yields (2n-1)^2-2(2a-1)^2 = 1
    // Letting x = 2n-1 and y = 2a-1 and rearranging yields x^2 - 2y^2 = -1.
    // Look! The negative Pell equation!
    public static void main(String[] args) {
        LargeNumber oldX = new LargeNumber(1);
        LargeNumber oldY = new LargeNumber(1);

        LargeNumber x;
        LargeNumber y;

        LargeNumber xTarget = new LargeNumber((long)2e12 - 1);

        while (!oldX.isGreateThanOrEqualTo(xTarget)){
            x = oldX.timesReturn(3).addReturn(oldY.timesReturn(4));
            y = oldX.timesReturn(2).addReturn(oldY.timesReturn(3));

            oldX = x;
            oldY = y;
        }

        System.out.println("A total of " + oldX.addReturn(1).dividedByTwo() + " discs with " + oldY.addReturn(1).dividedByTwo() + " blue!");
    }
}