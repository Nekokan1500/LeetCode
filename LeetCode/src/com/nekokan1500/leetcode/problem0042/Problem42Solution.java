package com.nekokan1500.leetcode.problem0042;

import java.util.Arrays;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem42Solution s = new Problem42Solution();
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(s.trap(height));
	}

}
