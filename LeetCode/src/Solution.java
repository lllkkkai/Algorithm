import java.util.ArrayList;
import java.util.List;

class Solution {
    //    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> output = new ArrayList();
//        output.add(new ArrayList<Integer>());
//        for (int num : nums) {
//            List<List<Integer>> newSubsets = new ArrayList();
//            for (List<Integer> curr : output) {
//                newSubsets.add(new ArrayList<Integer>(curr){{add(num);}});
//            }
//            for (List<Integer> curr : newSubsets) {
//                output.add(curr);
//            }
//        }
//        return output;
//    }
    static List<List<Integer>> res = new ArrayList();
    static ArrayList<Integer> path = new ArrayList();

    public static List<List<Integer>> subsets(int[] nums) {
        backTrace(nums, 0);
        return res;
    }

    public static void backTrace(int[] nums, int index) {
        res.add(new ArrayList<>(path));
        for (int i = index; i < nums.length; i++) {
            path.add(nums[index]);
            backTrace(nums, i + 1);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int nums[] = new int[]{2,1,1,1,1};
//        subsets(nums);
        System.out.println(jump(nums));
    }

    public static int jump(int[] nums) {
        // 0 1 2 3 4 5 6
        // 3 4 3 2 5 4 3
        // 1 1 1 1 2 2 2
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        int seconde = 0;
        int sum = nums[0];
        if (len == 1) {
            if (nums[0] == 1 || nums[0] == 0)
                return 0;
            return 1;
        }
        for (int i = 1; i < len; i++) {
            seconde = Math.max(nums[i-1], seconde);
            if (sum >= i) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + 1;
                sum = sum + seconde;
                seconde = 0;
            }
        }
        return dp[len - 1];
    }

}