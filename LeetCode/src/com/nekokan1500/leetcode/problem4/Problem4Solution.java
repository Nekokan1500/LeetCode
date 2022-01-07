package com.nekokan1500.leetcode.problem4;

public class Problem4Solution {
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		// Merge the two input arrays to a new combined sorted array
		int n1 = nums1.length;
		int n2 = nums2.length;
		
		int n = n1 + n2;
		int[] numbers = new int[n];
		
		if (n1 == 0 && n2 == 0) {
			return 0;
		}
		else if (n1 != 0 && n2 == 0) {
			numbers = nums1;
		}
		else if (n1 == 0 && n2 != 0) {
			numbers = nums2;
		}
		else {
			// indices that track the elements of the two arrays
			int j = 0;
			int k = 0;
			
			for (int i = 0; i < n; i++) {
				if (nums1[j] <= nums2[k]) {
					numbers[i] = nums1[j];
					j += 1;
					if (j == n1) {
						System.arraycopy(nums2, k, numbers, i+1, n2-k);
						break;
					}
				}
				else {
					numbers[i] = nums2[k];
					k += 1;
					if (k == n2) {
						System.arraycopy(nums1, j, numbers, i+1, n1-j);
						break;
					}
				}
			}
		}
		
		if (n % 2 == 0) {
			return (numbers[n/2]+ numbers[n/2-1])/2.0;
		}
		else {
			return numbers[n/2];
		}
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Problem4Solution s = new Problem4Solution();
		int[] numbers1 = {1,2};
		int[] numbers2 = {0,3,4,5,7};
		System.out.println(s.findMedianSortedArrays(numbers1, numbers2));
		// output is 3.0
	}

}
