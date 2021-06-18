# LeetCode

精选了我在力扣上做的觉得很不错的题目

## [494.目标和](https://leetcode-cn.com/problems/target-sum/)

给你一个整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同表达式的数目。

**示例 1：**

> 输入：nums = [1,1,1,1,1], target = 3
> 输出：5
> 解释：一共有 5 种方法让最终目标和为 3 。
> -1 + 1 + 1 + 1 + 1 = 3
> +1 - 1 + 1 + 1 + 1 = 3
> +1 + 1 - 1 + 1 + 1 = 3
> +1 + 1 + 1 - 1 + 1 = 3
> +1 + 1 + 1 + 1 - 1 = 3

**示例 2：**

> 输入：nums = [1], target = 1
> 输出：1

**题解**

1、回溯法

数组的每个元素都可以添加符号，因此每个元素有 2 种添加符号的方法，n 个数共有 2^n 种添加符号的方法，对应 2^n 种不同的表达式。当 n 个元素都添加符号之后，即得到一种表达式，如果表达式的结果等于目标数 target，则该表达式即为符合要求的表达式。

可以使用回溯的方法遍历所有的表达式，回溯过程中维护一个计数器 count，当遇到一种表达式的结果等于目标数 target 时，将 count 的值加 1。遍历完所有的表达式之后，即可得到结果等于目标数 target 的表达式的数目。

可以理解为一个 n 层的二叉树，2^n 个叶子

时间复杂度：O(2^n)，空间复杂度：O(n)

```Java
class Solution {
    int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }
}
```

## [1049. 最后一块石头的重量 II](https://leetcode-cn.com/problems/last-stone-weight-ii/)

有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。

每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

- 如果 x == y，那么两块石头都会被完全粉碎；

- 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。

最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。

**示例1：**

> 输入：stones = [2,7,4,1,8,1]
> 输出：1
> 解释：
> 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
> 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
> 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
> 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值

**示例**2：

>输入：stones = [31,26,33,21,40]
>
>输出：5

**示例3：**

> 输入：stones = [1,2]
>
> 输出：1

**题解：**

1、0-1背包问题

```Java
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for(int stone : stones) 
            sum += stone;
        int neg = sum >> 1;
        boolean[] dp = new boolean[neg+1];
        dp[0] = true;
        for(int stone : stones) {
            for(int i = neg; i >= stone; i--) 
                dp[i] |= dp[i-stone];
        }
        for(int i = neg; i >= 0; i--) {
            if(dp[i]) 
                return sum - (i << 1);
        }
        return sum;
    }
}
```

## [518. 零钱兑换 II](https://leetcode-cn.com/problems/coin-change-2/)

给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 

**示例1：**

> 输入: amount = 5, coins = [1, 2, 5]
> 输出: 4
> 解释: 有四种方式可以凑成总金额:
> 5=5
> 5=2+2+1
> 5=2+1+1+1
> 5=1+1+1+1+1

**示例2：**

> 输入: amount = 3, coins = [2]
> 输出: 0
> 解释: 只用面额2的硬币不能凑成总金额3。

**示例3：**

>输入: amount = 10, coins = [10] 
>输出: 1

**题解：**

1、动态规划

维护一个dp数组，

```Java
class Solution {
    public int change(int amount, int[] coins) {
        int [] dp = new int [amount+1];
        Arrays.fill(dp, 0);
        dp[0] = 1;

        for(int i = 0; i<coins.length; i++){
            for(int j = coins[i]; j <= amount; j++){
                if(j >= coins[i])
                    dp[j] = dp[j]+dp[j-coins[i]];
            }
        }
        return dp[amount];
    }
}
```

## [374. 猜数字大小](https://leetcode-cn.com/problems/guess-number-higher-or-lower/)

猜数字游戏的规则如下：

- 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
- 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。

你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：

- -1：我选出的数字比你猜的数字小 pick < num

- 1：我选出的数字比你猜的数字大 pick > num
- 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num

返回我选出的数字。

**示例 1：**

> 输入：n = 10, pick = 6
> 输出：6

**示例2：**

> 输入：n = 1, pick = 1
> 输出：1

**示例 3：**

> 输入：n = 2, pick = 1
> 输出：1

**示例 4：**

> 输入：n = 2, pick = 2
> 输出：2

**题解：**

典型的二分法，注意mid=min+(max-min)/2不要越界即可

```Java
/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 * 				  注意，这里的num表示要得到的数，比你输入的数小
 *			      1 if num is higher than the guess number
 *                同理，这里的num表示要得到的数，比你输入的数大
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int min = 1;
        int max = n;
        int mid = min+(max-min)/2;
        //小心min+max越界
        int flag = guess(mid);

        while(flag != 0){
            if(flag == -1)
                max = mid-1;
            if(flag == 1)
                min = mid+1;
            mid = min+(max-min)/2;
            flag = guess(mid);
        }
        return mid;
    }
}
```

## [39. 组合总和](https://leetcode-cn.com/problems/combination-sum/)

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

- 所有数字（包括 target）都是正整数。
- 解集不能包含重复的组合。 

**示例 1：**

> 输入：candidates = [2,3,6,7], target = 7,
> 所求解集为：
> [
>   [7],
>   [2,2,3]
> ]

**示例2:** 

> 输入：candidates = [2,3,5], target = 8,
> 所求解集为：
> [
>   [2,2,2,2],
>   [2,3,3],
>   [3,5]
> ]

**题解：**

回溯法，add，back()，remove经典三步走

```Java
class Solution {
    List<List<Integer>> res = new ArrayList();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<Integer> al = new ArrayList();
        Arrays.sort(candidates);
        back(al, candidates, target, 0);
        return res;
    }

    public void back(ArrayList al, int[] candidates, int target, int start){
        if(target == 0){
            res.add(new ArrayList<>(al));
            return;
        }
        else if(target < 0)
            return;

        if(candidates[0] > target)
            return;
        else{
            for(int i = start; i<candidates.length; i++){
                al.add(candidates[i]);
                back(al, candidates, target-candidates[i], i);
                al.remove(al.size()-1);
            }
            return;
        }
    }
}
```

## [16. 最接近的三数之和](https://leetcode-cn.com/problems/3sum-closest/)

给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

**示例：**

> 输入：nums = [-1,2,1,-4], target = 1
> 输出：2
> 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) .

**题解：**

此题为字节教育一面题

将数组排序后，先选中第 i 个数，（相当于成了两数之和）然后选后面一位和最后一位，保证所有可能即可

```Java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int close = nums[0]+nums[1]+nums[2];
        for(int i = 0; i<nums.length-2; i++){
            int left = i+1, right = nums.length-1;
            while(left<right){
                int three = nums[i]+nums[left]+nums[right];
                if(Math.abs(three-target)<Math.abs(close-target)){
                    close = three;
                }
                if(three>target){
                    right--;
                }
                else if(three<target){
                    left++;
                }
                else{
                    return target;
                }
            }
        }
        return close;
    }
}
```

## [3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

给定一个字符串，请你找出其中不含有重复字符的**最长子串**的长度。

**示例1：** 

> 输入: s = "abcabcbb"
> 输出: 3 
> 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

**示例2：**

> 输入: s = "bbbbb"
> 输出: 1
> 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

**示例3:**

> 输入: s = "pwwkew"
> 输出: 3
> 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
>      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

**示例4:**

> 输入: s = ""
>
> 输出: 

**题解：**

滑动窗口思想，简单易懂

```Java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }

        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int length = 0;
        while( right<s.length()){
            if(set.contains(s.charAt(right))){
                set.remove(s.charAt(left++));
            }else{
                set.add(s.charAt(right++));
            }
            length = set.size()>length ? set.size():length;
        }
        return length;
    }
}
```

## [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

进阶：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？

**示例** 1：

> 输入：nums = [5,7,7,8,8,10], target = 8
> 输出：[3,4]

**示例** 2：

> 输入：nums = [5,7,7,8,8,10], target = 6
> 输出：[-1,-1]

**示例** 3：

> 输入：nums = [], target = 0
> 输出：[-1,-1]

**题解**：

经典二分

```Java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] a = {-1,-1};
        if(nums.length == 0)
            return a;
        if(nums.length == 1){
            if(target == nums[0]){
                a[0] = 0;
                a[1] = 0;
            }
            return a;
        }
        int left = 0;
        int right = nums.length-1;
        int mid = left+(right-left)/2;
        int count = 0;
        boolean flag = false;
        while(flag == false){
             while(left<=right){
                 mid = left+(right-left)/2;
                 if(target>nums[mid]){
                     left = mid+1;
                 }
                 if(target<nums[mid]){
                     right = mid-1;
                 }
                 if(target == nums[mid]){
                     flag = true;
                     break;
                 }
            }
            if(flag == false){
                break;
            }
            else{
                left = mid;
                right = mid;
                while(nums[left] == target && left>=0){
                    left--;
                    if(left == -1)
                        break;
                }
                while(nums[right] == target && right<=nums.length-1){
                    right++;
                    if(right == nums.length)
                        break;
                }
                a[0] = left+1;
                a[1] = right-1;
            }
        }
        return a;
    }
}
```

## [55. 跳跃游戏](https://leetcode-cn.com/problems/jump-game/)

给定一个非负整数数组 nums ，你最初位于数组的 **第一个下标** 。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个下标。 

**示例** 1：

> 输入：nums = [2,3,1,1,4]
> 输出：true
> 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。

**示例** 2：

> 输入：nums = [3,2,1,0,4]
> 输出：false
> 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。

**题解：**

意思是每个格子决定当前可以走的步数上限，为0必然会停止不动

以 [2,3,1,1,4] 为例，max=2，表示我们可以走2、1、0步，即走i<=2的步数

i = 1

i<=max == 2

max = 2 ? 3 + 1 = 4

i = 2

i<=max == 4

max = 4?1+2 = 4

i = 3

i<= max ==4

  

```Java
class Solution {
    public boolean canJump(int[] nums) {
        if(nums.length <= 1)
            return true;
        int max = nums[0];
        for(int i = 1; i<nums.length-1; i++){
            if(i<=max){
                max = Math.max(max, nums[i] + i);
            }
        }
        return max>=nums.length-1;
    }
}
```

## [19. 删除链表的倒数第 N 个结点](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/)

给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。

**进阶：**你能尝试使用一趟扫描实现吗？

**示例 1：**

> 输入：head = [1,2,3,4,5], n = 2
> 输出：[1,2,3,5]

**示例2：**

> 输入：head = [1], n = 1
> 输出：[]

**题解：**

```Java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null)
            return null;
        ListNode fast = head;
        ListNode slow = head;
        for(int i = 0; i<n; i++){
            fast = fast.next;
        }
        if(fast == null)
            return head.next;
        while(fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
```

