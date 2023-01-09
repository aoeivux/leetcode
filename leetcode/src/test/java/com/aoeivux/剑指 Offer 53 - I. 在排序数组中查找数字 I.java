class Solution {
	public int search(int[] nums, int target) {
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			while (nums[i] == target) {
				max++;
				i++;
				if (i >= nums.length)
					break;
			}
		}

		return max;
	}
}

class SolutionTwo {
	public int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] <= target)
				left = mid + 1;
			else
				right = mid - 1;
		}
		int res = 0;
		while (right >= 0 && nums[right] == target && right-- >= 0)
			res++;
		return res;
	}
}