# include <bits/stdc++.h>
using namespace std;
class Solution {
public:
    int strToInt(string str) {
        if (str.empty()) return 0;
        int index = 0, n = str.size(), sign = 1, res = 0;
        // 处理前置空格
        while (index < n && str[index] == ' ') {
            ++index;
        }
        // 处理符号
        if (index < n && (str[index] == '+' || str[index] == '-')) {
            sign = str[index++] == '+' ? 1 : -1;
        }
        // 处理数字
        while (index < n && isdigit(str[index])) {
            int digit = str[index] - '0';
            // 判断是否溢出
            if (res > (INT_MAX - digit) / 10) {
                return sign == 1 ? INT_MAX : INT_MIN;
            }
            res = res * 10 + digit;
            ++index;
        }
        return res * sign;
    }
};