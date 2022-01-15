package com.nekokan1500.leetcode.problem0014;

import java.util.Arrays;

public class Problem14Solution {
	
	// vertical scanning
	// performance wise is the best of the three solutions in this class
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
				else break out;
			}
			if (match) prefix += current;
		}
		return prefix;
    }
	
	// Solution 2: horizontal scanning
	public String longestCommonPrefix2(String[] strs) {
		
		String prefix = "";
		
		if (strs.length == 1) return strs[0];
		else {
		
			String str1 = strs[0];
			String str2 = strs[1];
			
			int i = 0;
			while (i < str1.length() && i < str2.length() && str1.charAt(i) == str2.charAt(i)) {
				prefix += strs[1].charAt(i);
				i++;
			}
			
			strs = Arrays.copyOfRange(strs, 1, strs.length);
			strs[0] = prefix;
			prefix = longestCommonPrefix2(strs);
		}
		return prefix;
		
	}
	
	// Solution 3: divide and conquer
	public String longestCommonPrefix3(String[] strs) {
		
		if (strs.length == 0) return "";
		else if (strs.length == 1) return strs[0];
		else if (strs.length == 2) {
			String prefix = "";
			int i = 0;
			while (i < strs[0].length() && i < strs[1].length() && strs[0].charAt(i) == strs[1].charAt(i)) {
				prefix += strs[1].charAt(i);
				i++;
			}
			return prefix;
		}
		else {
			String prefix1 = longestCommonPrefix3(Arrays.copyOfRange(strs, 0, strs.length/2));
			String prefix2 = longestCommonPrefix3(Arrays.copyOfRange(strs, strs.length/2, strs.length));
			String[] newstrs = new String[2];
			newstrs[0] = prefix1;
			newstrs[1] = prefix2;
			return longestCommonPrefix3(newstrs);
		}
	}
	
	public static void main(String[] args) {
		Problem14Solution s = new Problem14Solution();
		String[] strs = {"abbb","a","accc","aa","aacd"};
		System.out.println(s.longestCommonPrefix2(strs));
	}

}
