package com.nekokan1500.leetcode.problem0041;

/*
 * *Solution to LeetCode problem #41: First Missing Positive
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * Solution must run in O(n) time and uses constant extra space.
 */

public class Problem41Solution {

	public int firstMissingPositive(int[] nums) {
		
		for (int i = 0; i < nums.length; ) {
	        if (nums[i] > 0 && i != nums[i] - 1 && nums[i] - 1 < nums.length && nums[nums[i] - 1] != nums[i]) {
	            swap(nums, nums[i] - 1, i);
	        } else{
	            i++;
	        }
	    }

	    for(int i = 0; i < nums.length; i ++) {
	        if (nums[i] !=i+1)
	            return i+1;
	    }

	    return nums.length + 1;
	}

	public static void swap(int[] nums, int i, int j) {
	    int temp = nums[i];
	    nums[i] = nums[j];
	    nums[j] = temp;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem41Solution s = new Problem41Solution();
		int[] nums = {3,4,5,6};
		System.out.println(s.firstMissingPositive(nums));
	}
}
