package day0103;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UseLambda {

	public static void main(String[] args) {
//		TestLambda tl = () -> new Date().toString(); // return 생략 가능.

//		TestLambda tl = () -> {
//			return new Date().toString();
//		}; // return을 사용할 경우에는 {}를 사용해야 합니다.

		TestLambda tl = () -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(new Date());
		};

		System.out.println(tl.toDay());

	}// main

}// class
