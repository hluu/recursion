import java.util.ArrayList;
import java.util.List;

/**
 * Binary string w/ wildcard
 */
public class StringFromWildCard {
    public static void main(String[] args) {
        System.out.println("StringFromWildCard.main");
        test("1?10");
    }

    private static void test(String s) {
        System.out.printf("s: %s\n", s);


        List<String> collector = new ArrayList<String>();
        helper(s, 0, new StringBuilder(), collector);

        String[] result = new String[collector.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = collector.get(i);
        }
        for (int i = 0; i < result.length; i++) {
            System.out.printf("%s\n", result[i]);
        }
    }

    private static void helper(String s, int idx, StringBuilder soFar, List<String> coll) {
        if (idx == s.length()) {
            coll.add(soFar.toString());
            return;
        } else {
            if (s.charAt(idx) == '?') {
                soFar.append("0");
                helper(s, idx+1, soFar, coll);
                soFar.deleteCharAt(soFar.length()-1);

                soFar.append("1");
                helper(s, idx+1, soFar, coll);
                soFar.deleteCharAt(soFar.length()-1);
            } else {
                soFar.append(s.charAt(idx));
                helper(s, idx+1, soFar, coll);
                soFar.deleteCharAt(soFar.length()-1);
            }
        }

    }
}
