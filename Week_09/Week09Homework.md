#### [72. 编辑距离](https://leetcode-cn.com/problems/edit-distance/)

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int word1Length = word1.length();
        int word2Length = word2.length();
        int[][] dp = new int[word1Length + 1][word2Length + 1];
        // 初始化第一列
        for (int i = 1; i <= word1Length; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }
        // 初始化第一行
        for (int j = 1; j <= word2Length; j++) {
            dp[0][j] = dp[0][j - 1] + 1;
        }

        for (int i = 1; i <= word1Length; i++) {
            for (int j = 1; j <= word2Length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 分治，两个字母相等
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }

        return dp[word1Length][word2Length];
    }
}
```

#### [8. 字符串转换整数 (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/)


```java
class Solution {
    public int myAtoi(String str) {
        int index = 0, sign = 1, total = 0;
        // empty string
        if (str.length() == 0) {
            return 0;
        }
        // remove spaces，这里节省空间没有使用 trim
        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }
        // handle sign
        if (index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }
        // convert number and avoid overflow
        while (index < str.length()) {
            int dight = str.charAt(index) - '0';
            if (dight < 0 || dight > 9) {
                break;
            }
            // check if total will be overflow after 10 times and add digit
            if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < dight) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            total = 10 * total + dight;
            index++;
        }
        return total * sign;
    }
}
```

#### [387. 字符串中的第一个唯一字符](https://leetcode-cn.com/problems/first-unique-character-in-a-string/)

```java
class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
```

#### [344. 反转字符串](https://leetcode-cn.com/problems/reverse-string/)

```java
class Solution {
    public void reverseString(char[] s) {
        if (s == null) {
            return;
        }
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
}
```

