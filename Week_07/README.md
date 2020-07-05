学习笔记



## Trie (前缀树)

### Trie 树的结点结构
Trie 树是一个有根的树，其结点具有以下字段：

+ 最多 R 个指向子结点的链接，其中每个链接对应字母表数据集中的一个字母。
+ 布尔字段，以指定节点是对应键的结尾还是只是键前缀。

### 向 Trie 树中插入键

我们通过搜索 Trie 树来插入一个键。我们从根开始搜索它对应于第一个键字符的链接。有两种情况：

+ 链接存在。沿着链接移动到树的下一个子层。算法继续搜索下一个键字符。
+ 链接不存在。创建一个新的节点，并将它与父节点的链接相连，该链接与当前的键字符相匹配。

### 在 Trie 树中查找键
每个键在 trie 中表示为从根到内部节点或叶的路径。我们用第一个键字符从根开始，检查当前节点中与键字符对应的链接。有两种情况：

+ 存在链接。我们移动到该链接后面路径中的下一个节点，并继续搜索下一个键字符。
+ 不存在链接。若已无键字符，且当前结点标记为 isEnd，则返回 true。否则有两种可能，均返回 false :
  + 还有键字符剩余，但无法跟随 Trie 树的键路径，找不到键。
  + 没有键字符剩余，但当前结点没有标记为 isEnd。也就是说，待查找键只是Trie树中另一个键的前缀。

```java
// 前缀树模板
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

## 并查集

一种比较固化的数据结构，特定场景使用，朋友圈等。

```java
class UnionFind { 
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
		count = n; 
		parent = new int[n]; 
		for (int i = 0; i < n; i++) { 
			parent[i] = i;
		}
	} 
	public int find(int p) { 
		while (p != parent[p]) { 
			parent[p] = parent[parent[p]]; 
			p = parent[p]; 
		}
		return p; 
	}
	public void union(int p, int q) { 
		int rootP = find(p); 
		int rootQ = find(q); 
		if (rootP == rootQ) return; 
		parent[rootP] = rootQ; 
		count--;
	}
}
```

