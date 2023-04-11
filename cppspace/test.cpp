#include <algorithm>
# include <bits/stdc++.h>

using namespace std;

int main(int argc, const char** argv) {
	vector<int> res = {1 ,2,3};

	std::cout << *max_element(res.begin(), res.end()) << std::endl;
	return 0;
}