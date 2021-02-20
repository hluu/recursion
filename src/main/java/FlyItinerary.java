import org.testng.Assert;

import java.util.*;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 *     If there are multiple valid itineraries, you should return the itinerary that has the
 *     smallest lexical order when read as a single string. For example,
 *     the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 *     All airports are represented by three capital letters (IATA code).
 *     You may assume all tickets form at least one valid itinerary.
 *     One must use all the tickets once and only once.
 *
 * Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 *
 * Analysis:
 * - need some sort of a mapping between from airport -> a list of airport (sorted order)
 * - JFK -> ATL, SFO
 * - SFL -> ATL
 * - ATL ->  JFK, SFO
 *
 * - start from JFK
 *
 */
public class FlyItinerary {
    public static void main(String[] args) {
        System.out.println("FlyItinerary.main");
        List<List<String>> itinerary1 = new ArrayList<>();
        itinerary1.add(Arrays.asList("MUC", "LHR"));
        itinerary1.add(Arrays.asList("JFK", "MUC"));
        itinerary1.add(Arrays.asList("SFO", "SJC"));
        itinerary1.add(Arrays.asList("LHR", "SFO"));

        test(itinerary1, Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC"));

        List<List<String>> itinerary2 = new ArrayList<>();
        itinerary2.add(Arrays.asList("JFK","SFO"));
        itinerary2.add(Arrays.asList("JFK","ATL"));
        itinerary2.add(Arrays.asList("SFO","ATL"));
        itinerary2.add(Arrays.asList("ATL","JFK"));
        itinerary2.add(Arrays.asList("ATL","SFO"));

        test(itinerary2, Arrays.asList("JFK","ATL","JFK","SFO","ATL","SFO"));

        List<List<String>> itinerary3 = new ArrayList<>();
        itinerary3.add(Arrays.asList("JFK", "KUL"));
        itinerary3.add(Arrays.asList("JFK", "NRT"));
        itinerary3.add(Arrays.asList("NRT", "JFK"));

        test(itinerary3, Arrays.asList("JFK","NRT","JFK","KUL"));

        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("NRT");
        queue.add("KUL");

        while (!queue.isEmpty()) {
            System.out.println("airport: " + queue.poll());
        }
    }

    private static void test(List<List<String>> itinerary, List<String> expected) {
        System.out.printf("itinerary: %s\n", itinerary.toString());

        List<String> actual = buildItinerary(itinerary);
        System.out.printf("expected: %s, actual: %s", expected,actual);

        System.out.println("\n");

        Assert.assertEquals(actual, expected);
    }

    private static List<String> buildItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> flyMap = new HashMap<>();

        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            if (!flyMap.containsKey(from)) {
                PriorityQueue<String> toList = new PriorityQueue<>();
                toList.add(to);
                flyMap.put(from, toList);
            } else {
                flyMap.get(from).add(to);
            }
        }

        List<String> itineraryList = new ArrayList<>();
        //itineraryList.add("JFK");
        helper(flyMap, "JFK", itineraryList);
        return itineraryList;
    }

    private static void helper(Map<String, PriorityQueue<String>> flyMap, String from,
                               List<String> soFar) {

       /* while (flyMap.containsKey(from) && !flyMap.get(from).isEmpty()) {
            helper(flyMap, flyMap.get(from).poll(), soFar);
        }
        soFar.add(0, from);*/

        if (!flyMap.containsKey(from) || flyMap.get(from).isEmpty()) {
            soFar.add(0, from);
            return;
        } else {
            PriorityQueue<String> toList = flyMap.get(from);
            while (!toList.isEmpty()) {
                String to = toList.poll();
                helper(flyMap, to, soFar);
            }
            soFar.add(0, from);

        }
    }


}
