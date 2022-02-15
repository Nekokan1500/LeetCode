package com.nekokan1500.leetcode.problem0042;

import java.util.Arrays;
import java.util.Stack;

/*
 * Soution to LeetCode problem #42: Trapping rain water
 */

public class Problem42Solution {
	
	public int trap(int[] height) {
		
		if (height.length <= 2) return 0;
		int rain = 0;
		
		int start = 0;
		int end = 0;
		
		for (int i = 1; i < height.length; i++) {
			if (height[i] >= height[start]) {
				end = i;
				break;
			}
		}
		
		int [] rest;
		
		if (end == 0) {
			end = height.length - 1;
			for (int i = end - 1; i >= 0; i--) {
				if (height[i] >= height[end]) {
					start = i;
					break;
				}
			}
			rain = calc(height, start, end);
			rest = Arrays.copyOfRange(height, 0, start+1);
		}else {
			rain = calc(height, start, end);
			rest = Arrays.copyOfRange(height, end, height.length);
		}
		return rain + trap(rest);
    }
	
	private int calc(int[] height, int i, int j) {
		int area = 0;
		if (height[i] <= height[j]) {
			for (int k = i+1; k < j; k++) {
				area += height[i] - height[k];
			}
		}
		else {
			for (int k = j-1; k > i; k--) {
				area += height[j] - height[k];
			}
		}
		return area;
	}

	// *Dynamic programming solution
	public int trap_dp(int[] height) {
		
		int rain = 0;
		
		// Find maximum height of bar from the left end for each index
		int[] left_max = new int[height.length];
		left_max[0] = height[0];
		// Find maximum height of bar from the right end for each index
		int[] right_max = new int[height.length];
		right_max[height.length-1] = height[height.length-1];
		
		for (int i = 1; i < height.length; i++) {
			left_max[i] = Math.max(height[i], left_max[i-1]);
		}
		
		for (int i = height.length - 2; i >= 0; i--) {
			right_max[i] = Math.max(height[i], right_max[i+1]);
		}
		
		for (int i = 0; i < height.length; i++) {
			rain += Math.min(left_max[i],  right_max[i]) - height[i];
		}
		
		return rain;
	}
	
	// *Stack solution
	public int trap_stack(int[] height) {
		
		int rain = 0;
		int current = 0;
		Stack<Integer> stack = new Stack<Integer>();
		
		while (current < height.length) {
			while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
				int top = stack.peek();
				stack.pop();
				if (stack.isEmpty()) break;
				int distance = current - stack.peek() - 1;
				int bounded_height = Math.min(height[current] - height[top], height[stack.peek()] - height[top]);
				rain += distance * bounded_height;
				
			}
			stack.push(current++);
		}
		
		return rain;
	}
	
	// *Two pointers solution
	public int trap_tp(int[] height) {
		
		int rain = 0;
		int left = 0;
		int right = height.length - 1;
		int left_max = 0;
		int right_max = 0;
		
		while (left < right) {
			if (height[left] <= height[right]) {
				if (height[left] >= left_max)
					left_max = height[left];
				else
					rain += left_max - height[left];
				left += 1;
			}
			else {
				if (height[right] >= right_max)
					right_max = height[right];
				else
					rain += right_max - height[right];
				right -= 1;
			}
		}
		
		return rain;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem42Solution s = new Problem42Solution();
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(s.trap_dp(height));
	}

}
