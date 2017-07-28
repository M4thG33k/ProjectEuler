package Problems1_10;

public class Problem8 {

    public static void main(String[] args) {
        String number = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450";

        // Turn the number into an array so we only have to parse pieces once;
        long[] numAsArray = new long[number.length()];

        for (int i=0; i<number.length(); i++)
        {
            numAsArray[i] = Integer.parseInt(number.substring(i, i+1));
        }

        int numAdj = 13;
        long maxProd;

        // get the product of the first numAdj integers
        long val = 1;
        for (int i=0; i<numAdj; i++)
        {
            val *= numAsArray[i];
        }

        maxProd = val;

        for (int i=numAdj; i<numAsArray.length; i++)
        {
            // if the first value of the sequence is zero, we have to recalculate
            if (numAsArray[i-numAdj] == 0)
            {
                val = numAsArray[i];
                for (int j=1; j<numAdj; j++)
                {
                    val *= numAsArray[i-j];
                }
            }
            else // otherwise, we simply divide by that value and multiply by the new one
            {
                val = (val / numAsArray[i-numAdj]) * numAsArray[i];
            }

            if (val > maxProd)
            {
                maxProd = val;
            }
        }

        System.out.println(maxProd);
    }
}
