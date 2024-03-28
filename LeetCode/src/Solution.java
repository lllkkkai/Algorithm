import java.util.ArrayList;
import java.util.List;

public class Solution {
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

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        if (nums.length == 1)
            return nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            res = Math.max(dp[i], res);
        }
        return res;
    }

    public static void main(String[] args) {
//        int nums[] = new int[]{-1,-2};
//        ListNode node = new ListNode(1);
//        ListNode start = node;
//        for (int i = 2; i <= 5; i++) {
//            node.next = new ListNode(i);
//            node = node.next;
        Solution solution = new Solution();
        solution.rob(new int[]{1,1,1,1});
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode res = head;
        ListNode last = head;
        if (head == null)
            return head;
        int count = 1;
        while (count != left) {
            last = head;
            head = head.next;
            count++;
        }
        //
        ListNode head2 = head;
        while (count != right) {
            head2 = head2.next;
            count++;
        }
        ListNode a = head;
        a = reverse(last, head, head2);
        return res;
    }

    public ListNode reverse(ListNode last, ListNode left, ListNode right) {
        ListNode pre = last;
        ListNode cur = left;
        while (cur != right) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;
        int mid = l + (r - l) / 2;
        while (l <= r) {
            if (nums[mid] == target) {
                return func(nums, mid);
            } else if (nums[mid] > target) {
                r = mid - 1;
                mid = l + (r - l) / 2;
            } else {
                l = mid + 1;
                mid = l + (r - l) / 2;
            }
        }
        return new int[]{-1, -1};
    }

    public int[] func(int[] nums, int i) {
        int[] res = new int[2];
        int len = nums.length;
        boolean flag = true;
        int left = i - 1;
        int right = i + 1;
        while ((left >= 0 || right < len)) {
            if (nums[right] == nums[i] && right < len) {
                right++;
            }
            if (left >= 0 && nums[left] == nums[i]) {
                left--;
            }
            if (left < 0 && right == len) {
                break;
            }
            if (left >= 0 && nums[left] != nums[i] && nums[right] != nums[i]) {
                break;
            }
        }
        res[0] = left + 1;
        res[1] = right - 1;
        return res;
    }

    public int rob(int[] nums) {
        // 1 2 3 1 9
        // 1 2 4 4 13
        // 1 1 1 0 1
        if(nums.length == 1){
            return nums[0];
        }
        int[][] dp = new int[nums.length+1][2];
        dp[0][0] = nums[0];
        dp[0][1] = 1;
        if(nums[1] > nums[0]){
            dp[1][0] = nums[1];
            dp[1][1] = 1;
        } else {
            dp[1][0] = dp[0][0];
            dp[1][1] = 0;
        }
        //
        for(int i = 2; i<nums.length; i++){
            if(dp[i-1][1] == 1 && dp[i-2][1] == 1){
                if(dp[i-2][0] + nums[i] > dp[i-1][0]){
                    dp[i][0] = dp[i-2][0] + nums[i];
                    dp[i][1] = 1;
                } else{
                    dp[i][0] = dp[i-1][0];
                    dp[i][1] = 0;
                }
            }
            if(dp[i-1][1] == 0 && dp[i-2][1] == 1){
                if(dp[i-2][0] + nums[i] > dp[i-1][0]){
                    dp[i][0] = dp[i-2][0] + nums[i];
                    dp[i][1] = 1;
                } else{
                    dp[i][0] = dp[i-1][0];
                    dp[i][1] = 0;
                }
            }
        }
        return dp[nums.length-1][0];
    }
}