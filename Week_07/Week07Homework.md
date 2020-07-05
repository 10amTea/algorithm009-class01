#### [70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)


```java
class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] ans = new int[n + 1];
        ans[1] = 1;
        ans[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            ans[i] = ans[i - 2] + ans[i - 1];
        }
        return ans[n];
    }
}
```

#### [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)

```java
public class Trie {
    private boolean is_string=false;
    private Trie next[]=new Trie[26];

    public Trie(){}

    public void insert(String word){//插入单词
        Trie root=this;
        char w[]=word.toCharArray();
        for(int i=0;i<w.length;++i){
            if(root.next[w[i]-'a']==null)root.next[w[i]-'a']=new Trie();
            root=root.next[w[i]-'a'];
        }
        root.is_string=true;
    }

    public boolean search(String word){//查找单词
        Trie root=this;
        char w[]=word.toCharArray();
        for(int i=0;i<w.length;++i){
            if(root.next[w[i]-'a']==null)return false;
            root=root.next[w[i]-'a'];
        }
        return root.is_string;
    }
    
    public boolean startsWith(String prefix){//查找前缀
        Trie root=this;
        char p[]=prefix.toCharArray();
        for(int i=0;i<p.length;++i){
            if(root.next[p[i]-'a']==null)return false;
            root=root.next[p[i]-'a'];
        }
        return true;
    }
}
```

