学习笔记



### DP三部曲

1、分治（子问题）

2、状态空间（状态数组）定义

a[i] 0..i天，且nums[i]必偷的最大值

3、DP方程

a[i] = max(a[i - 1], a[i - 2] + nums[i])



### demo

#### [198. 打家劫舍](https://leetcode-cn.com/problems/house-robber/)

```java
	// 二维数组DP
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0; // 0 代表不偷
        dp[0][1] = nums[0]; // 偷取第一个房子

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    // 一维数组DP
    public int rob_2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int res = Math.max(dp[0], dp[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            res = Math.max(res, dp[i]);
        }

        return res;
    }

    // 最终演进成变量递推
    public int rob_3(int[] nums) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }
```



关于DP数组（状态空间or状态数组的定义）大小如何确定？

需要多做题，经验使然。