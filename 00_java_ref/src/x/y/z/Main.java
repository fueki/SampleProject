package x.y.z;

import java.lang.reflect.Method;

import a.b.c.MyClass;

public class Main {

	public static void main(String[] args) throws Exception{
		MyClass c = new MyClass();
		
		
		System.out.println("--pri func");
		Method method_func_pri = c.getClass().getDeclaredMethod(
				"func_pri", null);
		method_func_pri.setAccessible(true);
		method_func_pri.invoke(c, null);
		
		System.out.println();
		System.out.println("-- func_has_params");
		Method method_func_has_params = c.getClass().getDeclaredMethod(
				"func_has_params", new Class[]{int.class, int.class});
		method_func_has_params.setAccessible(true);
		method_func_has_params.invoke(c, new Object[]{10, 20});
		
		System.out.println();
		System.out.println("-- func_return");
		Method method_func_return = c.getClass().getDeclaredMethod(
				"func_return", null);
		method_func_return.setAccessible(true);
		String ret = (String)method_func_return.invoke(c, null);
		System.out.println(ret);
		
	}
}
