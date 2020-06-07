学习笔记



### 回溯算法的理解

“回溯”指的是“状态重置”，可以理解为“回到过去”、“恢复现场”，是在编码的过程中，是为了节约空间而使用的一种技巧。而回溯其实是“深度优先遍历”特有的一种现象。之所以是“深度优先遍历”，是因为我们要解决的问题通常是在一棵树上完成的，在这棵树上搜索需要的答案，一般使用深度优先遍历。



### 模板的默写

#### 递归（python）

```python
def recursion(level, param1):
    # recursion terminator
    if level > MAX
    	process_result
      	return
    
    # process logic in current level
    process(level, data)
    
    # drill down
    self.recursion(level + 1, p1)
    
    # reverse the current level status if needed
```

递归的每一步实现并不是分割的开来的。



