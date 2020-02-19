import org.testng.Assert;

import java.util.Arrays;
import java.util.BitSet;
import java.util.stream.IntStream;

/**
 * On the first row, we write a 0. Now in every subsequent row,
 * we look at the previous row and replace each occurrence of 0 with 01,
 * and each occurrence of 1 with 10.
 *
 * Given row N and index K, return the K-th indexed symbol in row N.
 * (The values of K are 1-indexed.) (1 indexed).
 *
 * Examples:
 * Input: N = 1, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 1
 * Output: 0
 *
 * Input: N = 2, K = 2
 * Output: 1
 *
 * Input: N = 4, K = 5
 * Output: 1
 *
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 */
public class KthSymbol {

    public static void main(String[] args) {
        System.out.println("KthSymbol.main");

       // test(2,1,0);
       // test(2,2,1);
       //test(4,5,1);
       //test(4,8,1);
       test(30, 434991989,0);
    }

    private static void test(int n, int k, int expected) {
        System.out.println("\nn = [" + n + "], k = [" + k + "]");


        int actual1 = memoryHogSolution(n, k);

        int actual2 = bitSetSolution(n, k);

        System.out.printf("expected: %d, actual1: %d, actual2: %d\n",
                expected, actual1, actual2);

        Assert.assertEquals(actual1, expected);
        Assert.assertEquals(actual2, expected);
    }

    /**
     * This is a binary explosion - each number becomes 2.
     *
     * The idea is to use a more optimized data structure like bitset or boolean array
     * because of only 2 possible values per bit.
     * - number of bits would be 2^(n-1)
     *
     * row 1: 0
     * row 2: 01
     * row 3: 0110  (2^(3-2) - 1) => 4-1 =3
     * row 4: 01101001   (2^(4-2) - 1) => (8-1) = 7
     * row 5: 0110100110010110  (2^(5-2) - 1
     *
     * Approach:
     * - create a bitset or boolean array if size 2^(n-1)
     * - since we are re-using the same array
     *   - we need to generate the bits from the end
     *   - 3 => 7
     *   - 6 => 13
     *   - 7 => 15
     *   - bitIdx => (bitIdx*2) + 1
     *
     * @param n
     * @param k
     * @return
     */
    private static int bitSetSolution(int n, int k) {
        int numBits = (int)Math.pow(2, (n-1));
        System.out.printf("numbits: %d\n", numBits);
        boolean[] bitSet = new boolean[numBits];

        bitSetSolutionHelper(n, bitSet);

        //final StringBuilder buffer = new StringBuilder(numBits);
        //IntStream.range(0, numBits).mapToObj(i -> bitSet[i] ? '1' : '0').forEach(buffer::append);


        //System.out.println("bitset: " + buffer.toString());

        if (bitSet[k-1]) {
            return 1;
        } else {
            return 0;
        }
    }

    private static void bitSetSolutionHelper(int n,  boolean[] bitset) {
        if (n == 1) {
            bitset[0] = false;
        } else {
            bitSetSolutionHelper(n-1, bitset);

            /*final StringBuilder buffer = new StringBuilder((n-1) * 2);
            IntStream.range(0, (int)Math.pow(2, n-2)).mapToObj(i -> bitset[i] ? '1' : '0').forEach(buffer::append);
            System.out.printf("\nn: %d, bitset: %s\n", n,
                    buffer.toString());*/

            int rightMostFromBit = (int)Math.pow(2, n-2) - 1;
            int rightMostToBit = rightMostFromBit * 2 + 1;

            /*System.out.printf("rightMostFromBit: %d, rightMostToBit: %d\n",
                    rightMostFromBit, rightMostToBit);*/

            for (int start = rightMostFromBit; start >= 0; start--) {
                int bitIdx = start;
                int rightIdx = rightMostToBit;
                int leftIdx = rightIdx - 1;

                /*System.out.printf("n: %d, start: %d, bitIdx: %d, rightIdx: %d, leftIdx: %d\n",
                        n, start, bitIdx, rightIdx, leftIdx);*/

                if (bitset[bitIdx]) { // 1 to 10
                   bitset[rightIdx] = false;
                   bitset[leftIdx] = true;
                } else {
                    bitset[rightIdx] = true;  // 0 to 01
                    bitset[leftIdx] =  false;
                }
                rightMostToBit = rightMostToBit - 2;

            }
            /*System.out.printf("\n after n-1: %d, bitset: %s\n", n-1,
                    buffer.toString());*/
        }
    }


    private static int memoryHogSolution(int n, int k) {
        String str = generate(n, k);

        char charAt = str.charAt(k-1);

        if (charAt == '0') {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Build the string from the bottom up
     *  n = 1, n = 2, n = 3, etc
     *
     * This consumes lots of memory when n is large like 30
     *   2^30 * 8 => ~16GB
     *
     * Notice we only care about 1 bit
     *
     *
     * @param n
     * @param k
     * @return
     */
    private static String generate(int n, int k) {
        if (n == 1) {
            return "0";
        } else {
            String str = generate(n-1, k);
            if (str.length() >= k) {
                return str;
            }
            StringBuilder buf = new StringBuilder();

            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (charAt == '0') {
                    buf.append("01");
                } else if (charAt == '1') {
                    buf.append("10");
                }
            }

            return buf.toString();
        }

    }
}
