public class AdditionRecursion {
    public static void main(String[] args) {
        System.out.println("AdditionRecursion.main");

        int[] input1 = new int[]  {1,2,3,4,5};
        int[] input2 = new int[]  {2,4,6,8};

        System.out.println("before: "  +  before(input1, 0));
//                + " after: " + after(input1, 0));
        //System.out.println("before: "  +  before(input2, 0) + " after: " + after(input2, 0));
    }
    
    private static int before(int[] input, int idx) {
        if (idx == input.length) {
            return 0;
        }

        //System.out.println("sumb: "+ input[idx]);
        int sum  = input[idx] + before(input, idx+1);
        System.out.println("sum: "+ sum);
        return sum;
    }

    private static int after(int[] input, int idx) {
        if (idx == input.length) {
            return 0;
        }

        int sum = after(input, idx+1) +  input[idx];
        System.out.println("suma: "+ input[idx]);
        return sum;
    }
}
