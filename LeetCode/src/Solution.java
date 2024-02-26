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
        int nums[] = new int[]{1,1,3,1,1};
        Solution solution = new Solution();
        System.out.println(solution.jump(nums));
    }

    public int jump(int[] nums) {

    }
}