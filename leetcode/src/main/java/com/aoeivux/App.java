package com.aoeivux;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		for (int index = 0; index < arrayList.size(); index++) {
			System.out.println(index % 2 == 0);
		}
	}
}
