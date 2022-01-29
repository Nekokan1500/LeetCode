package com.nekokan1500.leetcode.problem0025;

public class Problem25Solution {
	
	public ListNode reverseKGroup(ListNode head, int k) {
        
		if (head == null) return null;
		if (k == 1) return head;
		
		int i = 1;
		ListNode first = head;
		ListNode last = null;
		ListNode next_node = null;
		ListNode next = null;
		ListNode prev = null;
		
		while (head.next !=null) {
			head = head.next; 
			i++;
			if (i == k) {last = head; next_node = head.next; break;}
		}
		if (i < k) return first; else {head = first; prev = first; next = head.next;}
		
		while (head != last) {
			head = next;
			next = next.next;
			head.next = prev;
			prev = head;
		}
		
		first.next = reverseKGroup(next_node,k);
		
		return last;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem25Solution s = new Problem25Solution();
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		ListNode result = s.reverseKGroup(node1, 2);
		System.out.println(result.val);
	}

}
