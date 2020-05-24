[toc]

## homework_week_01

### 第三课习题

#### 1、[26. 删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)

```java
	// 注意数组是有序的
	public int removeDuplicates(int[] nums) {
    	int cur = 0;
    	for (int number : nums) {
        	if (number > nums[cur]) {
            	nums[++cur] = number;
        	}
    	}
    	return ++cur;
	}
```

#### 2、[189. 旋转数组](https://leetcode-cn.com/problems/rotate-array/)

```java
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        int flag = 0;
        while (++flag <= k) {
            int temp = nums[length - 1];
            // 往后复制一个
            for (int i = length - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }
            nums[0] = temp;
        }
    }
```

#### 3、[21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

```java
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 声明一个虚节点
        ListNode dummyNode = new ListNode(-1);
        ListNode tail = dummyNode;
        while(l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = l1 == null ? l2 : l1;
        return dummyNode.next;
    }
```

#### 4、[88. 合并两个有序数组](https://leetcode-cn.com/problems/merge-sorted-array/)

```java
	// 1、暴力，加到nums1末尾，Arrays.sort()	

	// 2、跟合并两个有序链表类似，这里也用两个指针分别指向数组1的开头，和数组2的开头。
	// 跟链表合并不同，如果往数组中插入一个元素，为了保证整体的顺序性，
	// 需要挪动前后的元素，所以需要再新建一个数组。
	public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0, k = 0;
        int[] array = new int[m + n];
        while (i < m || j < n) {
            if (i == m){
                array[k++] = nums2[j++];
            } else if (j == n) {
                array[k++] = nums1[i++];
            } else if (nums1[i] <= nums2[j]) {
                array[k++] = nums1[i++];
            } else {
                array[k++] = nums2[j++];
            }
        }
        int a = 0;
        for (int number : array) {
            nums1[a++] = number;
        }
    }
```

### TODO

https://leetcode-cn.com/problems/two-sum/
https://leetcode-cn.com/problems/move-zeroes/
https://leetcode-cn.com/problems/plus-one/



### 第三课习题

#### 1、[641. 设计循环双端队列](https://leetcode-cn.com/problems/design-circular-deque/)

```java
	// 1、投机做法
	private Deque<Integer> deque;

    private Integer capacity;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        capacity = k;
        deque = new ArrayDeque<>(k);
    }
	 
	// ...

	// 2、数组做法
private int[] elements;
private int size;

public MyCircularDeque(int k) {
    elements = new int[k];
}

public boolean insertFront(int value) {
    if(isFull()){
        return false;
    }
    for(int i=size-1; i>=0; i--){
        elements[i+1] = elements[i];
    }
    elements[0] = value;
    size++;
    return true;
}

public boolean insertLast(int value) {
    if(isFull()){
        return false;
    }
    elements[size] = value;
    size++;
    return true;
}

public boolean deleteFront() {
    if(isEmpty()){
        return false;
    }
    elements[0] = 0;
    for(int i=0; i<size-1; i++){
        elements[i]=elements[i+1];
    }
    size--;
    return true;
}

public boolean deleteLast() {
    if(isEmpty()){
        return false;
    }
    elements[size-1] = 0;
    size--;
    return true;
}

public int getFront() {
    return size == 0 ? -1 : elements[0];
}

public int getRear() {
    return size == 0 ? -1 : elements[size-1];
}

public boolean isEmpty() {
    return size == 0;
}

public boolean isFull() {
    return size == elements.length;
}
```

#### 2、[42. 接雨水](https://leetcode-cn.com/problems/trapping-rain-water/)

```java
	// 1、双指针

	// 2、单调栈
    public int trap(int[] height) {
        if (height == null) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int cur = stack.pop();
                while (!stack.isEmpty() && height[stack.peek()] == height[cur]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    int stackTop = stack.peek();
                    ans += (Math.min(height[stackTop], height[i]) - height[cur]) *
                            (i - stackTop - 1);
                }
            }
            stack.add(i);
        }
        return ans;
    }
```

