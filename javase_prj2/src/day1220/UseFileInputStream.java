package day1220;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * HDD에 존재하는 File과 연결하여 그 내용을 읽어 들일 때 사용하는
 * FileInputStream
 * @author Owner
 */
public class UseFileInputStream {
	public UseFileInputStream() throws IOException{
		File file=new File("c:/dev/temp/java_read.txt");
		if(file.exists()) {
			BufferedReader br=null;
			try {
				//스트림을 생성하여 JVM에서 HDD의 파일과 연결
//				FileInputStream fis=new FileInputStream(file);
//				int temp=0;
//				
//				while( (temp=fis.read()) != -1 ) { //읽어들인 내용이 존재한다면
//					System.out.print((char)temp);
//				}//end while
//				//스트림사용을 완료했으면 스트림을 끊어서 메모리누수를 막는다. 
//					fis.close();
				
				///////////////////////////12-20-2018 코드 추가////////////////////////////
				// 8bit stream과 16bit stream연결 : 한글이 깨지는 문제해결
//				FileInputStream fis=new FileInputStream(file); // 파일과 연결
//				InputStreamReader isr = new InputStreamReader(fis); // 8bit + 16bit 연결
//				br = new BufferedReader(isr); // 속도개선, 줄단위 읽기
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				
				
				String temp="";
				// 줄단위(\n전까지)로 읽어서 읽어들인 내용이 있다면
				while( (temp=br.readLine()) != null ) { 
					System.out.println( temp ); //출력
				}//end while
				
			}finally {
				if(br != null) {	br.close(); }//end if // 반드시 연결종료
			}//end finally
			
		}else {
			System.out.println("경로나 파일명을 확인하세요.");
		}//end else
		
	}//UseFileInputStream
	
	public static void main(String[] args) {
		try {
			new UseFileInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
	}//main

}//class
