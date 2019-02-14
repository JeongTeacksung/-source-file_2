package day0108;

public class UseSingleton {

	
	public static void main(String[] args) {
		//Singleton Pattern으로 만들어진 객체 사용
//		Singleton s=new Singleton(); //생성자가 private이므로 클래스외부에서 객체화가 되지 않는다
		
		Singleton s=Singleton.getInstance();
		Singleton s1=Singleton.getInstance();
		Singleton s2=Singleton.getInstance();
		Singleton s3=Singleton.getInstance();
		
		System.out.println(s);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		
	}//main
	
}//class
