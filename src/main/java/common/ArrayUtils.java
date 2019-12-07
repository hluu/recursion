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
}
