package com.nekokan1500.leetcode.problem0012;

/* solution to leetcode problem 12 and 13: Integer to Roman
 * and Roman to Integer
 */

public class Problem1213Solution {
	
	public String intToRoman(int num) {
        
		String roman ="";
		
		if (num < 4) {
			for (int i = 0; i < num; i++) {
				roman += "I";
			}
			return roman;
		}
		else if (num <= 10) {
			switch (num) {
			case 4: return "IV";
			case 5: return "V";
			case 9: return "IX";
			case 10: return "X";
			default: {
				roman += "V";
				roman += intToRoman(num - 5);
			}
			}
		}
		else if (num <= 50) {
			if (num == 50) return "L";
			else if (num >= 40) return "XL" + intToRoman(num-40);
			else {
				for (int i = 0; i < num/10; i++) {
					roman += "X";
				}
				return roman += intToRoman(num % 10);
			}
		}
		else if (num <= 100) {
			if (num == 100) return "C";
			else if (num >= 90) return "XC" + intToRoman(num-90);
			else {
				roman += "L";
				int n = (num-50)/10;
				for (int i = 0; i < n; i++) {
					roman += "X";
					num -= 10;
				}
				return roman += intToRoman(num-50);
			}
		}
		else if (num <= 500) {
			if (num == 500) return "D";
			else if (num >= 400) return "CD" + intToRoman(num-400);
			else {
				int n = num/100;
				for (int i = 0; i < n; i++) {
					roman += "C";
					num -= 100;
				}
				return roman += intToRoman(num);
			}
		}
		else if (num <= 1000) {
			if (num == 1000) return "M";
			else if (num >= 900) return "CM" + intToRoman(num-900);
			else {
				roman += "D";
				int n = (num-500)/100;
				for (int i = 0; i < n; i++) {
					roman += "C";
					num -= 100;
				}
				return roman += intToRoman(num-500);
			}
		}
		else {
			int n = num/1000;
			for (int i = 0; i < n; i++) {
				roman += "M";
				num -= 1000;
			}
			return roman += intToRoman(num);
		}
		
		
		return roman;
    }
	
	public int romanToInt(String s) {
        
		int num = 0;
		
		int i = 0;
		while (i < s.length()) {
			if (s.charAt(i) == 'M') { num += 1000; i += 1;}
			else if (s.charAt(i) == 'C' && (i+1) < s.length() && s.charAt(i+1) == 'M') {
				num += 900; i += 2;
			}
			else if (s.charAt(i) == 'D') {num += 500; i += 1;}
			else if (s.charAt(i) == 'C' && (i+1) < s.length() && s.charAt(i+1) == 'D') {
				num += 400; i += 2;
			}
			else if (s.charAt(i) == 'C') {num += 100; i += 1;}
			else if (s.charAt(i) == 'X' && (i+1) < s.length() && s.charAt(i+1) == 'C') {
				num += 90; i += 2;
			}
			else if (s.charAt(i) == 'L') {num += 50; i += 1;}
			else if (s.charAt(i) == 'X' && (i+1) < s.length() && s.charAt(i+1) == 'L') {
				num += 40; i += 2;
			}
			else if (s.charAt(i) == 'X') {num += 10; i += 1;}
			else if (s.charAt(i) == 'I' && (i+1) < s.length() && s.charAt(i+1) == 'X') {
				num += 9; i += 2;
			}
			else if (s.charAt(i) == 'V') {num += 5; i += 1;}
			else if (s.charAt(i) == 'I' && (i+1) < s.length() && s.charAt(i+1) == 'V') {
				num += 4; i += 2;
			}
			else if (s.charAt(i) == 'I') {num += 1; i += 1;}
		}
		
		
		return num;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem1213Solution s = new Problem1213Solution();
		System.out.println(s.intToRoman(41));
		System.out.println(s.romanToInt("III"));
	}

}
