package com.nekokan1500.leetcode.problem0037;

import java.util.HashMap;

/*
 * Leetcode problem #37; Sudoku solver
 */

public class Problem37Solution {
	
	// Solution: backtracking
	public void solveSudoku(char[][] board) {
        if (solveSudoku_(board, 0, 0) == false) System.out.println("No solution.");
        else {
        	for (int i = 0; i < 9; i++) {
    			for (int j = 0; j< 9; j++) {
    				System.out.print(board[i][j] + " ");
    			}
    			System.out.println();
    		}
        }
    }               
	
	private boolean checkValidity(char[][] board) {
		
		boolean isValid = true;
		
		HashMap<Integer, Character> rowMap = new HashMap<Integer, Character>();
		HashMap<Integer, Character> colMap = new HashMap<Integer, Character>();
		HashMap<Integer, Character> blockMap = new HashMap<Integer, Character>();
		
		// Check row and column validity
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (rowMap.containsValue(board[i][j])) return false;
				else rowMap.put(j, board[i][j]);
				if (colMap.containsValue(board[j][i])) return false;
				else colMap.put(j, board[j][i]);
			}
			rowMap.clear();
			colMap.clear();
		}

		// Check block validity
		for (int i = 0; i < 3; i++ ) {
			for (int j = 0; j < 3; j++) {
				int counter = 0;
				for (int k = 0; k < 3; k++) {
					for (int p = 0; p < 3; p++) {
						if (blockMap.containsValue(board[3*i+k][3*j+p])) return false;
						else blockMap.put(counter, board[3*i+k][3*j+p]);
						counter++;
					}
				}
				blockMap.clear();
			}
		}
		return isValid;
	}
	
	// The checkIsSafe method checks whether assiging a number will make
	// the grid invalid, which means the number already appears either in the current row,
	// column, or block
	private boolean checkIsSafe(char[][] board, int i, int j) {
		
		boolean isSafe = true;
		
		HashMap<Integer, Character> rowMap = new HashMap<Integer, Character>();
		HashMap<Integer, Character> colMap = new HashMap<Integer, Character>();
		HashMap<Integer, Character> blockMap = new HashMap<Integer, Character>();
		
		// Check row and column validity
		for (int n = 0; n < 9; n++) {
			if (board[i][n] != '.' && rowMap.containsValue(board[i][n])) return false;
			else rowMap.put(n, board[i][n]);
			if (board[n][j] != '.' && colMap.containsValue(board[n][j])) return false;
			else colMap.put(n, board[n][j]);
		}

		// Check block validity
		int counter = 0;
		i = i/3;
		j = j/3;
		for (int k = 0; k < 3; k++) {
			for (int p = 0; p < 3; p++) {
				if (board[3*i+k][3*j+p] != '.') {
					if (blockMap.containsValue(board[3*i+k][3*j+p])) return false;
					else blockMap.put(counter, board[3*i+k][3*j+p]);
					counter++;
				}
			}
		}
		return isSafe;
	}
	
	private boolean solveSudoku_(char[][] board, int i, int j) {
		
		boolean hasSolution = false;
		
		if (i == 8 && j == 8) {
			if (board[8][8] != '.') {
				if (checkValidity(board)) return true; else return false;
			}
			else {
				for (char c = 49; c <= 57; c++) {
					board[i][j] = c;
					if (checkValidity(board)) return true;
				}
			}
		}
		else if (j == 9) {
			return solveSudoku_(board, i+1, 0);
		}
		else {
			if (board[i][j] != '.') {
				return solveSudoku_(board, i, j+1);
			}
			else {
				for (char c = 49; c <= 57; c++) {
					board[i][j] = c;
					if (checkIsSafe(board, i, j)) {
						if (solveSudoku_(board, i, j+1)) return true;
						else board[i][j] = '.';
					}
					else board[i][j] = '.';
				}
			}
		}
		return hasSolution;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem37Solution s = new Problem37Solution();
		char[][] board = 
			{{'5','3','.','.','7','.','.','.','.'},
			 {'6','.','.','1','9','5','.','.','.'},
			 {'.','9','8','.','.','.','.','6','.'},
			 {'8','.','.','.','6','.','.','.','3'},
			 {'4','.','.','8','.','3','.','.','1'},
			 {'7','.','.','.','2','.','.','.','6'},
			 {'.','6','.','.','.','.','2','8','.'},
			 {'.','.','.','4','1','9','.','.','5'},
			 {'.','.','.','.','8','.','.','7','9'}};
		
		s.solveSudoku(board);
	}
}