package a.b.c;

public class MyClass {

	private void func_pri(){
		System.out.println("func_pri");
	}
	
	private void func_has_params(int a, int b){
		System.out.println("func_has_params a:" + a +  " b:" + b);
	}
	
	private String func_return(){
		return "hogehoge";
	}
}
