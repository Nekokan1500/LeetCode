package com.nekokan1500.leetcode.problem0014;

public class Problem14Solution {
	
	public String longestCommonPrefix(String[] strs) {
        
		String prefix = "";
		
		int n = strs.length;
		
		out: for (int i = 0; i < strs[0].length(); i++) {
			char current = strs[0].charAt(i);
			boolean match = true;
			for (int j = 1; j < n; j++) {
				if (i < strs[j].length()) {
					if (strs[j].charAt(i) != current) {
						match = false;
						break out;
					}
				} 
				else
					break out;
			}
			if (match) prefix += current;
		}
		
		
		return prefix;
    }
	
	public static void main(String[] args) {
		Problem14Solution s = new Problem14Solution();
		String[] strs = {"ab","a"};
		System.out.println(s.longestCommonPrefix(strs));
	}

}
