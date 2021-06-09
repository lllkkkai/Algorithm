# 剑指offer

点击🔜[这里](https://www.nowcoder.com/ta/coding-interviews)开始自己做题

挑了一些值得反复做的题，硬做没什么算法思想的就不囊括了

## JZ3、从尾到头打印单链表

输入一个链表，按链表从尾到头的顺序返回一个ArrayList

**示例**

> 输入：{67,0,24,58}
>
> 返回值：[58,24,0,67]

**题解**

1、非递归法

新建一个ArrayList，每次add在固定零下标的位置，实现反转

时间复杂度On，空间复杂度On

```Java
import java.util.*;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode tmp = listNode;
        while(tmp!=null){
            list.add(0,tmp.val);
            tmp = tmp.next;
        }
        return list;
    }
}
```

2、递归法

递归就不用解释了，看了都懂，没学会的多复习复习[树的遍历]()

时间复杂度O(n)，空间复杂度O(n)

```Java
import java.util.*;
public class Solution {
    ArrayList<Integer> list = new ArrayList();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode!=null){
            printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }
}
```

3、反转链表法

见[反转链表]()

时间复杂度O(n)，空间复杂度O(n)

```Java
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode pre = null;
        ListNode cur = listNode;
        ListNode temp = cur;
        while(cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        ArrayList<Integer> res = new ArrayList();
        while(pre != null){
            res.add(pre.val);
            pre = pre.next;
        }
        return res;
    }
}
```

## JZ4、重建二叉树

输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。

**示例**

> 输入：[1,2,3,4,5,6,7],[3,2,4,1,6,5,7]
>
> 返回值：{1,2,5,3,4,6,7}

**题解**

所有的二叉树题目都可以从三元素树出发进行递归求解

根据中序遍历和前序遍历可以确定二叉树，具体过程为：

1. 根据前序序列第一个结点确定根结点
2. 根据根结点在中序序列中的位置分割出左右两个子序列
3. 对左子树和右子树分别递归使用同样的方法继续分解

例如：

前序序列{1,2,4,7,3,5,6,8} = pre
中序序列{4,7,2,1,5,3,8,6} = in

1. 根据当前前序序列的第一个结点确定根结点，为 1
2. 找到 1 在中序遍历序列中的位置，为 in[3]
3. 切割左右子树，则 in[3] 前面的为左子树， in[3] 后面的为右子树
4. 则切割后的**左子树前序序列**为：{2,4,7}，切割后的**左子树中序序列**为：{4,7,2}；切割后的**右子树前序序列**为：{3,5,6,8}，切割后的**右子树中序序列**为：{5,3,8,6}
5. 对子树分别使用同样的方法分解

```Java
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.Arrays;
public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        // 在中序中找到前序的根
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                // 左子树，注意 copyOfRange 函数，左闭右开
                root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                // 右子树，注意 copyOfRange 函数，左闭右开
                root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }
}
```

## JZ6、旋转数组的最小数字

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

**示例**

>输入：[3,4,5,1,2]
>
>返回值：1

**题解**

1、暴力法

遍历一遍数组即可，时间复杂度O(n)

2、二分法

通过二分的方法，不断去更新存在于两个子数组(两个非递减排序子数组)中的下标。时间复杂度O(log(n))

```Java
public int minNumberInRotateArray(int[] array) {
       if (array.length == 0) {
           return 0;
       }
       int l = 0;
       int r = array.length - 1;
       while (l < r - 1) {
           int mid = (l + r) >> 1;
           if (array[mid] >= array[l]) {
               l = mid; /// 说明mid所在的位置是在第一个非递减子数组中
           } else if (array[mid] <= array[r]) {
               r = mid; /// 说明mid所在的位置是在第二个非递减子数组中
           }
       }
       return array[r];
   }`
```

## JZ8、跳台阶

一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。

**题解**

1、递归法

假设f[i]表示在第i个台阶上可能的方法数。逆向思维。如果我从第n个台阶进行下台阶，下一步有2中可能，一种走到第n-1个台阶，一种是走到第n-2个台阶。

所以f[n] = f[n-1] + f[n-2]

那么初始条件了，f[0] = f[1] = 1

所以就变成了：f[n] = f[n-1] + f[n-2], 初始值f[0]=1, f[1]=1，目标求f[n]

时间复杂度：O(2^n)，空间复杂度：递归栈的空间

```Java
public class Solution {
    public int jumpFloor(int target) {
        if (target<=1) 
            return 1;
        return jumpFloor(target-1) + jumpFloor(target-2);
    }
}
```

2、动态规划

递归法是自上往下，动态规划的思想是自底向上型循环求解

时间复杂度：O(n)，空间复杂度：O(1)

```Java
public class Solution {
    public int JumpFloor(int target) {
        // f[1] = 1, f[0] = 1 (f[0]是为了简便作答自己添加的)
        int a = 1, b = 1;
        for (int i = 2; i <= target; i++) {
            // 求f[i] = f[i - 1] + f[i - 2]
            a = a + b; // 这里求得的 f[i] 可以用于下次循环求 f[i+1]
            // f[i - 1] = f[i] - f[i - 2]
            b = a - b; // 这里求得的 f[i-1] 可以用于下次循环求 f[i+1]
        }
        return a;
    }
}
```

## JZ9、变态跳台阶（Not）

一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。

**示例：**

>输入：3
>
>返回值：4

**题解**

1、暴力法

## JZ10、矩形覆盖

我们可以用2×1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2×1的小矩形无重叠地覆盖一个2×n的大矩形，从同一个方向看总共有多少种不同的方法？

比如n=3时，2×3的矩形块有3种不同的覆盖方法(从同一个方向看)：

![图1](./img/jz10.png)

**输入描述：**

2×1的小矩形的总个数n

**返回值描述：**

覆盖一个2×n的大矩形总共有多少种不同的方法(从同一个方向看)

**示例**

>输入：0
>
>返回值：0
>
>
>
>输入：1
>
>返回值：1
>
>
>
>输入：4
>
>返回值：5

**题解**

动态规划的思想，寻找状态转移方程

f[n] = f[n-1] + f[n-2]，初始条件f[1] = 1, f[2] =2

时间复杂度：O（n），空间复杂度：O（1）

```Java
public class Solution {
    public int rectCover(int target) {
        if(target <= 2)
            return target;
        int pre1 = 1;
        int pre2 = 2;
        int res = 0;
        for (int i = 2; i< target; i++){
            res = pre1+pre2;
            pre1=pre2;
            pre2 = res;
        }
        return res;
    }
}
```

## JZ14、链表中倒数第k个结点

输入一个链表，输出该链表中倒数第k个结点。如果该链表长度小于k，请返回空。

**示例：**

>输入：{1，2，3，4，5}，1
>
>返回值：{5}

**题解：**

1、双指针法

fast指针在前先走k位，slow指针随后从头开始同时前行，直到fast指向null为止

```Java
public ListNode FindKthToTail (ListNode pHead, int k) {
        // write code here
        ListNode fast = pHead;
        for(int i=0; i<k; i++){
            if(fast == null) return fast;
            fast = fast.next;
        }
        ListNode slow = pHead;
        while(fasr!=null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
```

2、栈方法

过于麻烦，不写了，知道思想就可以

3、递归法

浅显易懂，递到尾指针然后开始size自增，==k时返回该节点，即可

```Java
int size; public ListNode FindKthToTail(ListNode pHead, int k) {    if (pHead == null)        return pHead;    ListNode node = FindKthToTail(pHead.next, k);    if (++size == k)        return pHead;    return node;}
```

## JZ15、反转链表（Not）

输入一个链表，反转链表后，输出新链表的表头。

**示例：**

>输入：{1，2，3}
>
>返回值：{3，2，1}

**题解：**

1、栈方法

链表全部节点进栈，再重新出栈组成一个新链表

```Java
import java.util.Stack;
public class Solution {
    public ListNode ReverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        //把链表节点全部摘掉放到栈中
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty())
            return null;
        ListNode node = stack.pop();
        ListNode dummy = node;
        //栈中的结点全部出栈，然后重新连成一个新的链表
        while (!stack.isEmpty()) {
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        //最后一个结点就是反转前的头结点，一定要让他的next
        //等于空，否则会构成环
        node.next = null;
        return dummy;
    }
}
```

2、双链表法

将旧链表的节点一个一个摘除即可

```Java
public ListNode ReverseList(ListNode head) {
    //新链表
    ListNode newHead = null;
    while (head != null) {
        //先保存访问的节点的下一个节点，保存起来
        //留着下一步访问的
        ListNode temp = head.next;
        //每次访问的原链表节点都会成为新链表的头结点，
        //其实就是把新链表挂到访问的原链表节点的
        //后面就行了
        head.next = newHead;
        //更新新链表
        newHead = head;
        //重新赋值，继续访问
        head = temp;
    }
    //返回新链表
    return newHead;
}
```

3、递归法

## JZ17、树的子结构

输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）

**示例：**

>输入：{8,8,#,9,#,2,#,5}，{8,9,#,2}
>
>返回值：true

**题解：**

1、递归法

大部分树的题目都可以用递归法求解，从root1的根节点开始遍历，判断与root2的根节点是否相等，相等则进入IfSame，否则，递归遍历root1的左右子树

IfSame有三种情况，1.root1为空，root2不为空，返回false；2.root2为空，返回true；3.root1与root2值不相等，返回false；

```Java
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}
*/
public class Solution {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean res = false;
        if(root1 == null || root2 == null)
            return res;
        if(root1.val == root2.val){
            res = IfSame(root1,root2);
        }
        if(!res)
            res = HasSubtree(root1.left,root2);
        if(!res)
            res = HasSubtree(root1.right,root2);
        return res;
    }
    private boolean IfSame(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 != null)
            return false;
        if(root2 == null)
            return true;
        if(root1.val != root2.val)
            return false;
        return IfSame(root1.left,root2.left)&&IfSame(root1.right,root2.right);
    }
}
```

## JZ18、二叉树的镜像

此题为成电21年考研真题

操作给定的二叉树，将其变换为源二叉树的镜像。

> 比如：源二叉树 
>             8
>            /  \
>           6   10
>          / \  / \
>         5  7 9 11
>         镜像二叉树
>             8
>            /  \
>           10   6
>          / \  / \
>         11 9 7  5

**示例：**

> 输入：{8,6,10,5,7,9,11}
>
> 返回值：{8,10,6,11,9,7,5}

**题解：**

典型的递归法，递归反转**当前**节点的左右子树（从叶子到根节点）

```Java
import java.util.*;
/*
 * public class TreeNode {
 *   int val = 0;
 *   TreeNode left = null;
 *   TreeNode right = null;
 *   public TreeNode(int val) {
 *     this.val = val;
 *   }
 * }
 */
public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * @param pRoot TreeNode类 
     * @return TreeNode类
     */
    public TreeNode Mirror (TreeNode pRoot) {
        // write code here
        if(pRoot == null)
            return null;
        pRoot.left = Mirror(pRoot.left);
        pRoot.right = Mirror(pRoot.right);
        TreeNode temp = new TreeNode(-1);
        temp = pRoot.left;
        pRoot.left = pRoot.right;
        pRoot.right = temp;
        return pRoot;
    }
}
```

## JZ21、栈的压入、弹出序列

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）

**示例：**

> 输入：[1,2,3,4,5],[4,3,5,1,2]
>
> 返回值：false

**题解：**

栈的经典运用，输入序列每次入栈后比较栈顶元素是否与压出顺序当前索引元素相等，相等则出栈

具体过程：1入栈，1！=4，2入栈，2！=4，3入栈，3！=4，4入栈，4==4，4出栈，5入栈，5==5，5出栈...

```Java
import java.util.ArrayList;
import java.util.Stack;
public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA.length == 0 || popA.length ==0)
            return false;
        Stack<Integer> s = new Stack<>();
        int index=0;
        for(int i = 0; i<pushA.length; i++){
            s.push(pushA[i]);
            while(!s.empty() && s.peek() == popA[index]){
                s.pop();
                index++;
            }
        }
        return s.empty();
    }
}
```

## JZ22、从上往下打印二叉树（层序遍历）

从上往下打印出二叉树的每个节点，同层节点从左至右打印。

**示例：**

> 输入：{5,4,#,3,#,2,#,1}
>
> 返回值：{5,4,3,2,1}

**题解**：

1、队列求解

new一个queue，根节点入队，每一个结点出队后，left先入，然后right再入

```Java
import java.util.ArrayList;
import java.util.LinkedList;
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode current = null;
        queue.offer(root);//将根节点入队
        while(!queue.isEmpty())
        {
            current = queue.poll();//出队队头元素并访问
            res.add(current.val);
            if(current.left != null)//如果当前节点的左节点不为空入队
            {
                queue.offer(current.left);
            }
            if(current.right != null)//如果当前节点的右节点不为空，把右节点入队
            {
                queue.offer(current.right);
            }
        }
        return res;
    }
}
```

