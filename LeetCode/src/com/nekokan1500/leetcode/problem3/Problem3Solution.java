package com.nekokan1500.leetcode.problem3;

/*
 * LeetCode problem #3: Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.
 */

public class Problem3Solution {
	
	public int lengthOfLongestSubstring(String s) {

	    if (s.length() == 0) return 0;
	    if (s.length() == 1) return 1;
		
		int longest = 0;
	    String temp = "";
	    
	    for (char c: s.toCharArray()) {
	    	String str = String.valueOf(c);
	    	if (temp.contains(str) == true) {
	    		temp = temp.substring(temp.indexOf(c)+1);
	    	}
	    	temp += str;
	    	longest = Math.max(temp.length(), longest);
	    }
	    return longest;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem3Solution s = new Problem3Solution();
		System.out.println(s.lengthOfLongestSubstring("aab"));
	}

}
