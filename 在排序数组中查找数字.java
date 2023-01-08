import java.util.ArrayList;
import java.util.List;

class Solution {
	public int search(int[] nums, int target) {
		List<Integer> list = new ArrayList<Integer>();
        int max  = 0, i=0;
		while(true) {
			while(nums[i] == target && i<nums.length){
                max = max+1;
                i++;
                if(i>=nums.length) break;
                
			}
			break;
		}
		return max;
	}
}