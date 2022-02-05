package com.nekokan1500.leetcode.problem0032;

import java.util.Stack;
/*
 * Solution to leetcode problem #32: Longest Valid Parentheses
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 */

public class Problem32Solution {
	
	
	// Sliding window solution, works but slow
	public int longestValidParentheses(String s) {
        
		// The input string must have at least one ( and one )
		if (s.length() < 2) return 0;
		int index = s.indexOf("(");
		int index_right = s.indexOf(")");
		if (index == -1 || index_right == -1) return 0;
		
		int answer = 0;
		int num_left = 1;
		int num_right = 0;
		int next_left = index;
		index += 1;
		
		while (index <= s.length() && index != -1) {
			if (index < s.length()) {
				if (s.charAt(index) == '(') 
					num_left += 1;
				else 
					num_right += 1;
				if (num_left == num_right){
					if (answer < num_left + num_right) answer = num_left + num_right;
					index += 1;
					if (index < s.length()) {
						if (s.charAt(index) == ')') {
							next_left = s.indexOf("(", index);
							if (next_left != -1) {
								index = next_left +1;
								num_left = 1;
								num_right = 0;
							}
							else break;
						}
						else {
							num_left += 1;
							next_left = s.indexOf("(", index+1);
							index += 1;
						}
					}
				}
				else {
					index += 1;
				}
		}
		else {
			if (next_left != -1) {
				index = next_left + 1;
				num_left = 1;
				num_right = 0;
				next_left = s.indexOf("(", index);
			}
			else
				index += 1;
		}
	}	
	return answer;
 }

	// Dynamic programming solution runs in linear time :)
	public int longestValidParentheses_dp(String s) {
		
		// input string length should be at least 2
		if (s.length() < 2) return 0;
		
		int answer = 0;
		
		// initialize the array that tracks intermediate results
		int[] dp = new int[s.length()+1];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = 0;
		}
		
		// scan every two consecutive characters
		for (int i = 2; i < dp.length; i++) {
			// case 1: the two characters are "(" and ")"
			if (s.charAt(i-2) == '(' && s.charAt(i-1) == ')') {
				dp[i] = dp[i-2] + 2;
				answer = Math.max(answer, dp[i]);
			}
			// case 2: the two characters are ")" and ")"
			else if (s.charAt(i-2) == ')' && s.charAt(i-1) == ')') {
				if ((i-dp[i-1]-2 >= 0) && s.charAt(i - 1 - dp[i-1] - 1) == '(') {
					dp[i] = dp[i - dp[i-1] - 2] + dp[i-1] + 2;
					answer = Math.max(answer, dp[i]);
				}
			}
		}
		
		return answer;
	}
	
	// Using stack, slower than dynamic programming
	public int longestValidParentheses_stack(String s) {
		
		// input string length should be at least 2
		if (s.length() < 2) return 0;
				
		int answer = 0;
		
		// create a stack to keep the indices of left parentheses
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(-1);
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				stack.push(i);
			else if (s.charAt(i) == ')') {
				stack.pop();
				if (!stack.isEmpty()) answer = Math.max(answer, i - stack.peek());
				else stack.push(i);
			}
		}
		
		return answer;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem32Solution s = new Problem32Solution();
		String str = ")()())";
		System.out.println(s.longestValidParentheses_dp(str));

	}

}
