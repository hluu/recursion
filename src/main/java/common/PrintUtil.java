package common;

public class PrintUtil {
    /**
     * Simple utility to print out to console if debug parameter is true
     * @param debug
     * @param format
     * @param args
     */
    public static void printf(boolean debug, String format, Object ... args) {
        if (debug) {
            System.out.printf(format, args);
        }
    }

    public static void printWithLevel(int level, String msg) {
        System.out.printf("%2d ", level);
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        System.out.println(msg);
    }
}
