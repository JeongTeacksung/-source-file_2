package day1221;

public class Test {

	public Test() {
		String s="c:/dev/temp/java_read.txt";
		StringBuilder sb=new StringBuilder(s);
		System.out.println(sb.insert(s.lastIndexOf("."),"_bak"));
		
		
		
	}//Test
	
	public static void main(String[] args) {
		new Test();
	}//main

}//class
