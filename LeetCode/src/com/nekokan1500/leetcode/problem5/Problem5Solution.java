package com.nekokan1500.leetcode.problem5;

/*
 * Solution to LeetCode problem #5: Longest Palindromic Substring
 * Given a string s, return the longest palindromic substring in s.
 * This solutions takes a dynamic programming approach
 */

public class Problem5Solution {
	
	public String longestPalindrome(String s) {
		
		int longest = 1;
		int start = 0; // starting index of the longest palindrome
		int end = 1;	// ending index of the longest palindrome
		
		int n = s.length();
		
		int[][] subs = new int[n][n+1];
		
		// check if s.substring(i,j) is a palindrome
		// initialize the substring index matrix
		for (int i = 0; i < n; i++) {
			// all substrings of size 1 are palindromes
			subs[i][i+1] = 1;
		}
		
		// check if any substring of size 2 is palindrome or not
		for (int i = 0; i < n-1; i++) {
			if (s.charAt(i) == s.charAt(i+1))
				subs[i][i+2] = 1;
			else
				subs[i][i+2] = 0;
		}
		
		// check other substrings
		for (int i = n-3; i >= 0; i--) {
			for (int j = i+3; j < n+1; j++) {
				char left = s.charAt(i);
				char right = s.charAt(j-1);
				if (subs[i+1][j-1] == 1 && left == right) 
					subs[i][j] = 1;
				else
					subs[i][j] = 0;
			}
		}
		
		// find the longest substring
		for (int i = n-1; i >= 0; i--) {
			for (int j = n; j >= 1; j--) {
				if (subs[i][j] == 1) {
					if (j - i > longest) {
						longest = j - i;
						start = i;
						end = j;
					}
				}
			}
		}
		
		return s.substring(start,end);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem5Solution s = new Problem5Solution();
		System.out.println(s.longestPalindrome("aacabdkacaa"));
	}

}
