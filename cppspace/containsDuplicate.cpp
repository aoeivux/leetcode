# include <bits/stdc++.h>
#include <vector>

using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        int len = nums.size();
        vector<int> res(2, 0);
        unordered_map<int, int> m;
        for(auto v : nums) {
            if(m.find(target - v) != m.end()){
                res.push_back(v);
                res.push_back(target - v);
                return res;
            }
			m[v] = 1;
        }
        return res;
    }
};