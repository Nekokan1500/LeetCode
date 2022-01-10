package com.nekokan1500.leetcode.problem9;

public class Problem9Solution {
	
	public boolean isPalindrome(int x) {
		
		if (x < 0) return false;
        
		int rev = 0;
		
		int d = 0;
		int y = x;
		
		while (y != 0) {
			d = y % 10;
			if (rev >= Integer.MAX_VALUE / 10) return false;
			else if (rev < Integer.MAX_VALUE / 10) {
				rev = rev*10 + d;
				y = y / 10;
			}
		}
		
		if (x == rev) return true;
		else return false;
    }
	
	
	public static void main(String[] args) {
		Problem9Solution s = new Problem9Solution();
		System.out.println(s.isPalindrome(123454321));
		
	}
}
