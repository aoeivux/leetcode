package com.aoeivux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.print.attribute.Size2DSyntax;

import org.junit.Test;

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

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
		if (n <= 1)
			return n;
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
		 * 0 0
		 * 1 1
		 * 2 2
		 * 3 3
		 * 4 5 => 1 1 1 1 || 2 1 1 || 1 2 1 || 1 1 2 || 2 2
		 * 状态转移方程: f(x) = f(x-1) + f(x-2) -> x > 1
		 */
		int[] dp = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 0; i <= n; i++)
			dp[i] = (dp[i - 1] + dp[i - 2]) % constant;
		return dp[n];
	}

	// 动态规划 求前n天的最大股票利益 状态方程：f(x) = max(f(x-1),
	// curentPrice(x)-minPrice(0:x))
	// 贪心法
	public int maxProfit(int[] prices) {
		int maxProfit = 0;
		int minPrice = Integer.MAX_VALUE;
		for (int price : prices) {
			minPrice = Math.min(minPrice, price);
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
		if (head == null)
			return null;
		ListNode prev = head;
		ListNode tmp = new ListNode(-1); // 新创建一个单向链表来存储删除节点后的链表
		ListNode res = tmp;
		while (prev != null) {
			if (prev.val != val) {
				res.next = prev;
				res = res.next; // 将当前节点向后移动
			}
			if (prev.next == null)
				res.next = null; // 考虑当删除节点在单向链表的尾部
			prev = prev.next; // 将当前节点向后移动
		}
		return tmp.next;
	}

	// 剑指 Offer 22. 链表中倒数第k个节点

	public ListNode getKthFromEnd(ListNode head, int k) {
		ListNode length = head;
		if (head == null)
			return null;
		int n = 0;
		// 获取链表的长度
		while (length != null) {
			n++;
			length = length.next;
		}
		// 原地去除不需要的部分
		while (n-- > k) {
			head = head.next;
		}
		return head;
	}
	// 总结反思：不能将 node =
	// node.next;理解为简单的赋值作用，而是node指针移向当前的下一个node节点

	// 面试题13. 机器人的运动范围
	// DFS+递归版本
	// DFS将一个方向走到底后，再递归回到上一个再判断
	public int movingCount(int m, int n, int k) {
		boolean[][] vis = new boolean[m][n];
		return dfs_machine(vis, m, n, k, 0, 0);
	}

	public int dfs_machine(boolean[][] visited, int m, int n, int k, int i, int j) {
		if (i >= m || j >= n || visited[i][j] == true || get_sum(i) + get_sum(j) > k)
			return 0;
		visited[i][j] = true;
		// 向下、向右递归
		return 1 + dfs_machine(visited, m, n, k, i + 1, j) + dfs_machine(visited, m, n, k, i, j + 1);
	}

	public int get_sum(int x) {
		int res = 0;
		while (x != 0) {
			res += x % 10;
			x /= 10;
		}
		return res;
	}

	// BFS，常使用队列实现,横向平推的方式遍历
	public int movingCount2(int m, int n, int k) {
		boolean[][] visited = new boolean[m][n];
		int res = 0;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] { 0, 0 });
		while (queue.size() > 0) {
			int[] x = queue.poll();
			int i = x[0], j = x[1];
			if (i >= m || j >= n || k < get_sum1(i) + get_sum1(j) || visited[i][j])
				continue;
			visited[i][j] = true;
			res++;
			queue.add(new int[] { i + 1, j });
			queue.add(new int[] { i, j + 1 });
		}
		return res;
	}

	public int get_sum1(int x) {
		int res = 0;
		while (x != 0) {
			res += x % 10;
			x /= 10;
		}
		return res;
	}

	@Test
	public void test3() {
		Integer[] nums = new Integer[] { 10, 2 };
		Arrays.sort(nums);
		Arrays.sort(nums, (x, y) -> {
			System.out.println(x);
			System.out.println(y);
			return x - y;
		});
		for (Integer var : nums) {
			System.out.print(var + " ");
		}
		System.out.println();
	}

	@Test
	public void test4() {
		/**
		 * 基本数据类型和变量在栈上面
		 * 对象在对上面,
		 * 数据类型：1、基本数据类型 2、引用数据类型(class、[]、interface)
		 */

		// 验证java是值传递，当向方法中传递一个对象时, 将指向该对象的引用进行复制
		// 值传递、引用传递：https://www.bilibili.com/video/BV1q34y1s72a/?spm_id_from=333.1007.top_right_bar_window_history.content.click
		String[] s = { "asdad", "adsad", "2132313" };
		valueTo(s, 1, 2);
		for (String string : s) {
			System.out.println(string);
		}
	}

	void valueTo(String[] strs, int i, int j) {
		strs[i] = "1";
		strs[j] = "2";
	}

	// 面试题45. 把数组排成最小的数
	public String minNumber(int[] nums) {
		String[] strs = new String[nums.length];
		for (int i = 0; i < nums.length; i++)
			strs[i] = String.valueOf(nums[i]);
		quickSort(strs, 0, strs.length - 1);

		StringBuilder res = new StringBuilder();
		for (String s : strs)
			res.append(s);
		return res.toString();
	}

	public void quickSort(String[] strs, int l, int h) {
		if (l < h) {
			int m = getMid(strs, l, h);
			quickSort(strs, l, m - 1);
			quickSort(strs, m + 1, h);
		}
	}

	int getMid(String[] strs, int l, int h) {
		// 数组的一个数是基准数
		String temp = strs[l];
		while (l < h) {
			// 从右向左找到小于基准数的数
			while (l < h && (strs[h] + temp).compareTo(temp + strs[h]) >= 0)
				h--;
			strs[l] = strs[h];
			// 从左向右找到大于基准数的数
			while (l < h && (strs[l] + temp).compareTo(temp + strs[l]) <= 0)
				l++;
			strs[h] = strs[l];
			/**
			 * 把比基准小的数移到低端
			 * 把比基准大的数移到高端
			 */
		}
		strs[l] = temp;
		return l;
	}

	//
	public String longestPalindrome(String s) {
		String ans = "";
		for (int i = 0; i < s.length(); i++) {
			// 回文串为奇数
			int l = i - 1, r = i + 1;
			String sub = getString(s, l, r);
			if (sub.length() > ans.length())
				ans = sub;

			// 回文串为偶数
			l = i - 1;
			r = i;
			String sub1 = getString(s, l, r);
			if (sub1.length() > ans.length())
				ans = sub1;
		}
		return ans;
	}

	// s = "babad" => bab
	// s = ""
	String getString(String s, int l, int r) {
		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return s.substring(l + 1, r);
	}
	//

	// 最长公共前缀和
	public String longestCommonPrefix(String[] ss) {
		String ans = "";
		if (ss.length == 0)
			return ans;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			String s = ss[0];
			if (i >= s.length())
				return ans;
			char c = ss[0].charAt(i);
			for (String item : ss) {
				if (i >= item.length() || item.charAt(i) != c)
					return ans;
			}
			ans += String.valueOf(c);
		}
		return ans;
	}

	public String longestCommonPrefix1(String[] s) {
		if (s.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s[0].length(); i++) {
			for (String v : s) {
				if (i >= v.length() || v.charAt(i) != s[0].charAt(i))
					return sb.toString();
			}
			sb.append(s[0].charAt(i));
		}
		return sb.toString();
	}
	//

	@Test
	public void test5() {
		char[] ch = new char[] { '1', '2', '3' };
		String[] sre = { "asd", "chg", "sds" };
		String res = "asdasd";
		char[] res1;
		char[] charArray = res.toCharArray();
		System.out.println(charArray);
		System.out.println(charArray instanceof char[]);
		System.out.println("=============");
		for (int i = 0; i < charArray.length; i++) {
			System.out.print(charArray[i]);
		}
		System.out.println("======");
		System.out.println("ch[0]*ch[1]" + "=>" + ch[0] * ch[1]);
		System.out.println(ch[0] - '0');
	}

	/**
	 * 计算形式
	 * num1
	 * x num2
	 * ------
	 * result
	 */

	//
	// 字符串相乘:两个长度分别为 n 和 m 的数相乘，长度不会超过 n + m
	public String multiply(String n1, String n2) {
		int m = n1.length(), n = n2.length();
		int[] res = new int[m + n];
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int a = n1.charAt(i) - '0';
				int b = n1.charAt(j) - '0';
				int r = a * b;
				r += res[i + j + 1];
				res[i + j + 1] = r % 10;
				res[i + j] += r / 10;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m + n; i++) {
			if (sb.length() == 0 && res[i] == 0)
				continue;
			sb.append(res[i]);
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}

	public String multiply1(String n1, String n2) {
		int n = n1.length(), m = n2.length();
		int[] res = new int[n + m];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				int a = n1.charAt(i) - '0';
				int b = n2.charAt(j) - '0';
				int r = a * b;
				r += res[i + j + 1];
				res[i + j + 1] = r % 10;
				res[i + j] += r / 10;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n + m; i++) {
			if (sb.length() == 0 && res[i] == 0)
				continue;
			sb.append(res[i]);
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}
	//

	@Test
	public void test6() {
		StringBuilder sb = new StringBuilder();
		sb.append("sb");
		for (int i = 0; i < 5; i++) {
			sb.append(i);
		}
		System.out.println(sb);
	}

	@Test
	public void test7() {
		String string = "-123456";
		StringBuilder sb = new StringBuilder("-123456");
		long parseLong = Long.parseLong(sb.toString());

		System.out.println(parseLong);
		System.out.println("=========================");
		System.out.println(sb);
		sb.reverse();
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);

		// string number to int
		int parseInt = Integer.parseInt(string);
		Integer valueOf = Integer.valueOf(string);
		int intValue = valueOf.intValue();

		Integer.valueOf(string);

		System.out.println("=====");
		System.out.println(parseInt);
		System.out.println(intValue);
		System.out.println(Math.pow(2, 31) - 1);
		// Random(long seed);设置种子后每次随机相同
		Random r = new Random();
		System.out.println("===================");
		System.out.println(r.nextInt(10) - 5);
		List<Integer> l = new ArrayList<>();
		l.add(1);
		System.out.println(l.size());
		l.get(l.size() - 1);

	}

	// 整数翻转
	public int reverse(int x) {
		int res = 0;
		while (x != 0) {
			// 每次取末尾数字
			int tmp = x % 10;
			// 判断是否 大于 最大32位整数
			if (res > 214748364 || (res == 214748364 && tmp > 7)) {
				return 0;
			}
			// 判断是否 小于 最小32位整数
			if (res < -214748364 || (res == -214748364 && tmp < -8)) {
				return 0;
			}
			res = res * 10 + tmp;
			x /= 10;
		}
		return res;
	}
	//

	@Test
	public void test8() { // 双指针从左向右移动案例
		String s = "abcabcbb";
		// l and r point
		int l = 0, max = 0;
		Map<Character, Integer> m = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (m.containsKey(s.charAt(i))) {
				l = Math.max(l, m.get(s.charAt(i)) + 1);
			}
			m.put(s.charAt(i), i);
			max = Math.max(max, i - l + 1); // 这里+1因为数组下标从0开始,这是则是表示长度
		}
	}

	@Test
	public void test9() {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(new int[] { 2, 3, 5, 67, 1 });
		List<List<Integer>> res = null;
	}

	// threeSum
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);// 原地排序
		int n = nums.length;
		List<List<Integer>> ans = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			int j = i + 1, k = n - 1;
			while (j < k) {
				while (j > i + 1 && j < n && nums[j] == nums[j - 1])
					j++;
				if (j >= k)
					break;
				int sum = nums[i] + nums[j] + nums[k];
				if (sum == 0) {
					ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
					j++;
				} else if (sum > 0) {
					k--;
				} else if (sum < 0) {
					j++;
				}
			}
		}
		return ans;
	}

	public int threeSumClosest(int[] nums, int t) {
		Arrays.sort(nums);
		int ans = nums[0] + nums[1] + nums[2];
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			int j = i + 1, k = n - 1;
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				if (Math.abs(sum - t) < Math.abs(ans - t))
					ans = sum;
				if (ans == t) {
					return t;
				} else if (sum > t) {
					k--;
				} else if (sum < t) {
					j++;
				}
			}
		}
		return ans;
	}

	public List<List<Integer>> fourSum(int[] nums, int t) {
		Arrays.sort(nums);
		int n = nums.length;
		List<List<Integer>> ans = new ArrayList<>();
		for (int i = 0; i < n; i++) { // 确定第一个数
			if (i > 0 && nums[i] == nums[i - 1])
				continue; // 对第一个数进行去重（相同的数只取第一个）
			for (int j = i + 1; j < n; j++) { // 确定第二个数
				if (j > i + 1 && nums[j] == nums[j - 1])
					continue; // 对第二个数进行去重（相同的数只取第一个）
				// 确定第三个数和第四个数
				int k = j + 1, p = n - 1;
				while (k < p) {

					// 对第三个数进行去重（相同的数只取第一个）
					while (k > j + 1 && k < n && nums[k] == nums[k - 1])
						k++;
					// 如果 k 跳过相同元素之后的位置超过了 p，本次循环结束
					if (k >= p)
						break;
					long sum = (long) nums[i] + (long) nums[j] + (long) nums[k] + (long) nums[p];
					if (sum == t) {
						ans.add(Arrays.asList(nums[i], nums[j], nums[k], nums[p]));
						k++;
					} else if (sum > t) {
						p--;
					} else if (sum < t) {
						k++;
					}
				}
			}
		}
		return ans;
	}

	@Test
	public void test10() {
		long[] asd = new long[] { 1, 2, 3, 5 };
		long a = 100000000;
		System.out.println(a);
	}

	public List<List<Integer>> fourSumTest(int[] nums, int t) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;
			for (int j = i + 1; j < n; j++) {
				if (j > j + 1 && nums[j] == nums[j - 1])
					continue;
				int l = j + 1, r = n - 1;
				while (l < r) {
					while (l > j + 1 && l < n && nums[l] == nums[l - 1])
						l++;
					if (l >= r)
						break;
					long sum = (long) nums[i] + (long) nums[j] + (long) nums[l] + (long) nums[r];
					if (sum == t) {
						Arrays.asList(nums[i], nums[j], nums[l], nums[r]);
						l++;
					} else if (sum > t)
						r--;
					else if (sum < t)
						l++;
				}
				;
			}
		}
		return res;
	}

	// lengthOfLongestSubstringTest
	public int lengthOfLongestSubstringTest(String s) {
		Map<Character, Integer> m = new HashMap<>();
		int n = s.length();
		int max = 0, l = 0;
		for (int i = 0; i < n; i++) {
			if (m.containsKey(s.charAt(i))) {
				l = Math.max(l, m.get(s.charAt(i)) + 1);
			}

			m.put(s.charAt(i), i);
			max = Math.max(max, i - l + 1);
		}
		return max;
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head.next == null)
			return null;
		ListNode slow = head, fast = head;
		while (n-- > 0)
			fast = fast.next;
		if (fast == null) {
			head = slow.next;
		} else {
			while (fast.next != null) {
				slow = slow.next;
				fast = fast.next;
			}
			slow.next = slow.next.next;
		}
		return head;
	}

	// 这个双指针，一开始看怎么逻辑不对，仔细研究，精髓在swap(nums[i--], nums[j--]);的i--巧妙地检查了交换后的 nums[i]
	// 是否还为 val
	// i-- 在执行完swap才会执行i = i - 1,之后第一层循环结束之后又会i = i + 1。
	public int removeElement(int[] nums, int val) {
		int j = nums.length - 1;
		for (int i = 0; i <= j; i++) {
			if (nums[i] == val) {
				swap(nums, i--, j--);
			}
		}
		return j + 1;
	}

	void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public int removeDuplicates(int[] nums) {
		int n = nums.length;
		int j = 0;
		for (int i = 0; i < n; i++) {
			if (nums[i] == nums[j])
				continue;
			nums[++j] = nums[i];
		}
		return j + 1;
	}

	@Test
	public void testG1() {

	}

	public int maxSubArray(int[] nums) {
		int len = nums.length;
		// dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
		// 1.状态定义
		int[] dp = new int[len];

		// 2.初始值
		dp[0] = nums[0];

		for (int i = 1; i < len; i++) {
			if (dp[i - 1] > 0) {
				dp[i] = dp[i - 1] + nums[i];
			} else {
				dp[i] = nums[i];
			}
		}

		// 也可以在上面遍历的同时求出 res 的最大值，这里我们为了语义清晰分开写，大家可以自行选择
		int res = dp[0];
		for (int i = 1; i < len; i++) {
			res = Math.max(res, dp[i]);
		}
		return res;
	}

	public int testMaxSubArray(int[] nums) {
		int length = nums.length;

		// 定义状态
		int[] dp = new int[length];

		// 定义初始值
		dp[0] = nums[0];

		// 状态转移方程
		for (int i = 1; i < dp.length; i++) {
			if (dp[i - 1] > 0) {
				dp[i] = dp[i - 1] + nums[i];
			} else {
				dp[i] = nums[i];
			}
		}

		int res = dp[0];
		for (int i = 1; i < length; i++) {
			res = Math.max(res, dp[i]);
		}

		return res;

	}

	@Test
	public void test11() {
		HashMap<Integer, Character> map = new HashMap<>();
		LinkedList<Object> l = new LinkedList<>();
		// l.size()
		// l.get
		map.put(1, 'a');
		map.put(2, 'b');
		map.put(1, 'c');
		System.out.println(map);
	}

	//
	public int maxProfit1(int[] prices) {
		/*
		 * dp[i][0]：规定了今天不持股，有以下两种情况：
			* 昨天不持股，今天什么都不做；
			* 昨天持股，今天卖出股票（现金数增加），
		 * dp[i][1]：规定了今天持股，有以下两种情况：
			* 昨天持股，今天什么都不做（现金数与昨天一样）；
			* 昨天不持股，今天买入股票（注意：只允许交易一次，因此手上的现金数就是当天的股价的相反数）。
		 * 
		 */
		int len = prices.length;
		// 特殊判断
		if (len < 2) {
			return 0;
		}
		int[][] dp = new int[len][2];

		// dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
		// dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数

		// 初始化：不持股显然为 0，持股就需要减去第 1 天（下标为 0）的股价
		dp[0][0] = 0;
		dp[0][1] = -prices[0];

		// 从第 2 天开始遍历, 0代表第一天开始
		for (int i = 1; i < len; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
			dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
		}
		return dp[len - 1][0];
	}

	@Test
	public void test12() {
/* 		System.out.println(
			3/2+"=>"+3%2
		); */
		while(i) {
			System.out.println(i);
		}
	}

}
