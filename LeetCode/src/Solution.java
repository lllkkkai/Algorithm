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

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        if(nums.length == 1)
            return nums[0];
        for(int i = 1; i<nums.length;i++){
            dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
            res = Math.max(dp[i], res);
        }
        return res;
    }

    public static void main(String[] args) {
//        int nums[] = new int[]{-1,-2};
        ListNode node = new ListNode(1);
        ListNode start = node;
        for(int i =2;i<=5;i++){
            node.next = new ListNode(i);
            node = node.next;
        }
        Solution solution = new Solution();
        System.out.println(solution.reverseBetween(start,2,4));
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
}