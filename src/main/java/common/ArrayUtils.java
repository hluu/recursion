package common;

import java.util.List;

public class ArrayUtils {
    public static void swap(int[] input, int i, int j) {
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    public static void printResult(List<String> result, String message) {
        System.out.println("=====> " + message + " <======");
        for (String row : result) {
            System.out.println(row);
        }
        System.out.println();
    }

    public static void printResult(char[][] input) {
        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[0].length; col++) {
                System.out.print(input[row][col]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}
