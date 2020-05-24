[toc]

## week01学习笔记

### 第三节

#### 时间复杂度

##### 常见的时间复杂度量级

![引用](https://pic1.zhimg.com/80/v2-8c2fee5f94f72835e60b8e7b85753079_720w.jpg)

常数级 O(1)

对数级 O(logn)

线性级 O(n)

线性对数级 O(nlogn)

平方级 O(n^2)

k次方级 O(n^k)

指数级 O(2^n)

阶乘级 O(n!)

##### 本节所涉及的数据结构的时间复杂度

|      | insert  | delete  | lookup  | prepend | append  |
| ---- | ------- | ------- | ------- | ------- | ------- |
| 数组 | O(n)    | O(n)    | O(1)    | NA      | NA      |
| 链表 | O(1)    | O(1)    | O(n)    | O(1)    | O(1)    |
| 跳表 | O(logn) | O(logn) | O(logn) | O(logn) | O(logn) |

其中跳表的空间复杂度还是O(n)。

### 第四节

#### 代码改写

```java
	Deque<String> deque = new LinkedList<>();
    deque.addFirst("a"); // 这里存放元素与使用 push 功能上就和栈一致
    deque.addFirst("b");
    deque.addFirst("c");
    System.out.println(deque);

    String str = deque.element(); // peekFirst() 查询栈顶元素
    System.out.println(str);
    System.out.println(deque);

    while (!deque.isEmpty()) {
      System.out.println(deque.removeFirst()); // pop 栈顶元素
    }
    System.out.println(deque);
```

#### 源码分析

##### Queue源码分析

Queue是一个接口，它定义了六个方法，从操作分为三类。

###### UML类图

![QueueUML](https://github.com/10amTea/algorithm009-class01/blob/master/Week_01/picture/PriorityQueueUML.png)

######  Insert（tail）

boolean add(E e);

boolean offer(E e);

区别：当队列空间已满无法入队时，add()会抛出异常;而offer()会返回false。

###### Remove

E remove(); // the head of this queue

E poll(); // the head of this queue

区别：当队列为空时，remove()会抛出异常，而poll()会返回null。

###### Examine

E element(); // the head of this queue

E peek(); // the head of this queue

区别：当队列为空时，element()会抛出异常，而peek()会返回null。

##### PriorityQueue源码分析

PriorityQueue从字面上就很好理解，它是一个优先级队列，它通过存入的元素实现Comparable接口或者Comparator接口。

通过构造器中匿名类的形式实现Comparator接口，通过Comparator自定义排序算法（元素就不需要实现Comparable接口）；另外，假设元素已经实现了Comparable接口，你还是可以通过Comparator自定义排序。一般优先队列的默认为natural ordering，即1 2 3 4 5 6。

下面简要叙述一下比较常用的几个field和method。
PriorityQueue不支持null；
PriorityQueue不是线程安全的，多线程可以使用java.util.concurrent.PriorityBlockingQueue；
使用iterator()遍历时，不保证输出的序列是有序的，其实遍历的是存储数组。

###### UML类图

![PriorityQueueUML](https://github.com/10amTea/algorithm009-class01/blob/master/Week_01/picture/PriorityQueueUML.png)

###### field

// 数组的默认初始容量
private static final int DEFAULT_INITIAL_CAPACITY = 11;

// 队列中元素的比较器
private final Comparator<? super E> comparator;

###### method

// 构造器 3：指定比较器的构造器
public PriorityQueue(Comparator<? super E> comparator) {
	this(DEFAULT_INITIAL_CAPACITY, comparator);
}



// 扩容方法
// minCapacity表示需要的最小容量
private void grow(int minCapacity) {
	int oldCapacity = queue.length; // 获取当前容量
	// Double size if small; else grow by 50%
	// 如果旧容量小于64，则增加旧容量+2的大小
	// 如果旧容量大于等于64，则增加旧容量的一半大小
	int newCapacity = oldCapacity + ((oldCapacity < 64) ? (oldCapacity + 2) : 
											(oldCapacity >> 1));
	// overflow-conscious code
	if (newCapacity - MAX_ARRAY_SIZE > 0) // 这里处理大容量
		newCapacity = hugeCapacity(minCapacity);
	queue = Arrays.copyOf(queue, newCapacity); // 复制已存储的数据
}

### TODO

1、数组双指针的时候边界值的控制，需要非常的清晰。

2、单调栈

3、滑动窗口

+ 使用队列（双端）

### 参考资料

https://www.zhihu.com/question/20196775/answer/693388880

https://zhuanlan.zhihu.com/p/104147657

https://zhuanlan.zhihu.com/p/105102835

https://design-patterns.readthedocs.io/zh_CN/latest/read_uml.html

https://www.liaoxuefeng.com/wiki/1252599548343744/1265120632401152

https://stackoverflow.com/questions/5167928/what-is-natural-ordering-when-we-talk-about-sorting

https://www.jianshu.com/p/dc0eeb82e994