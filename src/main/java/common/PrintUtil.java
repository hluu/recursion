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
}
