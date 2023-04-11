package com.aoeivux;

import java.lang.reflect.Constructor;

public class Test {
	@InitMethod
	@org.junit.Test
	public void hello() throws Exception{
		Class clazz = Class.forName("com.aoeivux.InitMethod");
		Constructor constructor = clazz.getConstructor(null);
		Object newInstance = constructor.newInstance(null);
		System.out.println(newInstance);

	}
}
