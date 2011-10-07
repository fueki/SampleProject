package jp.sample;

class Fruots {

	public void method(int value){
		System.out.println("Fruots1" + value);
	}
}

class App extends Fruots1{
	public void method(int value){
		System.out.println("App" + value);
	}
}

class Pet{
	enum Animel{DOG,CAT,BIRD};
	Animel kind;
}
class Anime{}
class Cat extends Anime{}
class Fruots1 {
	public static void main(String[] args){
		Pet ps = new Pet();
		ps.kind = Pet.Animel.BIRD;
//		try{
//			return;
//			throw new Exception();
//		}catch (Exception e) {
//			System.out.println("Exception");
//		}finally{
//			System.out.println("finally");
//		}
//	new Thread(new X()).start();
//	List<Anime> list = new ArrayList<Cat>();
//	List<Anime> list = new ArrayList<Anime>();
//	List<?> list = new ArrayList<Cat>();
//	List<?> list = new ArrayList<Anime>();
//	List<? extends Anime> list = new ArrayList<Cat>();
//	List<? super Anime> list = new ArrayList<Anime>();
	

//	list.add(new Cat());
//	list.add(new Cat());
//	
//	for (Anime s:list){
//		System.out.println(s);
//	}
	}
}

interface MyInterface{
	void func();
	void func(int i);
}

//class Test implements MyInterface{
//	public void func(){}
//	public void func(int s){}
//}

class X implements Runnable{
	public void run(){
		System.out.println("run");
	}
	public void start(){
		System.out.println("start");
	}
	public void stop(){
		System.out.println("stop");
	}
}
