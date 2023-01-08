class Main {
	public int missingNumber(int[] nums) {
		int[] newInt = new int[nums.length + 1];
		for (int i = 0; i < newInt.length; i++) {
			newInt[i] = i;
		}
		for (int i = 0; i < nums.length; ) {
			if ((newInt[i] - nums[i])!=0) {
				return newInt[i];
			}

			i++;
			if(i==nums.length){
				return newInt[i];
			}

		}
		return 0;
	}

	public static void main(String[] args) {
		new Main().missingNumber(new int[] { 1, 2, 3, 4, 5 });
	}

}