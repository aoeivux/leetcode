class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(i);
		}
		return true;
    }

	public static void main(String[] args) {
		new Solution().findNumberIn2DArray(new int[][]{{1,2,3,4,5},{6,7,8,9,10}}, 9);
	}
}