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
		
		numString = numString.substring(i);
		
		int result = 0;
		
		for (int j = 0; j < numString.length(); j++) {
			// if current result is less than 214748364
			if (result < Integer.MAX_VALUE/10) {
				result = result*10 + numString.charAt(j) - 48;
			}
			// if current result is larger than 214748364
			else if (result > Integer.MAX_VALUE/10) {
				if (sign) return Integer.MAX_VALUE;
				else return Integer.MIN_VALUE;
			}
			// if current result is equal to 214748364
			else {
				// if next number is 0~7
				if (numString.charAt(j) <= 55)
					result = result*10 + (numString.charAt(j) - 48);
				// if next number is 8
				else if (numString.charAt(j) >= 56) {
					if (sign) return Integer.MAX_VALUE;
					else return Integer.MIN_VALUE;
				}	
			}
		}
		
		if (sign) return result;
		else return -1*result;
    }
	
	public static void main(String[] args) {
		Problem8Solution s = new Problem8Solution();
		System.out.println(s.myAtoi("-2147483646"));
	}
}
