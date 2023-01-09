/*
 * @lc app=leetcode.cn id=138 lang=java
 *
 * [138] 复制带随机指针的链表
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
		Node cur = head;
		Map<Node, Node> map = new HashMap<Node, Node>();
		if(head==null) return null;
		while(cur != null){
			map.put(cur, new Node(cur.val));
			cur = cur.next;
		}

        cur = head;

		while(cur != null){
			map.get(cur).next = map.get(cur.next);
			map.get(cur).random = map.get(cur.random);
			cur = cur.next;
		}

		return map.get(head);
		
    }
}
// @lc code=end

