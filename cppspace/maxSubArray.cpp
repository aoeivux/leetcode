#include <algorithm>
# include <bits/stdc++.h>
#include <iterator>
#include <vector>
using namespace std;

class Solution {
public:
    int maxSubArray(vector<int>& nums) {
		if(nums.empty()) return 0;
        int l = nums.size();
        // 定义dp状态
        vector<int> dp;
        // 定义初始值
        dp[0]  = nums[0];
        // 状态转移方程
        for(int i = 1 ;i<l; i++) {
            if(dp[i-1] > 0) {
                dp[i] = dp[i-1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }

		// res = *max_element(nums.begin(), nums.end());

		int res = dp[0];
		for (int i = 1; i < l; i++) {
			res = max(res, dp[i]);
		}

		return res;

    }
};