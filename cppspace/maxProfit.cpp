# include <bits/stdc++.h>

using namespace std;

// 版本一
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int len = prices.size();
        if (len == 0) return 0; // 开始为0
        vector<vector<int>> dp(len, vector<int>(2));
        dp[0][0] -= prices[0]; //持有股票
        dp[0][1] = 0;// 不持股票
        for (int i = 1; i < len; i++) {
            dp[i][0] = max(dp[i - 1][0], -prices[i]);
            dp[i][1] = max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
        }
        return dp[len - 1][1];
    }
};