package day0108;

/**
 * 실행 중 인 JVM에서 하나의 객체가 만들어지고, 하나의 객체만 사용되는 Pattern
 * @author Owner
 */
public class Singleton {
	private static Singleton single;
	
	/**
	 * 1. class외부에서 객체화를 하지못하도록 막는다.
	 */
	private Singleton() {
	}//Singleton
	
	public static Singleton getInstance() {
		if(single==null) {//객체가 생성되어있지 않다면 객체를 생성하여 반환하고
			single=new Singleton();
		}//end if
		//객체가 생성되어있다면 생성된 객체가 반환
		return single;
	}
	
}//class
