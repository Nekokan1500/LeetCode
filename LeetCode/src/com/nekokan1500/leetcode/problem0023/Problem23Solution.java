package com.nekokan1500.leetcode.problem0023;

import java.util.Arrays;

public class Problem23Solution {
	
	public ListNode mergeKLists(ListNode[] lists) {
		
		if (lists.length == 1) return lists[0];
		if (lists.length == 2) {
			if (lists[0] == null && lists[1] == null) return null;
			if (lists[0] != null && lists[1] == null) return lists[0];
			if (lists[0] == null && lists[1] != null) return lists[1];
			// initialize pointers
			ListNode result = lists[0].val <= lists[1].val? lists[0]: lists[1];
			ListNode current = result;
			ListNode lna = lists[0].val <= lists[1].val? lists[0].next: lists[0];
			ListNode lnb = lists[0].val <= lists[1].val? lists[1]: lists[1].next;
			
			while  (lna != null || lnb != null) {
				if (lna != null && lnb != null) {
					current.next = lna.val <= lnb.val? lna: lnb;
					current = current.next;
					if (current == lna) lna = lna.next;
					else lnb = lnb.next;
				}
				else if (lna != null && lnb == null)
					{current.next = lna; current = current.next; lna = lna.next;}
				else
					{current.next = lnb; current = current.next; lnb = lnb.next;}
			}
			return result;
		}
		else {
			ListNode[] list1 = Arrays.copyOfRange(lists, 0, lists.length/2);
			ListNode[] list2 = Arrays.copyOfRange(lists, lists.length/2, lists.length);
			ListNode[] r = new ListNode[2];
			r[0] = mergeKLists(list1);
			r[1] = mergeKLists(list2);
			return mergeKLists(r);
		}
    }
	
	public static void main(String[] args) {
		Problem23Solution s = new Problem23Solution();
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node6 = new ListNode(6);
		ListNode node0 = new ListNode(0);
		ListNode node22 = new ListNode(2);
		ListNode node5 = new ListNode(5);
		node1.next = node3;
		node2.next = node4;
		node4.next = node6;
		node0.next = node22;
		node22.next = node5;
		ListNode[] lists = new ListNode[3];
		lists[0] = node1;
		lists[1] = node2;
		lists[2] = node0;
		ListNode r = s.mergeKLists(lists);
		System.out.println(r.val); // [0,1,2,2,3,4,5,6]
	}

}
