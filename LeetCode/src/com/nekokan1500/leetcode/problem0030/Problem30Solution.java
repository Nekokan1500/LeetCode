package com.nekokan1500.leetcode.problem0030;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Arrays;

/*
 * LeetCode Problem #30: Substring with Concatenation of All Words
 * You are given a string s and an array of strings words of the same length. 
 * Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, 
 * in any order, and without any intervening characters. You can return the answer in any order.
 */

public class Problem30Solution {
	
	
	// Solution 1: Check All Indices Using a Hash Table that keeps track of words that have appeared
	public List<Integer> findSubstring_s1(String s, String[] words){
		
		List<Integer> result = new ArrayList<Integer>();
		
		if (words.length == 0) return result;
		
		int wordLength = words[0].length();
		
		if (s.length() < words.length*wordLength) return result;
		
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			if (! wordCount.containsKey(words[i]))
				wordCount.put(words[i], 1);
			else
				wordCount.put(words[i], wordCount.get(words[i])+1);
		}
		
		int index = 0;
		while (index <= s.length() - words.length*wordLength) {
			if (checkIndex(s.substring(index, index+words.length*wordLength), wordLength, wordCount)) 
				result.add(index);
			index += 1;
		}
		
		return result;
	}
	
	private boolean checkIndex(String str, int wordLength, HashMap<String, Integer> wordCount) {
		boolean hasSubstring = true;
		HashMap<String, Integer> wordCountTable = new HashMap<String, Integer>(wordCount);
		int index = 0;
		while (index <= str.length() - wordLength) {
			String s = str.substring(index, index+wordLength);
			if (wordCountTable.containsKey(s) && wordCountTable.get(s) > 0) {
				wordCountTable.put(s, wordCountTable.get(s)-1);
				index += wordLength;
			}
			else
				return false;
		}
		
		return hasSubstring;
	}
	
	// Solution 2: Sliding window (surprising slower than solution 1:(  )
	public List<Integer> findSubstring_s2(String s, String[] words){
		
		List<Integer> result = new ArrayList<Integer>();
		
		if (words.length == 0) return result;
		
		int wordLength = words[0].length();
		
		if (s.length() < words.length*wordLength) return result;
		
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
		for (int i = 0; i < words.length; i++) {
			if (! wordCount.containsKey(words[i]))
				wordCount.put(words[i], 1);
			else
				wordCount.put(words[i], wordCount.get(words[i])+1);
		}
		
		for (int i = 0; i < wordLength; i++) {
            result.addAll(slidingWindow(i, s, wordLength, words.length, wordCount));
        }
		
		return result;
	}
	
	private List<Integer> slidingWindow(int left, String s, int wordLength, int numWords, HashMap<String, Integer> wordCount) {
		
		List<Integer> answer = new ArrayList<Integer>();
		
		HashMap<String, Integer> wordCountTable = new HashMap<String, Integer>(wordCount);
		
		int right = left;
		while (right <= s.length() - wordLength) {
			String sub = s.substring(right, right+wordLength);
			if (wordCountTable.containsKey(sub) && wordCountTable.get(sub) > 0) {
				int count = wordCountTable.get(sub)-1;
				wordCountTable.put(sub, count);
				right += wordLength;
				if ((right - left) == wordLength*numWords) {
					answer.add(left);
					left = left + wordLength;
					right = left;
					wordCountTable = new HashMap<String, Integer>(wordCount);
				}
			}
			else if (!wordCountTable.containsKey(sub)){
				wordCountTable = new HashMap<String, Integer>(wordCount);
				left = right + wordLength;
				right = left;
			}
			else {
				wordCountTable = new HashMap<String, Integer>(wordCount);
				left = left + wordLength;
				right = left;
			}
		}
		return answer;
	}
	
	// my solution
	public List<Integer> findSubstring(String s, String[] words) {
		
		List<Integer> result = findSubstring_(s, words);
		
		Set<Integer> resultSet = new LinkedHashSet<Integer>();
		resultSet.addAll(result);
		result.clear();
		result.addAll(resultSet);
		return result;
    }
	
	public List<Integer> findSubstring_(String s, String[] words){
		List<Integer> result = new ArrayList<Integer>();
		
		int w_len = words[0].length();
		if (s.length() < words.length*w_len) return result;
		if (words.length == 0) return result;
		if (words.length == 1) {
			int i = s.indexOf(words[0]);
			while (i != -1) {
				result.add(i);
				i = s.indexOf(words[0],i+w_len);
			}
		}
		else {
			for (int i = 0; i < words.length; i++) {
				int index = 0;
				int loc = 0;
				while (index < s.length() && loc != -1) {
					List<String> strs = new ArrayList<String>(Arrays.asList(words));
					index = s.indexOf(words[i], loc);
					if (index == -1) return new ArrayList<Integer>();
					else {
						loc = s.indexOf(words[i], index+1);
						strs.remove(i);
						String[] new_words = new String[strs.size()];
						new_words = strs.toArray(new_words);
						List<Integer> subresult = findSubstring_(s.substring(index+w_len),new_words);
						if (subresult.contains(0)) {
							result.add(index);
						}
						index = loc;
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem30Solution s = new Problem30Solution();
		String str = "bcabbcaabbccacacbabccacaababcbb";
		String[] words = new String[9];
		words[0] = "c";
		words[1] = "b";
		words[2] = "a";
		words[3] = "c";
		words[4] = "a";
		words[5] = "a";
		words[6] = "a";
		words[7] = "b";
		words[8] = "c";
		List<Integer> result = s.findSubstring_s2(str, words);
		for (int i: result) System.out.println(i);
	}

}
