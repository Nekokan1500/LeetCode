package com.nekokan1500.leetcode.problem8;

public class Problem8Solution {
	
	public int myAtoi(String s) {
		
		String numString = "";
        
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (numString == "") {
				if ((c < 48 || c > 57) && c != 43 && c != 45 && c!= 32) return 0; 	// 32 is white space
				else if ((c == 43 || c == 45) || 							// 43 is + and 45 is -
						(c >= 48 && c <= 57))
					numString += c;
			}
			else {
				if (c >= 48 && c <= 57) 		// 48 is 0 and 57 is 9
					numString += c;
				else
					break;
			}
		}
		
		int i = 0; // i is the index of the first number
		boolean sign = true; // assume number is positive
		
		if (numString == "") return 0;
		if (numString.charAt(0) == 45) sign = false;
		
		while (i < numString.length()) {
			char c = numString.charAt(i);
			if (c == 43 || c == 45 || c == 48) i++;
			else break;
		}
		
		String numString_cleaned = numString.substring(i);
		
		if ((numString_cleaned.length() > 10 && sign) ||
			(numString_cleaned.length() == 10 && numString_cleaned.charAt(0) > 50 && sign)) 
			return Integer.MAX_VALUE;
		if ((numString_cleaned.length() > 10 && !sign) ||
			(numString_cleaned.length() == 10 && numString_cleaned.charAt(0) > 50 && !sign))
			return -Integer.MAX_VALUE-1;
		
		int result = 0;
		
		for (int j = 0; j < numString_cleaned.length(); j++) {
			int next;
			if (sign)
				next = (numString_cleaned.charAt(j) - 48) * (int)Math.pow(10,(numString_cleaned.length() - j - 1));
			else
				next = -(numString_cleaned.charAt(j) - 48) * (int)Math.pow(10,(numString_cleaned.length() - j - 1));
			if ((next <= Integer.MAX_VALUE - result && sign) ||
				(next >= -Integer.MAX_VALUE - 1 - result && !sign))
				result += next;
			else {
				if (sign) return Integer.MAX_VALUE;
				else return -Integer.MAX_VALUE-1;
			}
		}
		
		return result;
    }
	
	public static void main(String[] args) {
		Problem8Solution s = new Problem8Solution();
		System.out.println(s.myAtoi("2147483646"));
	}
}
