package com.nekokan1500.leetcode.problem2;

/*
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit. 
 * Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */

public class Problem2Solution {
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode node = new ListNode();
       
        node.val = (l1.val + l2.val) % 10;
        if (l1.val + l2.val >= 10) {
        	node.next = addTwoNumbers(l1.next,l2.next,1);
        }
        else {
        	node.next = addTwoNumbers(l1.next,l2.next,0);
        }
        return node;  
    }
	
	private ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
		ListNode node = new ListNode();
		if (l1 == null && l2 == null){
			if (carry > 0) {
				node.val = carry;
				return node;
			}
			else {
				return null;
			}
        }
        else if (l1 == null && l2 != null) {
        	node.val = (l2.val + carry) % 10;
        	if (l2.val + carry >= 10) {
        		node.next = addTwoNumbers(null,l2.next,1);
        	}
        	else {
        		node.next = addTwoNumbers(null,l2.next,0);
        	}
        }
        else if (l1 != null && l2 == null) {
        	node.val = (l1.val + carry) % 10;
        	if (l1.val + carry >= 10) {
        		node.next = addTwoNumbers(l1.next,null,1);
        	}
        	else {
        		node.next = addTwoNumbers(l1.next,null,0);
        	}
        }
        else {
        	node.val = (l1.val + l2.val + carry) % 10;
            if (l1.val + l2.val + carry >= 10) {
            	node.next = addTwoNumbers(l1.next,l2.next,1);
            }
            else {
            	node.next = addTwoNumbers(l1.next,l2.next,0);
            }
        }
		
		return node;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode n11 = new ListNode(2);
		ListNode n12 = new ListNode(4);
		ListNode n13 = new ListNode(3);
		n11.next = n12;
		n12.next = n13;
		ListNode n21 = new ListNode(5);
		ListNode n22 = new ListNode(6);
		ListNode n23 = new ListNode(4);
		n21.next = n22;
		n22.next = n23;
		Problem2Solution s = new Problem2Solution();
		ListNode node = s.addTwoNumbers(n11,n21);
		System.out.println(node.val);
		ListNode next = node.next;
		while (next != null) {
			System.out.println(next.val);
			next = next.next;
		}
	}
	
	// output is 7,0,8

}
