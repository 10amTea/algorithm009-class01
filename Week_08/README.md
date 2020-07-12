学习笔记

## 位运算

N皇后的常规解法

```java
// 全排列
class Solution {
    public List<List<String>> solveNQueens(int n) {

        List<Integer> nums = new ArrayList();
        for(int i = 0; i<n; i++) nums.add(i);

        // 用一个一维数列来表示一个棋盘的摆放
        // 如【0,1,2,3】表示棋子放在第一行第一列，第二行第二列。。。。
        List<List<Integer>> col = new ArrayList();
        f(col, 0, nums); 
        // 此番函数结束已得到答案，下面就是转换一下答案形式

        List<List<String>> ans = new ArrayList();
        for(int i = 0; i<col.size(); i++){
            List<String> item = new ArrayList();
            for(int j = 0; j<n; j++){
                item.add(genStr((int)col.get(i).get(j), n));
            }
            ans.add(item);
        }
        return ans;

    }

    // 回溯的主要函数，关键地方就在这儿
    void f(List collection, int pos, List nums){
        if(pos == nums.size()-1 && isLegal(nums))
            collection.add(new ArrayList(nums));
        //轮流坐位置
        for(int i = pos; i<nums.size(); i++){
            Collections.swap(nums, pos, i);
            f(collection, pos+1, nums);
            Collections.swap(nums, pos, i);
        }
    }

    //判断一棋盘时候是否符合要求，其实就是不要出现斜线吃子的情况
    boolean isLegal(List<Integer> nums){
        for(int i = 0; i<nums.size(); i++){
            for(int j = i+1; j<nums.size(); j++){
                if(Math.abs(nums.get(i) - nums.get(j)) == j-i)
                    return false;
            }
        }
        return true;
    }

    //把数字转换成题目要求的字符串形式
    String genStr(int i, int n){
        char[] c = new char[n];
        Arrays.fill(c, '.');
        c[i] = 'Q';
        return new String(c);
    }


}

// 作者：acnesu
// 链接：https://leetcode-cn.com/problems/n-queens/solution/ke-yi-xian-zuo-yi-xia-quan-pai-lie-by-acnesu-2/
```



## 布隆过滤器

过滤存在误判，但是能够极大的优化查询的时间。

布隆过滤器的实战训练，见到过解题中，出现该方法的题。（TODO）



### 排序

1、常规算法，选择、插入、冒泡

2、高级，快速和归并，快速是先左右数组调配出来，基于左右排序；而归并需要先排序左右，然后进行两个有序数组的合并。



