package org.sun.algorithm.week04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Week04 {
    // homework
    // 860. 柠檬水找零 https://leetcode-cn.com/problems/lemonade-change/description/
    // 思路1：贪心，暴力的思路不怎么好理解
    // https://leetcode-cn.com/problems/lemonade-change/solution/ning-meng-shui-zhao-ling-javajian-ji-ti-jie-bao-li/
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            switch (bill) {
                case 5:
                    five++;
                    break;
                case 10:
                    five--; // 10块也需要考虑找零
                    ten++;
                    break;
                case 20:
                    if (ten > 0) {
                        ten--;
                        five--;
                    } else {
                        five -= 3;
                    }
                    break;
                default:
                    break;
            }
            if (five < 0) {
                return false;
            }
        }
        return true;
    }

    // 122. 买卖股票的最佳时机 II https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/
    // 思路1：出现下一天比后一天价格上升就可以购买，购买次数不限
    // 官方解答中称为峰谷法，其实就是贪心；相对暴力搜索比较难想
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                res += prices[i + 1] - prices[i];
            }
        }
        return res;
    }
    // 暴力搜索（回溯搜索） 以下题解涉及，超时
    // https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/tan-xin-suan-fa-by-liweiwei1419-2/

    // 455. 分发饼干 https://leetcode-cn.com/problems/assign-cookies/description/
    // 思路1：暴力，小饼干的值需要大于等于孩子的胃口，其实不是暴力，还是用了贪心的思想
    // O(n)
    // https://leetcode-cn.com/problems/assign-cookies/solution/tan-xin-jie-fa-by-cyc2018/
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        for (int i = 0, j = 0; i < s.length && j < g.length; ) {
            if (s[i] >= g[j]) {
                i++;
                j++;
                res++;
            } else {
                i++;
            }
        }
        return res;
    }

    // 874. 模拟行走机器人 https://leetcode-cn.com/problems/walking-robot-simulation/
    // 条件，求取的是过程中最大的欧式距离平方
    public int robotSim(int[] commands, int[][] obstacles) {
        // 将obstacles放到set集合中;
        Set<String> set = new HashSet<>();
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] + "," + obstacle[1]);
        }
        int maxDis = 0;
        // now = 0, {0, 1} 向北走
        // now = 1, {1, 0} 向东走
        // ...
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int now = 0, x1, y1;
        int[] position = {0, 0};
        for (int command : commands) {
            if (command == -1) { // 向右转 90
                now++;
                if (now > 3) {
                    now = 0;
                }
            } else if (command == -2) { // 向左转 90
                now--;
                if (now < 0) {
                    now = 3;
                }
            } else {
                for (int i = 0; i < command; i++) { // 行走的步数
                    x1 = position[0] + direction[now][0];
                    y1 = position[1] + direction[now][1];
                    if (!set.contains(x1 + "," + y1)) {
                        position[0] = x1;
                        position[1] = y1;
                        maxDis = Math.max((int) (Math.pow(position[0], 2) + Math.pow(position[1], 2)), maxDis);
                    }
                }
            }
        }
        return maxDis;
    }

    // 127. 单词接龙 https://leetcode-cn.com/problems/word-ladder/
    // 思路1：图的广度优先遍历，visited 一定要在访问的时候就添加，否则成环会死循环
    // https://leetcode-cn.com/problems/word-ladder/solution/yan-du-you-xian-bian-li-shuang-xiang-yan-du-you-2/
    // TODO 队列出栈的时候标记访问程序同样也没有死循环？ 之前遇到过 BFS 这样就失败了
    // 可以这样记忆：反正入队的元素肯定会被访问到，所以讲道理马上标记为「已经访问」是合理的。
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        wordSet.remove(beginWord);

        // 图的广度优先遍历，必须使用的队列和表示是否访问过的 visited （数组，哈希表）
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int wordLen = beginWord.length();
        // 包含起点，因此初始化的时候步数为 1
        int step = 1; // 返回的结果初始化
        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                // 依次遍历当前队列中的单词
                String word = queue.poll();
                char[] charArray = word.toCharArray(); // 队列中的单词

                // 修改每一个字符
                for (int j = 0; j < wordLen; j++) {
                    // 一轮以后应该重置，否则结果不正确
                    char originChar = charArray[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == originChar) {
                            continue; // 如果是原字母就没有做变换，不符合变换
                        }

                        charArray[j] = k;
                        String nextWord = String.valueOf(charArray);

                        if (wordSet.contains(nextWord)) {
                            if (nextWord.equals(endWord)) {
                                visited.add(nextWord); // 标记已经访问过，但没有意义程序已经return
                                return step + 1;
                            }

                            if (!visited.contains(nextWord)) {
                                queue.add(nextWord);
                                visited.add(nextWord); // 注意：添加到队列以后，必须马上标记为已经访问
                            }
                        }
                    }
                    // 恢复变换的字母位数查找下一个符合一次变换的位置
                    charArray[j] = originChar;
                }
            }
            step++;
        }
        return 0;
    }

    // 200. 岛屿数量 https://leetcode-cn.com/problems/number-of-islands/
    // 思路较多，DFS、BFS、并查集都行
    private int n;
    private int m;

    public int numIslands(char[][] grid) {
        n = grid.length;
        if (n == 0) {
            return 0;
        }
        m = grid[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public static void main_1(String[] args) {
        new Week04().maxProfit(new int[]{7, 1, 5, 3, 6, 4});
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        String[] wordListArray = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        Collections.addAll(wordList, wordListArray);
        Week04 solution = new Week04();
        int res = solution.ladderLength(beginWord, endWord, wordList);
        System.out.println(res);
    }
}
