package com.nekokan1500.leetcode.problem7;

/*
 * Solution to LeetCode problem #7: Reverse integer
 */

public class Problem7Solution {
	
	public int reverse(int x) {
		
		if (x == -Integer.MAX_VALUE-1) return 0;
		
		int i = 1;
		
		while (Math.abs(x % (int)Math.pow(10, i)) < Math.abs(x) && i < 10) {
			i++;
		}
		
		// check integer overflow or underflow
		if (Math.abs(x % 10) > 2 && i >= 10){
			return 0;
		}
		else {
			int result = 0;
			while (i > 0) {
				int next = (x % 10) * (int) Math.pow(10, i-1);
				if ((x >= 0 && next <= Integer.MAX_VALUE - result) ||
					(x < 0 && next >= -Integer.MAX_VALUE-1 - result)) {
						result += next;
						x = x / 10;
						i -= 1;	
				}	
				else {
					return 0;
				}	
			}
			return result;	
		}
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem7Solution s = new Problem7Solution();
		System.out.println(s.reverse(5));
		// output is -463847412
	}

}
