package com.nekokan1500.leetcode.problem6;

public class Problem6Solution {

	public String convert(String s, int numRows) {
		
		if (numRows == 1) {
			return s;
		}
		
		int len = s.length();
		
		// create a matrix based on the size of the input string and the number of rows
		int numCols = (int) Math.ceil(len/2.0);
        int[][] matrix = new int[numRows][numCols];
        
        for (int i = 0; i < len; i++) {
        	// determine the block where the letter at index i belongs to
        	int b = (int) Math.ceil((i+1)/(2.0*(numRows-1)));
        	int diff = (i+1) - (b-1)*(2*(numRows-1));
        	if (diff <= numRows) {
        		matrix[diff-1][(b-1)*(numRows-1)] = i;
        	}
        	else {
        		int col_diff = diff - numRows;
        		matrix[numRows-1-col_diff][(b-1)*(numRows-1)+col_diff] = i;
        	}
        }
        
        String result = "" + s.charAt(0);
        
        for (int i = 0; i < numRows; i++) {
        	for (int j = 0; j < numCols; j++) {
        		if (matrix[i][j] != 0) {
        			result += s.charAt(matrix[i][j]);
        		}
        	}
        }
        
        return result;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem6Solution s = new Problem6Solution();
		System.out.println(s.convert("AB", 1));
	}

}
