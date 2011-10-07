package com.example.usb.util;

import java.lang.reflect.Method;

public class RefrectionUtil {

	public static Method getMethod(String func, Class<?> clazz) throws Exception{
		Method method;
		method = clazz.getDeclaredMethod(
				func, null);
		method.setAccessible(true);
		return method;
	}
}
