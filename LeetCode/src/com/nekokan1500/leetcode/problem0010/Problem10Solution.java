package com.nekokan1500.leetcode.problem0010;

public class Problem10Solution {
	
	// Recursive solution
	public boolean isMatch(String text, String pattern) {
		
        if (pattern.isEmpty()) return text.isEmpty();
        
        boolean first_match = (!text.isEmpty() && (text.charAt(0) == pattern.charAt(0) ||
        					   pattern.charAt(0) == '.'));
        
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
        	return (isMatch(text, pattern.substring(2)) ||
        			(first_match && isMatch(text.substring(1), pattern)));
        }
        else {
        	return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }
	
	// Dynamic programming solution
	enum Result {TRUE, FALSE};
	Result[][] memo;
	
	// Top-down approach
	public boolean dpMatch(String text, String pattern) {
		memo = new Result[text.length()+1][pattern.length()+1];
		return dp(0, 0, text, pattern);
	}
	
	public boolean dp(int i, int j, String text, String pattern) {
		
		if (memo[i][j] != null) {
			return memo[i][j] == Result.TRUE;
		}
		
		boolean ans;
		
		if (j == pattern.length()) {
			ans = i == text.length();
		}
		else {
			boolean firstMatch = (i < text.length() &&
								 (pattern.charAt(i) == text.charAt(i) || pattern.charAt(i) == '.'));
			
			if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*') {
				ans = (dp(i, j+2, text, pattern) ||
					  (firstMatch && dp(i+1, j, text, pattern)));
			}
			else {
				ans = firstMatch && dp(i+1, j+1, text, pattern);
			}
		}
		
		memo[i][j] = ans? Result.TRUE: Result.FALSE;
		return ans;
	}
	
	// bottom-up approach
	public boolean dpMatch2(String text, String pattern) {
		boolean[][] dp = new boolean[text.length()+1][pattern.length()+1];
		dp[text.length()][pattern.length()] = true;
		
		for (int i = text.length(); i >= 0; i--) {
			for (int j = pattern.length() - 1; j >= 0; j--) {
				boolean firstMatch = (i < text.length() && 
						(text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.'));
				if (j+1 < pattern.length() && pattern.charAt(j+1) == '*') {
					dp[i][j] = dp[i][j+2] || (firstMatch && dp[i+1][j]);
				}
				else {
					dp[i][j] = firstMatch && dp[i+1][j+1];
				}
			}
		}
		
		return dp[0][0];
	}
	
	
	public static void main(String[] args) {
		Problem10Solution s = new Problem10Solution();
		boolean match = s.dpMatch("mississippi", "mis*is*p*.");
		System.out.println(match);
	}

}
