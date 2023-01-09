class Solution {
    String reverseLeftWords(String s, int n) {
/* 		String s1 = (String) s.subSequence(0, n);
		String s2 = (String) s.subSequence(n, s.length());
		return s1+s2; */
		StringBuilder sb = new StringBuilder();
		for (int i = n; i < s.length(); i++) {
			sb.append(s.charAt(i));
		}

		for (int i = 0; i < n; i++) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
    }
};