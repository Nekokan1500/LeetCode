import java.util.HashMap;

/*
 * Problem 1: Two Sum
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 */

public class Problem1Solution {
	
	public int[] twoSum(int[] nums, int target) {
		// map1 maps index to value
		HashMap<Integer,Integer> map1 = new HashMap<Integer,Integer>();
		// map2 maps value to index
		HashMap<Integer,Integer> map2 = new HashMap<Integer,Integer>();
		int[] pair = new int[2];
        for (int i = 0; i < nums.length; i++) {
        	map1.put(Integer.valueOf(i), Integer.valueOf(nums[i]));
        	map2.put(Integer.valueOf(nums[i]), Integer.valueOf(i));
        }
        for (int j = 0; j < nums.length; j++) {
        	Integer y = Integer.valueOf(target - nums[j]);
        	if (map1.containsValue(y)) {
        		pair[0] = j;
        		pair[1] = map2.get(y);
        	}
        }
        return pair;	
    }
	
	public static void main(String[] args) {
		Problem1Solution s = new Problem1Solution();
		int[] input = {5,4,2,7,1,5,3,6};
		int[] result = s.twoSum(input,11);
		System.out.println("[" + result[0] + "," + result[1]+"]");
		// Result is [7,5]
	}
}
