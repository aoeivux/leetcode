package com.aoeivux.util;

public class StringSort {
	public void quickSort(String[] strs, int l, int h){
		if(l < h){
			int m = getMid(strs, l, h);
			quickSort(strs, l, m - 1);
			quickSort(strs, m+1, h);
		}
	}

	private int getMid(String[] strs, int l, int h) {
		//数组的一个数是基准数
		String temp = strs[l];
		while(l < h) {
			//从右向左找到小于基准数的数
			while(l < h && (strs[h] + temp).compareTo(strs[l] + temp) >= 0) h--;
            //把比基准小的数移到低端
			//从右向左找到大于基准数的数
			while(l < h && (strs[h] + temp).compareTo(strs[l] + temp) <= 0) l++;
            //把比基准大的数移到高端
		}
		strs[l] = temp;
		return l;
	}
	void swap(String[] strs, int i, int j) {
		String temp = "";
		temp = strs[i];
		strs[i] = strs[j];
		strs[j] = temp;
	}
}
