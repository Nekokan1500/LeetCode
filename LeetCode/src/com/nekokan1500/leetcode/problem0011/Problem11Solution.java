package com.nekokan1500.leetcode.problem0011;

/*
 * Solution to LeetCode problem #11: Container with most water
 */

public class Problem11Solution {
	
	public int maxArea(int[] height) {
		
		// keep track of the maximum area
		int maxA = 0;
		int i = 0;
		int j = height.length-1;
		// current area between two bars
		int current = 0;
		
		while (i < j) {
			if (height[i] >= height[j]) {
				current = height[j]*(j - i);
				j = j - 1;
			}
			else {
				current = height[i]*(j - i);
				i = i + 1;
			}
			maxA = current > maxA? current: maxA;
		}
		
		return maxA;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem11Solution s = new Problem11Solution();
		int[] heights = {1,8,6,2,5,4,8,25,7};
		System.out.println(s.maxArea(heights)); // 49

	}

}
