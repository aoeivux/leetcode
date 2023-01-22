package com.aoeivux;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

class ListNode {
  int val;
  ListNode next;

  ListNode() {}

  ListNode(int x) {
    val = x;
  }
}

public class AppTest {
  int constant = 1000000007;

  @Test
  public void test1() {
    System.out.println(1 % 1.1);
  }

  // 斐波那契数列:循环求余法<<=经过动态规划改进
  public int fib1(int n) {
    int a = 0, b = 1, sum = 0;
    for (int i = 0; i < n; i++) {
      sum = (a + b) % constant;
      a = b;
      b = sum;
    }
    return a;
  }

  // 动态规划: 动态规划的四个步骤
  public int fib(int n) {
    if (n <= 1) return n;
    // 1. 定义状态数组，dp[i] 表示的是数字 i 的斐波那契数
    int[] dp = new int[n + 1];

    // 2. 状态初始化
    dp[0] = 0;
    dp[1] = 1;

    // 3. 状态转移
    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
      // 取模
      dp[i] %= 1000000007;
    }
    // 4. 返回最终需要的状态值
    return dp[n];
  }

  public int numWays(int n) {
    /*
    0  0
    1  1
    2  2
    3  3
    4  5  => 1 1 1 1 || 2 1 1 || 1 2 1 || 1 1 2 || 2 2
    状态转移方程: f(x) = f(x-1) + f(x-2) -> x > 1
    * */
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    for (int i = 0; i <= n; i++) dp[i] = (dp[i - 1] + dp[i - 2]) % constant;
    return dp[n];
  }

  // 动态规划 求前n天的最大股票利益 状态方程：f(x) = max(f(x-1),
  // curentPrice(x)-minPrice(0:x))
  public int maxProfit(int[] prices) {
    int maxProfit = 0;
    int minPrice = Integer.MAX_VALUE;
    for (int price : prices) {
      minPrice = Math.max(minPrice, price);
      maxProfit = Math.max(maxProfit, price - minPrice);
    }
    return maxProfit;
  }

  @Test
  public void test2() {
    int intValue = 12331;
    String valueOf = String.valueOf(intValue);
    char[] charArray = valueOf.toCharArray();
    System.out.println(valueOf);
    System.out.println(charArray);
  }

  public int translateNum(int num) {
    /** 0 a 1 b 2 c ... 25 z */
    String string = String.valueOf(num);
    // 状态定义：设动态规划列表f[n]为以第n个结尾的数字的翻译方案数量
    int n = string.length();
    int[] f = new int[n + 1];
    // 状态初始化
    f[0] = 1;
    f[1] = 1;
    // 状态转移
    int res = f[0];
    for (int i = 2; i <= n; i++) { // 取等号，才能遍历所有情况
      String substring = string.substring(i - 2, i);
      if (substring.compareTo("10") >= 0 && substring.compareTo("25") <= 0) {
        f[i] = f[i - 1] + f[i - 2];
      } else {
        f[i] = f[i - 1];
      }
      res = Math.max(res, f[i]);
    }
    return res;
  }

  // 剑指 Offer 48. 最长不含重复字符的子字符串
  // 滑动窗口双指针法，l为左指针，i为遍历数组的下标，为右指针
  public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    int l = -1, res = 0;
    for (int i = 0; i < s.length(); i++) {
      if (map.containsKey(s.charAt(i))) {
        // 更新左指针
        l = Math.max(l, map.get(s.charAt(i)));
      }
      map.put(s.charAt(i), i);
      res = Math.max(res, i - l);
    }
    return res;
  }

  // 剑指 Offer 18. 删除链表的节点
  public ListNode deleteNode(ListNode head, int val) {
    if (head == null) return null;
    ListNode prev = head;
    ListNode tmp = new ListNode(-1); // 新创建一个单向链表来存储删除节点后的链表
    ListNode res = tmp;
    while (prev != null) {
      if (prev.val != val) {
        res.next = prev;
        res = res.next; // 将当前节点向后移动
      }
      if (prev.next == null) res.next = null; // 考虑当删除节点在单向链表的尾部
      prev = prev.next; // 将当前节点向后移动
    }
    return tmp.next;
  }

  //剑指 Offer 22. 链表中倒数第k个节点

  public ListNode getKthFromEnd(ListNode head, int k) {
    ListNode length = head;
    if (head == null) return null;
    int n = 0;
    //获取链表的长度
    while (length != null) {
      n++;
      length = length.next;
    }
    //原地去除不需要的部分
    while (n-- > k) {
      head = head.next;
    }
    return head;
  }
  //总结反思：不能将 node = node.next;理解为简单的赋值作用，而是node指针移向当前的下一个node节点
}
