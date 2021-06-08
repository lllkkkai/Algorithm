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

